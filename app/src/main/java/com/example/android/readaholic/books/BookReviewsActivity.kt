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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_reviews.*
import kotlinx.android.synthetic.main.bookreview.*
import kotlinx.android.synthetic.main.bookreview.view.*
import org.json.JSONArray
import org.json.JSONObject

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

fun feedReviewDataFromURL(bookid:Int)
{

    val queue = Volley.newRequestQueue(this)
    var urltry="https://api.myjson.com/bins/128tbq"
    val stringRequest = StringRequest(Request.Method.GET,urltry,
            Response.Listener<String> { response ->
                var  jsonresponse= JSONObject(response)
                feedFromDummey(jsonresponse!!.getJSONArray("pages"))

                adapter!!.notifyDataSetChanged()
            },
            Response.ErrorListener {

                adapter!!.notifyDataSetChanged()
            })

    queue.add(stringRequest)

}
    fun feedFromDummey(jsonarray:JSONArray)
    {

      for( i in 0..jsonarray.length()-1)
        {
           var jsonobject=jsonarray.getJSONObject(i)
           bookreviews!!.add(BookReview(jsonobject.getString("id").toInt(),jsonobject.getString("userId").toInt(),jsonobject.getString("bookId").toInt(),
                   jsonobject.getString("body"),jsonobject.getString("rating").toInt(),jsonobject.getString("lastUpdate"),jsonobject.getString("numberLikes").toInt()
                   ,jsonobject.getString("numberComments").toInt(),jsonobject.getString("username"),jsonobject.getString("userimagelink")))
        }

    }




    inner class ReviewAdabterlist1(): BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myview=layoutInflater.inflate(R.layout.bookreview,null)
            var currentreview= bookreviews!![position]
            myview.reviwernametxtui.text=currentreview.username
            myview.ratingui.rating=currentreview.rating.toFloat()
            myview.dateofreviewtxtui.text=currentreview.lastupdate
            myview.descriptionreviewui.text=currentreview.reviewbody
            myview.numberoflikesreviewtxtui.text=currentreview.numberoflikes.toString()
            myview.numberofcommentreviewtxtui.text=currentreview.numberofcomments.toString()
           // Picasso.get().load("https://i.ytimg.com/vi/3nmoffllWTw/hqdefault.jpg").into(myview.profile_image1)
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
}
