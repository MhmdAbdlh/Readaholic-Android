package com.example.android.readaholic.myshelves
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
import com.example.android.readaholic.books.BookPageActivity
import com.example.android.readaholic.books.BookPageInfo
import com.example.android.readaholic.books.BookReview
import com.example.android.readaholic.books.Cbookdata
import com.example.android.readaholic.contants_and_static_data.Urls
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
        shelvetype=Cbookdata.shelf
        Toast.makeText(this,shelvetype.toString(),Toast.LENGTH_SHORT).show()
        booklist= ArrayList()
        booklistadapter=BookistAdapter()
        booklistui.adapter=booklistadapter
        getShelvsBooks(shelvetype!!)
    }
     inner class BookistAdapter(): BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myview=layoutInflater.inflate(R.layout.bookticket,null)
            var currentbook:BookPageInfo=booklist!![position]
            if(shelvetype==0)
            {
                myview.noratedpartui.visibility=View.GONE
                myview.ratedpartui.visibility=View.VISIBLE
            }
            else
            {
                myview.ratedpartui.visibility=View.GONE
                myview.noratedpartui.visibility=View.VISIBLE
            }
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
    fun feedBooksInfo(jsonarray: JSONArray)
    {
        for( i in 0..jsonarray.length()-1)
        {
            var jsonobject=jsonarray.getJSONObject(i)
            booklist!!.add(BookPageInfo(jsonobject.getString("title"),0,jsonobject.getString("img_url"),0,0.toString(),0.toString(),0,0,0.toFloat()
                    ,jsonobject.getInt("ratings_count"),0.toString(),jsonobject.getInt("ratings_count"),0.toString(),0.toString(),jsonobject.getInt("book_id"),jsonobject.getInt("reviews_count")))
        }
        if(booklist!!.count()==0)
        {

            NoBooksTextUI.visibility=View.VISIBLE
            NoBooksTextUI.text="There is no books in this shelf"


        }

        booklistadapter!!.notifyDataSetChanged()
    }


}
