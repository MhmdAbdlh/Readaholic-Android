package com.example.android.readaholic.books
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.android.readaholic.R
import com.example.android.readaholic.books.Cbookdata.bookid
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
        val toolbar = findViewById<View>(R.id.Main_toolbarr) as Toolbar
        setSupportActionBar(toolbar)
        bookreviews= ArrayList()
        likedreviews= ArrayList()
        adapter= ReviewAdabterlist1()
        list.adapter=adapter
        swiperefresh.setOnRefreshListener {
            bookreviews!!.clear()
            if (UserInfo.ISMemic)
            {
                memicReviews(Cbookdata.bookid)
            }
            else{
                feedReviewDataFromURL(Cbookdata.bookid)
            }
            swiperefresh.isRefreshing=false

        }
        if (UserInfo.ISMemic)
        {
            memicReviews(Cbookdata.bookid)

        }
        else{
            feedReviewDataFromURL(Cbookdata.bookid)
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

    /**
     * collect all reviews id the the user put a like on it
     *
     * @param jsonarray
     */
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
                    Creviewdata.userId=currentreview.userId
                    startActivity(intent)

                }

            }
            myview.reviwernametxtui.setOnClickListener {
                val intent=Intent(baseContext,Profile::class.java)
                intent.putExtra("user-idFromFollowingList",currentreview.userId)
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

            myview.likereviewtxtui.setOnClickListener {
                var likes:Int=myview.numberoflikesreviewtxtui.text.toString().toInt()
                if (UserInfo.mIsGuest)
                {

                    Toast.makeText(baseContext, "Please Login To be able to like a review", Toast.LENGTH_SHORT).show()
                }
                else {
                    if (likeservicies(currentreview.reviewid)||UserInfo.ISMemic) {
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

    /**
     * make a like or unlike to the review
     *
     * @param reviewid
     * @return true if the action have been done
     */


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
                    Toast.makeText(this,"Something went wrong with the server",Toast.LENGTH_SHORT).show()
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

    /**
     * dummy reviews for memic part
     *
     * @param bookid
     */
    fun memicReviews(bookid:Int)
    {
        var jsonresponse:JSONObject?=null
        when(bookid)
        {
            2->jsonresponse= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":28,\"book_id\":2,\"body\":null,\"rating\":3,\"shelf_name\":0,\"likes_count\":0,\"comments_count\":0,\"user_id\":1,\"created_at\":\"2019-05-03 14:24:31\",\"updated_at\":\"2019-05-03 14:30:45\",\"username\":\"test\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":5,\"book_id\":2,\"body\":\"yTukzlyHI0\",\"rating\":0,\"shelf_name\":2,\"likes_count\":0,\"comments_count\":0,\"user_id\":3,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"waleed\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":6,\"book_id\":2,\"body\":\"LLgpRopfoc\",\"rating\":5,\"shelf_name\":2,\"likes_count\":0,\"comments_count\":0,\"user_id\":6,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"TheLeader\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":9,\"book_id\":2,\"body\":\"A8rDP8nSMI\",\"rating\":4,\"shelf_name\":0,\"likes_count\":0,\"comments_count\":0,\"user_id\":2,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"ta7a\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":14,\"book_id\":2,\"body\":\"wthxryziXe\",\"rating\":1,\"shelf_name\":1,\"likes_count\":0,\"comments_count\":0,\"user_id\":7,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"Mohamed\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":15,\"book_id\":2,\"body\":\"pZ96bm5K3t\",\"rating\":0,\"shelf_name\":0,\"likes_count\":0,\"comments_count\":0,\"user_id\":4,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"Nour\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":17,\"book_id\":2,\"body\":\"V1MHvmduq1\",\"rating\":3,\"shelf_name\":2,\"likes_count\":0,\"comments_count\":0,\"user_id\":3,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"waleed\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":19,\"book_id\":2,\"body\":\"c1kxrJBizg\",\"rating\":2,\"shelf_name\":0,\"likes_count\":0,\"comments_count\":0,\"user_id\":3,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"waleed\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"liked_reviews\":[{\"id\":7},{\"id\":27}]}")

            3->jsonresponse= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":10,\"book_id\":3,\"body\":\"i98eV2lxzG\",\"rating\":3,\"shelf_name\":0,\"likes_count\":0,\"comments_count\":0,\"user_id\":3,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"waleed\",\"userimagelink\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":12,\"book_id\":3,\"body\":\"EdOCBYW1qm\",\"rating\":3,\"shelf_name\":2,\"likes_count\":0,\"comments_count\":0,\"user_id\":6,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"TheLeader\",\"userimagelink\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":13,\"book_id\":3,\"body\":\"fCLjnc8tLK\",\"rating\":3,\"shelf_name\":0,\"likes_count\":0,\"comments_count\":0,\"user_id\":5,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"Salma\",\"userimagelink\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"}],\"liked_reviews\":[{\"id\":7},{\"id\":27}]}")
            1->jsonresponse= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":1,\"book_id\":1,\"body\":\"dECsVckfzg\",\"rating\":4,\"shelf_name\":2,\"likes_count\":0,\"comments_count\":0,\"user_id\":4,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"Nour\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":2,\"book_id\":1,\"body\":\"FFewhMCVy6\",\"rating\":5,\"shelf_name\":2,\"likes_count\":0,\"comments_count\":0,\"user_id\":5,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"Salma\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":4,\"book_id\":1,\"body\":\"G2nFegZx6v\",\"rating\":2,\"shelf_name\":0,\"likes_count\":0,\"comments_count\":0,\"user_id\":2,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"ta7a\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":8,\"book_id\":1,\"body\":\"xrPr40QXbQ\",\"rating\":1,\"shelf_name\":0,\"likes_count\":0,\"comments_count\":0,\"user_id\":7,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"Mohamed\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":16,\"book_id\":1,\"body\":\"NAB6cGoqlJ\",\"rating\":4,\"shelf_name\":2,\"likes_count\":0,\"comments_count\":0,\"user_id\":3,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"waleed\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":18,\"book_id\":1,\"body\":\"s2Ol0pFhmA\",\"rating\":4,\"shelf_name\":2,\"likes_count\":0,\"comments_count\":0,\"user_id\":7,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"Mohamed\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"},{\"id\":20,\"book_id\":1,\"body\":\"DuFILv2rX4\",\"rating\":1,\"shelf_name\":1,\"likes_count\":0,\"comments_count\":0,\"user_id\":4,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"Nour\",\"userimagelink\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"liked_reviews\":[{\"id\":7},{\"id\":27}]}")

            4->jsonresponse= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":27,\"book_id\":4,\"body\":\"Hello\",\"rating\":4,\"shelf_name\":2,\"likes_count\":2,\"comments_count\":0,\"user_id\":2,\"created_at\":\"2019-05-03 13:06:10\",\"updated_at\":\"2019-05-03 13:06:10\",\"username\":\"ta7a\",\"userimagelink\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":3,\"book_id\":4,\"body\":\"U6LXG7PWqZ\",\"rating\":4,\"shelf_name\":1,\"likes_count\":0,\"comments_count\":1,\"user_id\":7,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"Mohamed\",\"userimagelink\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":7,\"book_id\":4,\"body\":\"evKmmFuJMu\",\"rating\":0,\"shelf_name\":1,\"likes_count\":1,\"comments_count\":2,\"user_id\":3,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\",\"username\":\"waleed\",\"userimagelink\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"}],\"liked_reviews\":[{\"id\":7},{\"id\":27}]}")
        }

        //
        feedReviewsFromJson(jsonresponse!!.getJSONArray("pages"))
        feedLikedReviews(jsonresponse!!.getJSONArray("liked_reviews"))
        adapter!!.notifyDataSetChanged()

    }
}
