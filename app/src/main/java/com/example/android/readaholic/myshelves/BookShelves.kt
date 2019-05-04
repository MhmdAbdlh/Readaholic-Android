package com.example.android.readaholic.myshelves
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
import com.example.android.readaholic.books.BookPageActivity
import com.example.android.readaholic.books.BookPageInfo
import com.example.android.readaholic.books.BookReview
import com.example.android.readaholic.books.Cbookdata
import com.example.android.readaholic.contants_and_static_data.Urls
import com.example.android.readaholic.contants_and_static_data.UserInfo
import com.example.android.readaholic.profile_and_profile_settings.Profile
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_shelves.*
import kotlinx.android.synthetic.main.bookticket.view.*
import org.json.JSONArray
import org.json.JSONObject

class BookShelves : AppCompatActivity() {
protected var booklist:ArrayList<BookPageInfo>?=null
protected var booklistadapter:BookistAdapter?=null
protected var shelvetype:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_shelves)
        val toolbar = findViewById<View>(R.id.Main_toolbarr) as Toolbar
        setSupportActionBar(toolbar)
        shelvetype=Cbookdata.shelf
        booklist= ArrayList()
        booklistadapter=BookistAdapter()
        booklistui.adapter=booklistadapter
        if(UserInfo.ISMemic)
        {
            memicShelves()
        }
        else {
            getShelvsBooks(shelvetype!!)
        }
    }
     inner class BookistAdapter(): BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myview=layoutInflater.inflate(R.layout.bookticket,null)
            var currentbook:BookPageInfo=booklist!![position]
            myview.bookname.text=currentbook.book_title
            myview.authorname.text=currentbook.author_name
            myview.numbrtofratings.text=currentbook.ratings_count.toString()
            Picasso.get().load(currentbook.image_url).into(myview.bookimage)
            myview.bookimage.setOnClickListener {
                Cbookdata.bookid=currentbook.bookid
                var intent= Intent(baseContext, BookPageActivity::class.java)
                startActivity(intent)
            }
            return myview
        }

        override fun getItem(position: Int): Any {
            return  booklist!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return booklist!!.size
        }

    }

    /**
     * get all books information from API
     *
     *
     * @param shelfname
     */

    fun getShelvsBooks(shelftype:Int)
    {
        val queue = Volley.newRequestQueue(this)
        var url = Urls.getselfbooks(shelftype.toString())
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    var jsonresponse= JSONObject(response)
                    var books=jsonresponse.getJSONArray("pages")
                    feedBooksInfo(books)
                },
                Response.ErrorListener {

                }
        )
        queue.add(stringRequest)
    }

    /**
     * feed the jsondata into the books
     *
     * @param jsonarray
     */
    fun feedBooksInfo(jsonarray: JSONArray) {
        for (i in 0..jsonarray.length() - 1) {
            var jsonobject = jsonarray.getJSONObject(i)
            booklist!!.add(BookPageInfo(jsonobject.getString("title"), 0, jsonobject.getString("img_url"), 0, 0.toString(), 0.toString(), 0, 0, 0.toFloat()
                    , jsonobject.getInt("ratings_count"), 0.toString(), jsonobject.getInt("ratings_count"), 0.toString(), 0.toString(), jsonobject.getInt("book_id"), jsonobject.getInt("reviews_count")))
        }
        booklistadapter!!.notifyDataSetChanged()

        if(booklist!!.size==0)
        {
            NoBooksTextUI.visibility=View.VISIBLE
            NoBooksTextUI.text="You don't have any books on this shelf yet!!"


        }
    }

    fun memicShelves()
    {
        var jsonobject:JSONObject=JSONObject()
        when(shelvetype!!)
        {
            1->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"book_id\":4,\"title\":\"Internment\",\"id\":4,\"isbn\":9780349003344,\"img_url\":\"https://r.wheelers.co/bk/small/978034/9780349003344.jpg\",\"reviews_count\":1,\"ratings_count\":1,\"author_id\":4},{\"book_id\":1,\"title\":\"The Bird King\",\"id\":1,\"isbn\":9780802129031,\"img_url\":\"https://i5.walmartimages.com/asr/8bae6257-b3ed-43ba-b5d4-c55b6479697f_1.c6a36804e0a9cbfd0e408a4b96f8a94e.jpeg?odnHeight=560&odnWidth=560&odnBg=FFFFFF\",\"reviews_count\":0,\"ratings_count\":0,\"author_id\":1}]}")
            2->jsonobject= JSONObject("{\"status\":\"success\",\"pages\":[{\"book_id\":2,\"title\":\"Sherwood\",\"id\":2,\"isbn\":9780062422330,\"img_url\":\"https://kbimages1-a.akamaihd.net/6954f4cc-6e4e-46e3-8bc2-81b93f57a723/353/569/90/False/sherwood-7.jpg\",\"reviews_count\":18,\"ratings_count\":18,\"author_id\":2}]}")
            0->jsonobject= JSONObject("{\"status\":\"failed, no returned results for the input\",\"pages\":[]}")
        }
        feedBooksInfo(jsonobject.getJSONArray("pages"))
    }


}
