package com.example.android.readaholic.books
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.android.readaholic.R
import com.example.android.readaholic.contants_and_static_data.Urls
import com.example.android.readaholic.contants_and_static_data.Urls.makeLikeUnlike
import com.example.android.readaholic.contants_and_static_data.UserInfo
import com.example.android.readaholic.profile_and_profile_settings.Profile
import com.google.gson.JsonArray
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_reviews.*
import kotlinx.android.synthetic.main.bookreview.view.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.max
class BookReviewsActivity : AppCompatActivity() {
    var bookreviews:ArrayList<BookReview>?=null
    var likedreviews:ArrayList<Int>?=null
    var adapter: ReviewAdabterlist1?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_reviews)
        bookreviews= ArrayList()
        likedreviews= ArrayList()
       feedReviewDataFromURL(Cbookdata.bookid)
        adapter= ReviewAdabterlist1()
        list.adapter=adapter
        swiperefresh.setOnRefreshListener {
            bookreviews!!.clear()
            feedReviewDataFromURL(Cbookdata.bookid)
            swiperefresh.isRefreshing=false
        }

    }

    /**
     * git the list of reviews as a json string from the end point url
     *
     * @param bookid
     */

fun feedReviewDataFromURL(bookid:Int)
{
    var queue = Volley.newRequestQueue(this)
    var url=Urls.getShowReviewsForaBook(bookid.toString())
    val stringRequest = StringRequest(Request.Method.GET,url,
            Response.Listener<String> { response1 ->
                var  jsonresponse= JSONObject(response1)
                feedReviewsFromJson(jsonresponse!!.getJSONArray("pages"))
                feedLikedReviews(jsonresponse!!.getJSONArray("liked_reviews"))
                adapter!!.notifyDataSetChanged()},
            Response.ErrorListener {error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            })
    queue.add(stringRequest)

}
   fun feedLikedReviews(jsonarray:JSONArray)
    {
        var likedreview:Int
        for( i in 0..jsonarray.length()-1)
        {
            likedreview=jsonarray.getJSONObject(i).getInt("id")
            likedreviews!!.add(likedreview)
        }
    }

    /**
     *
     * extract the Reviews data from the json array
     * @param jsonarray
     */
    fun feedReviewsFromJson(jsonarray:JSONArray)
    {

      for( i in 0..jsonarray.length()-1)
        {
           var jsonobject=jsonarray.getJSONObject(i)
           bookreviews!!.add(BookReview(checknotnigativeintegers(jsonobject.getString("id").toInt()),checknotnigativeintegers(jsonobject.getString("user_id").toInt()),jsonobject.getString("book_id").toInt(),
                   jsonobject.getString("body"),jsonobject.getString("rating").toInt(),jsonobject.getString("likes_count").toInt()
                   ,checknotnigativeintegers(jsonobject.getString("comments_count").toInt()),jsonobject.getString("username"),jsonobject.getString("userimagelink"),jsonobject.getString("created_at")))
        }

    }

    /**
     * This Adapter mainly to take the list of reviews and show them in the UI
     *
     */


    inner class ReviewAdabterlist1(): BaseAdapter()
    {
        /**
         * will loop for every eelemnt in bookreviews array and feed the data to the UI
         *
         * @param position
         * @param convertView
         * @param parent
         */
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myview=layoutInflater.inflate(R.layout.bookreview,null)
            var currentreview= bookreviews!![position]
            myview.reviwernametxtui.text=currentreview.username
            myview.ratingui.rating=currentreview.rating.toFloat()
            myview.descriptionreviewui.text=currentreview.reviewbody
            myview.numberoflikesreviewtxtui.text=currentreview.numberoflikes.toString()
            myview.numberofcommentreviewtxtui.text=currentreview.numberofcomments.toString()
            myview.dateofreviewtxtui.text=currentreview.createdate
           Picasso.get().load(currentreview.userimageurl).into( myview.reviewerimage)
            myview.commentreviewtxtui.setOnClickListener {
                if (UserInfo.mIsGuest)
                {

                    Toast.makeText(baseContext, "Please Login To be able to comment on a review", Toast.LENGTH_SHORT).show()
                }
                else{

                    var intent= Intent(baseContext, ReviewActivity::class.java)
                    Creviewdata.reviewid=currentreview.reviewid
                    startActivity(intent)

                }

            }
            myview.readmoretxtui.setOnClickListener {

                var intent= Intent(baseContext, ReviewActivity::class.java)
                Creviewdata.reviewid=currentreview.reviewid
                startActivity(intent)
            }
            myview.reviewerimage.setOnClickListener {

                var intent= Intent(baseContext, Profile::class.java)
                startActivity(intent)

            }
            for(reviewid in likedreviews!!)
            {
                if(reviewid==currentreview.reviewid)
                {
                    myview.likereviewtxtui.text = "unlike"
                }

            }
            //myview.likereviewtxtui.text=
            myview.likereviewtxtui.setOnClickListener {
                var likes:Int=myview.numberoflikesreviewtxtui.text.toString().toInt()
                if (UserInfo.mIsGuest)
                {

                    Toast.makeText(baseContext, "Please Login To be able to like a review", Toast.LENGTH_SHORT).show()
                }
                else {
                    if (likeservicies(currentreview.reviewid)) {
                        if (myview.likereviewtxtui.text == "like") {
                            likes += 1
                            myview.likereviewtxtui.text = "unlike"
                        } else {
                            likes -= 1
                            myview.likereviewtxtui.text = "like"
                        }
                        myview.numberoflikesreviewtxtui.text = likes.toString()

                    } else {

                        Toast.makeText(baseContext, "Error with the server .. try again later", Toast.LENGTH_SHORT).show()

                    }

                }

            }
            return myview
        }

        override fun getItem(position: Int): Any {
            return  bookreviews!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return bookreviews!!.size
        }

    }


    fun likeservicies(reviewid:Int):Boolean
    {

        val queue = Volley.newRequestQueue(this)
        var url =makeLikeUnlike(reviewid.toString())
        var success=true
        val stringRequest = StringRequest(Request.Method.POST,url,
                Response.Listener<String> { response ->
                    var jsonresponse=JSONObject(response)
                    if(jsonresponse.getString("status")=="true") {
                        Toast.makeText(this, jsonresponse.getString("Message"), Toast.LENGTH_SHORT).show()

                    }
                },
                Response.ErrorListener {
                    Toast.makeText(this,"Someething went wrong with the server",Toast.LENGTH_SHORT).show()
                    success=false
                }
        )
        queue.add(stringRequest)
        return success
    }



    /**
     * THis to check to format of the date returned
     *
     * @param date
     */
    fun checkformat(date:String):String
    {
        if(date.length==10)
            return date
        else{
            return "2019-12-12"
        }

    }

    /**
     * This to make sure that the id returned is positive
     *
     * @param id
     */
    fun checknotnigativeintegers(id:Int):Int
    {
        return max(id,1)

    }
}
