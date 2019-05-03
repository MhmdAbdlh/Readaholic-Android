package com.example.android.readaholic.books

import android.content.Intent
import android.graphics.Color
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
import com.example.android.readaholic.profile_and_profile_settings.Profile
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_reviews.*
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.commentticket.view.*
import org.json.JSONArray
import org.json.JSONObject
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.example.android.readaholic.contants_and_static_data.UserInfo


class ReviewActivity : AppCompatActivity() {
    var CommentList:ArrayList<CommentInfo>?=null
    var commentadapter: CommentsAdabterlist?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.android.readaholic.R.layout.activity_review)
        CommentList= ArrayList()
        commentadapter= CommentsAdabterlist()
        commentlist.adapter=commentadapter
        getReviewInfo()
        swiperefreshcomment.setOnRefreshListener {
            CommentList!!.clear()
            swiperefreshcomment.isRefreshing=false
        }
        feedCommentsDataFromURL(Creviewdata.reviewid)
        swiperefreshcomment.setOnRefreshListener {
            CommentList!!.clear()
            feedCommentsDataFromURL(Creviewdata.reviewid)
            swiperefreshcomment.isRefreshing=false
        }
        bookimage.setOnClickListener {
            var intent=Intent(this, BookPageActivity::class.java)
            startActivity(intent)

        }
        booktitleui.setOnClickListener {
            var intent=Intent(this, BookPageActivity::class.java)
            startActivity(intent)
        }
        reviewerimage.setOnClickListener {
        //    var intent=Intent(this, Profile::class.java)
          //  intent.putExtra("user-idFromFollowingList",)
          //  startActivity(intent)


        }
        reviwernametxtui.setOnClickListener {
            //    var intent=Intent(this, Profile::class.java)
            //  intent.putExtra("user-idFromFollowingList",)
            //  startActivity(intent)


        }
        if (UserInfo.mIsGuest)
        {

            Addcomentlayout.visibility=View.GONE
        }
    }

    fun makeReviewLike(view:View)
    {

        if (UserInfo.mIsGuest)
        {

            Toast.makeText(baseContext, "Please Login To be able to like a review", Toast.LENGTH_SHORT).show()
        }
        else {
            var likes: Int = numberoflikesreviewtxtui.text.toString().toInt()
            if (likeservicies(Creviewdata.reviewid)) {


                if (likereviewtxtui.text == "like") {

                    likes += 1
                    likereviewtxtui.text = "unlike"
                } else {
                    likes -= 1
                    likereviewtxtui.text = "like"

                }
                numberoflikesreviewtxtui.text = likes.toString()


            }


        }

    }
    /**
     * get all review info from the end point (Server)
     *
     */
    fun getReviewInfo()
    {
        val queue = Volley.newRequestQueue(this)
        var url=Urls.getShowReview( Creviewdata.reviewid.toString())
        val stringRequest = StringRequest(Request.Method.GET,url,
                Response.Listener<String> { response ->
                    var  jsonresponse= JSONObject(response)
                        if(jsonresponse.getString("status")=="success")
                        {
                            feedCreview(jsonresponse.getJSONArray("pages").getJSONObject(0))
                            feeduser(jsonresponse.getJSONArray("user").getJSONObject(0))
                            feedbook(jsonresponse.getJSONArray("book").getJSONObject(0))
                            feedauthor(jsonresponse.getJSONArray("auther").getJSONObject(0))
                            feedliked(jsonresponse.getInt("if_liked"))
                            feedReviewDate()
                        }

                },
                Response.ErrorListener {

                })

        queue.add(stringRequest)

    }
        fun feedliked(isliked:Int)
        {
            if(isliked==1)
            {
                likereviewtxtui.text="unlike"
            }

        }


    /**
     * git the review data and assign them to the ui
     *
     */
    fun feedReviewDate()
    {
        reviwernametxtui.text=Creviewdata.username
        ratingui.rating=Creviewdata.rating.toFloat()
        dateofreviewtxtui.text=Creviewdata.lastupdate
        descriptionreviewui.text=Creviewdata.reviewbody
        numberoflikesreviewtxtui.text=Creviewdata.numberoflikes.toString()
        numberofcommentreviewtxtui.text=Creviewdata.numberofcomments.toString()
        booktitleui.text=Creviewdata.book_name
        authornameui.text=Creviewdata.author_name
        reviwernametxtui.text=Creviewdata.username
       Picasso.get().load(Creviewdata.book_image).into(bookimage)
        Picasso.get().load(Creviewdata.userimageurl).into(reviewerimage)
    }

    /**
     * feed it from the url
     *
     * @param jsonreaponse
     */

    fun feedCreview(jsonreaponse: JSONObject)
    {
        Creviewdata.rating=jsonreaponse.getString("rating").toInt()
        Creviewdata.lastupdate=jsonreaponse.getString("updated_at")
        Creviewdata.reviewbody=jsonreaponse.getString("body")
        Creviewdata.numberoflikes=jsonreaponse.getInt("likes_count")
        Creviewdata.numberofcomments=jsonreaponse.getInt("comments_count")
        Creviewdata.reviewid=jsonreaponse.getInt("id")
    }

    /**
     * feed user info who posted that review
     *
     * @param jsonreaponsebook
     */

    fun feeduser(jsonreaponsebook: JSONObject)
    {
        Creviewdata.username=jsonreaponsebook.getString("user_name")
        Creviewdata.userimageurl=jsonreaponsebook.getString("image_link")

    }

    /**
     * feed book info which the review posted on it
     *
     * @param jsonreaponsebook
     */
    fun feedbook(jsonreaponsebook: JSONObject)
    {
        Creviewdata.book_name=jsonreaponsebook.getString("book_name")
        Creviewdata.book_image=jsonreaponsebook.getString("book_image")
    }

    /**
     * Author info of that book
     *
     * @param jsonreaponsebook
     */
    fun feedauthor(jsonreaponsebook: JSONObject)
    {
        Creviewdata.author_name=jsonreaponsebook.getString("author_name")

    }


    fun likeservicies(reviewid:Int):Boolean
    {

        val queue = Volley.newRequestQueue(this)
        var url = Urls.makeLikeUnlike(reviewid.toString())
        var success=false
        val stringRequest = StringRequest(Request.Method.POST,url,
                Response.Listener<String> { response ->
                    var jsonresponse=JSONObject(response)
                    if(jsonresponse.getString("status")=="true")
                        Toast.makeText(this,jsonresponse.getString("Message"),Toast.LENGTH_SHORT).show()
                    success=true
                },
                Response.ErrorListener {
                    Toast.makeText(this,"Someething went wrong with the server",Toast.LENGTH_SHORT).show()
                }
        )


        queue.add(stringRequest)
        return success

    }


    /**
     * called when the user want to post gis comment
     *
     * @param view
     */
    fun sendcomment(view:View)
    {
        var commenttext=writercomment.text.toString()
        if(commenttext=="")
        {
            Toast.makeText(this,"Please write something first",Toast.LENGTH_SHORT).show()
        }
        else{
         var succes= sendCommetService(commenttext)
            if(succes)
            {
           //     Toast.makeText(this,"your comment added to the review",Toast.LENGTH_SHORT).show()
                writercomment.text.clear()

            }
            else{
              //  Toast.makeText(this,"Something went wrong with the seerver",Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * server communication to post the comment
     *
     * @param commentbody
     */
    fun sendCommetService(commentbody:String):Boolean
    {
        var success=true
        val queue = Volley.newRequestQueue(this)
        var url = Urls.makecomment(Creviewdata.reviewid.toString(),commentbody)
        val stringRequest = StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    var jsonresponse=JSONObject(response)
                    if(jsonresponse.getString("status")=="true") {
                        Toast.makeText(this, "Your comment have been added ,Thank you.", Toast.LENGTH_SHORT).show()
                        closeKeyboard()

                    }
                    else if(jsonresponse.getString("status")=="false")
                    {
                        Toast.makeText(this,jsonresponse.getString("errors"),Toast.LENGTH_SHORT).show()

                    }

                },
                Response.ErrorListener {
                    Toast.makeText(this,"Something went wrong with the server",Toast.LENGTH_SHORT).show()
                    success = false

                }

        )
        queue.add(stringRequest)
        return success


    }









    /**
     * got to the provided end point url and get the json return of the list of commints and send it to git the data from it
     *
     * @param bookid
     */

    fun feedCommentsDataFromURL(bookid:Int)
    {
        val queue = Volley.newRequestQueue(this)
        var url=Urls.getlistofcomments(bookid.toString())
        val stringRequest = StringRequest(Request.Method.GET,url,
                Response.Listener<String> { response ->
                    var  jsonresponse= JSONObject(response)
                    if(jsonresponse.getString("status")=="true")
                    {
                        if(jsonresponse.has("Message"))
                        {

                            noreviewtext.visibility=View.VISIBLE
                            noreviewtext.text=jsonresponse.getString("Message")+" you can be the first on !!"
                            noreviewtext.setTextColor(Color.GREEN)

                        }
                        else{
                            feedFromJsonReturn(jsonresponse!!.getJSONArray("0"))
                            commentadapter!!.notifyDataSetChanged()
                            noreviewtext.visibility=View.GONE
                        }

                    }
                    else{
                        noreviewtext.visibility=View.VISIBLE
                        noreviewtext.text=jsonresponse.getString("Message")
                        noreviewtext.setTextColor(Color.RED)
                    }

                },
                Response.ErrorListener {
                    commentadapter!!.notifyDataSetChanged()
                })

        queue.add(stringRequest)

    }

    /**
     * extract the comments data from the jsno return and assign it to the array of comments
     *
     * @param jsonarray array of comments
     */
    fun feedFromJsonReturn(jsonarray: JSONArray)
    {

        for( i in 0..jsonarray.length()-1)
        {
            var jsonobject=jsonarray.getJSONObject(i)
            CommentList!!.add(CommentInfo(jsonobject.getString("id").toInt(),0,jsonobject.getString("username"),jsonobject.getString("image_link")
            ,jsonobject.getString("body"),jsonobject.getString("created_at"),false))
            if(jsonobject.getString("have_the_comment")=="Yes")
                CommentList!![i].havethecomment=true
        }

    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    /**
     * Delete onlt the owned comment from the review     *
     * @param commentid
     */
    fun deletecomment(commentid:Int)
    {
        val queue = Volley.newRequestQueue(this)
        var url=Urls.deletecomment(commentid.toString())
        val stringRequest = StringRequest(Request.Method.DELETE,url,
                Response.Listener<String> { response ->
                    var  jsonresponse= JSONObject(response)
                   if(jsonresponse.getString("status")=="true")
                   {
                        Toast.makeText(this,jsonresponse.getString("Message"),Toast.LENGTH_SHORT).show()
                       onRestart()
                       commentadapter!!.notifyDataSetChanged()
                   }
                    else if(jsonresponse.getString("status")=="false")
                       Toast.makeText(this,jsonresponse.getString("errors"),Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {

                })

        queue.add(stringRequest)



    }

    inner class CommentsAdabterlist(): BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myview=layoutInflater.inflate(com.example.android.readaholic.R.layout.commentticket,null)
            var currentcomment= CommentList!![position]
            myview.reviwernametxtui.text=currentcomment.username
            myview.dateofreviewtxtui.text=currentcomment.commentdate
            myview.descriptionreviewui.text=currentcomment.commentbody
            Picasso.get().load(currentcomment.cuserimageurl).into(myview.usercommentimage)
            myview.usercommentimage.setOnClickListener {
                var intent= Intent(baseContext, Profile::class.java)
                startActivity(intent)
            }
            if(currentcomment.havethecomment)
            {

                myview.deletecommentbtn.visibility=View.VISIBLE

            }
            myview.deletecommentbtn.setOnClickListener {
                deletecomment(currentcomment.commentid)
            }
            return myview
        }

        override fun getItem(position: Int): Any {
            return  CommentList!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        /**
         *get the size of the list
         *
         */
        override fun getCount(): Int {
            return CommentList!!.size
        }


    }

}
