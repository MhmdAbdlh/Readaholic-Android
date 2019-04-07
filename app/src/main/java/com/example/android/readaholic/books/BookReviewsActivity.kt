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
import com.example.android.readaholic.URLS
import com.example.android.readaholic.contants_and_static_data.Urls
import com.example.android.readaholic.contants_and_static_data.UserInfo
import com.example.android.readaholic.profile_and_profile_settings.Profile
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_reviews.*
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.bookreview.view.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.max

class BookReviewsActivity : AppCompatActivity() {
    var bookreviews:ArrayList<BookReview>?=null
    var adapter: ReviewAdabterlist1?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_reviews)
        bookreviews= ArrayList()
         var bookid=5
       feedReviewDataFromURL(bookid)
        adapter= ReviewAdabterlist1()
        list.adapter=adapter
        swiperefresh.setOnRefreshListener {
            bookreviews!!.clear()
            feedReviewDataFromURL(4)
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

    val queue = Volley.newRequestQueue(this)
    var url= Urls.ROOT+"api/showReviewsForABook?bookId=1&token="+UserInfo.sToken +"&type="+UserInfo.sTokenType
    val stringRequest = StringRequest(Request.Method.GET,url,
            Response.Listener<String> { response ->
                var  jsonresponse= JSONObject(response)
                Toast.makeText(this,UserInfo.sToken,Toast.LENGTH_LONG).show()
                Toast.makeText(this,response,Toast.LENGTH_LONG).show()
                feedReviewsFromJson(jsonresponse!!.getJSONArray("pages"))
                adapter!!.notifyDataSetChanged()
            },
            Response.ErrorListener {
             //   feedReviewsFromJson( getdummyjson().getJSONArray("pages"))
              //  adapter!!.notifyDataSetChanged()
            })

    queue.add(stringRequest)

}

    fun sendReview(view:View)
    {
        var commenttext=writerreview.text.toString()
        if(commenttext=="")
        {
            Toast.makeText(this,"Please write something first", Toast.LENGTH_SHORT).show()
        }
        else{
            var succes= sendReviewService(commenttext,2)
            if(succes)
            {
                Toast.makeText(this,"your Review Added", Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this,"Someething went wrong with the server ,try again later", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * server communication to post the comment
     *
     * @param commentbody
     */
    fun sendReviewService(reviewbody:String,rating:Int):Boolean
    {
        var success=false
        val queue = Volley.newRequestQueue(this)
        var url= Urls.ROOT+"api/11showReviewsForABook?bookId=1&token="+UserInfo.sToken +"&type="+UserInfo.sTokenType
        val stringRequest = StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    var jsonresponse=JSONObject(response)
                    if(jsonresponse.getString("status")=="true")
                        success=true


                },
                Response.ErrorListener {


                }

        )

        queue.add(stringRequest)
        return success


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
           bookreviews!!.add(BookReview(checknotnigativeintegers(jsonobject.getString("id").toInt()),checknotnigativeintegers(jsonobject.getString("userId").toInt()),jsonobject.getString("bookId").toInt(),
                   jsonobject.getString("body"),jsonobject.getString("rating").toInt(),checkformat(jsonobject.getString("lastUpdate")),jsonobject.getString("numberLikes").toInt()
                   ,checknotnigativeintegers(jsonobject.getString("numberComments").toInt()),jsonobject.getString("username"),jsonobject.getString("userimagelink")))
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
            myview.dateofreviewtxtui.text=currentreview.lastupdate
            myview.descriptionreviewui.text=currentreview.reviewbody
            myview.numberoflikesreviewtxtui.text=currentreview.numberoflikes.toString()
            myview.numberofcommentreviewtxtui.text=currentreview.numberofcomments.toString()
           Picasso.get().load(currentreview.userimageurl).into( myview.reviewerimage)
            myview.commentreviewtxtui.setOnClickListener {
               var intent= Intent(baseContext, ReviewActivity::class.java)
                Creviewdata.numberoflikes=currentreview.numberoflikes
                Creviewdata.lastupdate=currentreview.lastupdate
                Creviewdata.rating=currentreview.rating
                Creviewdata.numberofcomments=currentreview.numberofcomments
                Creviewdata.reviewid=currentreview.reviewid
                Creviewdata.userId=currentreview.userId
                Creviewdata.username=currentreview.username
                Creviewdata.userimageurl=currentreview.userimageurl
                Creviewdata.reviewbody=currentreview.reviewbody
                startActivity(intent)
            }
            myview.reviewerimage.setOnClickListener {

                var intent= Intent(baseContext, Profile::class.java)
                startActivity(intent)

            }
            myview.readmoretxtui.setOnClickListener {

                var intent= Intent(baseContext, ReviewActivity::class.java)
                Creviewdata.numberoflikes=currentreview.numberoflikes
                Creviewdata.lastupdate=currentreview.lastupdate
                Creviewdata.rating=currentreview.rating
                Creviewdata.numberofcomments=currentreview.numberofcomments
                Creviewdata.reviewid=currentreview.reviewid
                Creviewdata.userId=currentreview.userId
                Creviewdata.username=currentreview.username
                Creviewdata.userimageurl=currentreview.userimageurl
                Creviewdata.reviewbody=currentreview.reviewbody
                startActivity(intent)
            }
            myview.likereviewtxtui.setOnClickListener {
                var likes:Int=myview.numberoflikesreviewtxtui.text.toString().toInt()
                if( myview.likereviewtxtui.text=="like")
                {
                    likeservicies(Creviewdata.reviewid)

                    likes+=1
                    myview.likereviewtxtui.text="unlike"
                }
                else{
                    likes-=1
                    myview.likereviewtxtui.text="like"

                }
                myview.numberoflikesreviewtxtui.text=likes.toString()

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
    fun likeservicies(reviewid:Int)
    {

        val queue = Volley.newRequestQueue(this)
        var url = "http://"+"localhost"+":8000/api/makeLike?id="+reviewid.toString()+"&type?=2"
        val stringRequest = StringRequest(Request.Method.POST,url,
                Response.Listener<String> { response ->
                    var jsonresponse=JSONObject(response)
                    if(jsonresponse.getString("status")=="true")
                        Toast.makeText(this,"Like added",Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {
                    Toast.makeText(this,"Someething went wrong with the server",Toast.LENGTH_SHORT).show()
                }
        )

        queue.add(stringRequest)

    }


    /**
     * git the data from json string and rety=urn it as json object
     *
     */
    fun getdummyjson():JSONObject
    {
        var dummy="{\"status\": \"success\",\"pages\": [{ \"id\": 3,\"bookId\": 1,\"body\": \"This is a great book\",\"rating\": 4,\"lastUpdate\": \"2019-03-06 00:00:00\",            \"numberLikes\": 4,            \"numberComments\": 5,            \"userId\": 2,            \"username\": \"dsds\",            \"userimagelink\": \"https://upload.wikimedia.org/wikipedia/commons/c/c9/Moon.jpg\"        },        {            \"id\": 1,            \"bookId\": 1,            \"body\": \"3ash gdn elktab da ya shbab\",            \"rating\": 1,            \"lastUpdate\": \"2019-03-20 00:00:00\",            \"numberLikes\": 0,            \"numberComments\": 0,            \"userId\": 9,            \"username\": \"Nassar\",            \"userimagelink\": \"https://upload.wikimedia.org/wikipedia/commons/c/c9/Moon.jpg\"        },        {            \"id\": 5,            \"bookId\": 1,            \"body\": \"Great book from a great Author\",            \"rating\": 9,            \"lastUpdate\": \"2019-03-06 00:00:00\",            \"numberLikes\": 100,            \"numberComments\": 5,            \"userId\": 2,            \"username\": \"hossam\",            \"userimagelink\": \"https://upload.wikimedia.org/wikipedia/commons/c/c9/Moon.jpg\"  }] }"

        return JSONObject(dummy)
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
