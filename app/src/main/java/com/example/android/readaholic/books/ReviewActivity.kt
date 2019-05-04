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
import android.support.v7.widget.Toolbar
import android.view.inputmethod.InputMethodManager
import com.example.android.readaholic.contants_and_static_data.UserInfo


class ReviewActivity : AppCompatActivity() {
    var CommentList:ArrayList<CommentInfo>?=null
    var commentadapter: CommentsAdabterlist?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.android.readaholic.R.layout.activity_review)
        val toolbar = findViewById<View>(R.id.Main_toolbarr) as Toolbar
        setSupportActionBar(toolbar)
        CommentList= ArrayList()
        commentadapter= CommentsAdabterlist()
        commentlist.adapter=commentadapter
        if (UserInfo.ISMemic)
        {
            memicReveview()
        }
        else{
            getReviewInfo()
            feedCommentsDataFromURL(Creviewdata.reviewid)
        }
        swiperefreshcomment.setOnRefreshListener {
            CommentList!!.clear()
            swiperefreshcomment.isRefreshing=false
        }

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
            var intent=Intent(this, Profile::class.java)
            intent.putExtra("user-idFromFollowingList",Creviewdata.userId)
            startActivity(intent)

        }
        reviwernametxtui.setOnClickListener {
                var intent=Intent(this, Profile::class.java)
              intent.putExtra("user-idFromFollowingList",Creviewdata.userId)
              startActivity(intent)


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
            if (UserInfo.ISMemic) {


                if (likereviewtxtui.text == "like") {

                    likes += 1
                    likereviewtxtui.text = "unlike"
                } else {
                    likes -= 1
                    likereviewtxtui.text = "like"

                }
                numberoflikesreviewtxtui.text = likes.toString()
            }
            else if(likeservicies(Creviewdata.reviewid))
            {


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
                            noReviewLogic()
                })

        queue.add(stringRequest)

    }
                fun noReviewLogic()
                {
                    whenfindreview.visibility=View.GONE
                    Whennoreview.visibility=View.VISIBLE
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

    /**
     * make a like the the Review or ulike if already liked before
     *
     *
     * @param reviewid the id of the review (badehiat)
     * @return return true or flase wheather or not the action have been taken
     */

    fun likeservicies(reviewid:Int):Boolean
    {

        val queue = Volley.newRequestQueue(this)
        var url = Urls.makeLikeUnlike(reviewid.toString())
        var success=true
        val stringRequest = StringRequest(Request.Method.POST,url,
                Response.Listener<String> { response ->
                    var jsonresponse=JSONObject(response)
                    if(jsonresponse.getString("status")=="true")
                        Toast.makeText(this,jsonresponse.getString("Message"),Toast.LENGTH_SHORT).show()

                },
                Response.ErrorListener {
                    Toast.makeText(this,"Something went wrong with the server",Toast.LENGTH_SHORT).show()
                    success=true
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
                if(UserInfo.ISMemic)
                {
                    if(UserInfo.ISMemic)
                    {
                        CommentList!!.add(CommentInfo(0,0,"ta7a","ahmed.jpg",commenttext,"7-10-1998",false))
                        commentadapter!!.notifyDataSetChanged()
                          Toast.makeText(this,"your comment added to the review",Toast.LENGTH_SHORT).show()
                    }


                }
                else{
                    var succes= sendCommetService(commenttext)
                    if(succes)
                    {

                        writercomment.text.clear()

                    }
                    else{
                        //  Toast.makeText(this,"Something went wrong with the seerver",Toast.LENGTH_SHORT).show()
                    }
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

    /**
     * close the keyboard after writing the comment
     *
     */
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
    fun memicReveview()
    {
        var jsonobject:JSONObject=JSONObject()
        when(Creviewdata.reviewid)
        {
            1-> jsonobject=JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":1,\"user_id\":4,\"book_id\":1,\"body\":\"dECsVckfzg\",\"shelf_name\":2,\"rating\":4,\"likes_count\":1,\"comments_count\":0,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"Nour\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"The Bird King\",\"book_image\":\"https://i5.walmartimages.com/asr/8bae6257-b3ed-43ba-b5d4-c55b6479697f_1.c6a36804e0a9cbfd0e408a4b96f8a94e.jpeg?odnHeight=560&odnWidth=560&odnBg=FFFFFF\"}],\"auther\":[{\"author_name\":\"G. Willow Wilson\"}],\"if_liked\":1}")

            2->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":2,\"user_id\":5,\"book_id\":1,\"body\":\"FFewhMCVy6\",\"shelf_name\":2,\"rating\":5,\"likes_count\":0,\"comments_count\":28,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"Salma\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"The Bird King\",\"book_image\":\"https://i5.walmartimages.com/asr/8bae6257-b3ed-43ba-b5d4-c55b6479697f_1.c6a36804e0a9cbfd0e408a4b96f8a94e.jpeg?odnHeight=560&odnWidth=560&odnBg=FFFFFF\"}],\"auther\":[{\"author_name\":\"G. Willow Wilson\"}],\"if_liked\":0}")

            3->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":3,\"user_id\":7,\"book_id\":4,\"body\":\"U6LXG7PWqZ\",\"shelf_name\":1,\"rating\":4,\"likes_count\":2,\"comments_count\":0,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"Mohamed\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"Internment\",\"book_image\":\"https://r.wheelers.co/bk/small/978034/9780349003344.jpg\"}],\"auther\":[{\"author_name\":\"Samira Ahmed\"}],\"if_liked\":1}")

            5->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":5,\"user_id\":3,\"book_id\":2,\"body\":\"yTukzlyHI0\",\"shelf_name\":2,\"rating\":0,\"likes_count\":0,\"comments_count\":2,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"waleed\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"}],\"book\":[{\"book_name\":\"Sherwood\",\"book_image\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\"}],\"auther\":[{\"author_name\":\"Meagan Spooner\"}],\"if_liked\":0}")

            6->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":6,\"user_id\":6,\"book_id\":2,\"body\":\"LLgpRopfoc\",\"shelf_name\":2,\"rating\":5,\"likes_count\":0,\"comments_count\":0,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"TheLeader\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"Sherwood\",\"book_image\":\"https://kbimages1-a.akamaihd.net/6954f4cc-6e4e-46e3-8bc2-81b93f57a723/353/569/90/False/sherwood-7.jpg\"}],\"auther\":[{\"author_name\":\"Meagan Spooner\"}],\"if_liked\":0}")

            7->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":7,\"user_id\":3,\"book_id\":4,\"body\":\"evKmmFuJMu\",\"shelf_name\":1,\"rating\":0,\"likes_count\":0,\"comments_count\":4,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"waleed\",\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"}],\"book\":[{\"book_name\":\"Internment\",\"book_image\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\"}],\"auther\":[{\"author_name\":\"Samira Ahmed\"}],\"if_liked\":0}")

            8->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":8,\"user_id\":7,\"book_id\":1,\"body\":\"xrPr40QXbQ\",\"shelf_name\":0,\"rating\":1,\"likes_count\":1,\"comments_count\":0,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"Mohamed\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"The Bird King\",\"book_image\":\"https://i5.walmartimages.com/asr/8bae6257-b3ed-43ba-b5d4-c55b6479697f_1.c6a36804e0a9cbfd0e408a4b96f8a94e.jpeg?odnHeight=560&odnWidth=560&odnBg=FFFFFF\"}],\"auther\":[{\"author_name\":\"G. Willow Wilson\"}],\"if_liked\":1}")

            9->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":9,\"user_id\":2,\"book_id\":2,\"body\":\"A8rDP8nSMI\",\"shelf_name\":2,\"rating\":4,\"likes_count\":0,\"comments_count\":0,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"ta7a\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"Sherwood\",\"book_image\":\"https://kbimages1-a.akamaihd.net/6954f4cc-6e4e-46e3-8bc2-81b93f57a723/353/569/90/False/sherwood-7.jpg\"}],\"auther\":[{\"author_name\":\"Meagan Spooner\"}],\"if_liked\":0}")

            10->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":10,\"user_id\":3,\"book_id\":3,\"body\":\"i98eV2lxzG\",\"shelf_name\":0,\"rating\":3,\"likes_count\":0,\"comments_count\":0,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"waleed\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"Once & Future\",\"book_image\":\"https://images-na.ssl-images-amazon.com/images/I/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\"}],\"auther\":[{\"author_name\":\"Amy Rose Capetta\"}],\"if_liked\":0}")

            12->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":12,\"user_id\":6,\"book_id\":3,\"body\":\"EdOCBYW1qm\",\"shelf_name\":2,\"rating\":3,\"likes_count\":0,\"comments_count\":0,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"TheLeader\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"Once & Future\",\"book_image\":\"https://images-na.ssl-images-amazon.com/images/I/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\"}],\"auther\":[{\"author_name\":\"Amy Rose Capetta\"}],\"if_liked\":0}")

            13->jsonobject=JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":13,\"user_id\":5,\"book_id\":3,\"body\":\"fCLjnc8tLK\",\"shelf_name\":0,\"rating\":3,\"likes_count\":0,\"comments_count\":0,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"Salma\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"Once & Future\",\"book_image\":\"https://images-na.ssl-images-amazon.com/images/I/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\"}],\"auther\":[{\"author_name\":\"Amy Rose Capetta\"}],\"if_liked\":0}")

            else->jsonobject=JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":13,\"user_id\":5,\"book_id\":3,\"body\":\"fCLjnc8tLK\",\"shelf_name\":0,\"rating\":3,\"likes_count\":0,\"comments_count\":0,\"created_at\":\"2019-05-03 08:40:29\",\"updated_at\":\"2019-05-03 08:40:29\"}],\"user\":[{\"user_name\":\"Salma\",\"image_link\":\"http://ec2-52-90-5-77.compute-1.amazonaws.com/storage/avatars/default.jpg\"}],\"book\":[{\"book_name\":\"Once & Future\",\"book_image\":\"https://images-na.ssl-images-amazon.com/images/I/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\"}],\"auther\":[{\"author_name\":\"Amy Rose Capetta\"}],\"if_liked\":0}")

        }

        feedCreview(jsonobject.getJSONArray("pages").getJSONObject(0))
        feeduser(jsonobject.getJSONArray("user").getJSONObject(0))
        feedbook(jsonobject.getJSONArray("book").getJSONObject(0))
        feedauthor(jsonobject.getJSONArray("auther").getJSONObject(0))
        feedliked(jsonobject.getInt("if_liked"))
        feedReviewDate()



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
