package com.example.android.readaholic.books

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.android.readaholic.R
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.android.readaholic.contants_and_static_data.Urls
import com.example.android.readaholic.contants_and_static_data.UserInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_page.*
import org.json.JSONObject

/**
 * This Activity is for showing book information
 *
 */
class BookPageActivity : AppCompatActivity() ,AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    /**
     * Handling the book btn ui between cuurently reading ,read and want to read
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var shelftype:String=parent!!.getItemAtPosition(position).toString()
        bookreadbtnui.text=shelftype
        if(position!=0)
        {
            bookreadbtnui.setBackgroundResource(R.drawable.btnselectedshape); // From android.graphics.Color
            bookreadbtnui.setTextColor(Color.BLACK)
        }
    }
    var bookinfo: BookPageInfo?=null
    var bookreview:ArrayList<BookReview>?=null
    var mybookrating:Int?=null
    var shelvetype:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)
        var spinneradapter:ArrayAdapter<CharSequence> =ArrayAdapter.createFromResource(this, R.array.Shelves,android.R.layout.simple_spinner_item)
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activitybook_sheleve_spinner.adapter=spinneradapter
        activitybook_sheleve_spinner.onItemSelectedListener=this
        /////////////////////////////
        bookinfo= BookPageInfo()
        var intent:Intent= Intent()
        bookinfo!!.bookid=intent.getIntExtra("BookId",0)
        seeallreviewstxtui.setOnClickListener {
            Cbookdata.author_name=bookinfo!!.author_name
            Cbookdata.book_title=bookinfo!!.book_title
            Cbookdata.bookid=bookinfo!!.bookid
            Cbookdata.image_url=bookinfo!!.image_url
            Cbookdata.bookid=1
            var intent=Intent(this, BookReviewsActivity::class.java)
            startActivity(intent)
        }
        bookreview= ArrayList()
         var succes=feedUrlFromApi(bookinfo!!.bookid)

        rateittext.setOnClickListener {
            writeareviewbtn.visibility=View.VISIBLE
        }
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            mybookrating=ratingBar.rating.toInt()
            setshelve()
            writeareviewbtn.visibility=View.VISIBLE
            rateittext.text="My Rating"
            bookreadbtnui.text="READ"
            bookreadbtnui.setBackgroundResource(R.drawable.btnselectedshape); // From android.graphics.Color
            bookreadbtnui.setTextColor(Color.BLACK)
            animate()
        }

    }
    fun setshelve()
    {
        when( bookreadbtnui.text) {
            "READ" -> shelvetype=0
            "Currently reading"->  shelvetype=1
            "Want to read" ->  shelvetype=2
        }
    }
    fun refreshBookPage(view: View)
    {
        feedUrlFromApi(bookinfo!!.bookid)
    }
    fun writeReview(view:View)
    {




    }
    fun animate()
    {

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(writeareviewbtn);
    }
    /**
     * get the book info from the url as a json file or show error messege in failiar case     *
     */
    fun feedUrlFromApi(BookId:Int)
    {
        val queue = Volley.newRequestQueue(this)
        var url =Urls.getShowBook("1")

        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    whenconnection.visibility=View.VISIBLE
                    noconnection.visibility=View.GONE
                    var jsonresponse=JSONObject(response)
                    feedFromDummey(jsonresponse)

                },
                Response.ErrorListener {

                    whenconnection.visibility=View.GONE
                    noconnection.visibility=View.VISIBLE

                }
        )

        queue.add(stringRequest)

    }

    /**
     * get the book image from the url
     *
     */
    fun importImage()
    {
       Picasso.get().load(bookinfo!!.image_url).into(bookimageui)
    }

    /**
     * fitch the bookobject info from the jsonobject
     * @param jsonobject
     */
    fun feedFromDummey(jsonresponce:JSONObject)
    {

        var jsonobject=jsonresponce.getJSONArray("pages").getJSONObject(0)
        bookinfo!!.book_title =jsonobject.getString("title")
        bookinfo!!.author_name =jsonobject.getString("author_name")
        bookinfo!!.author_id =jsonobject.getString("author_id").toInt()
        bookinfo!!.description =jsonobject.getString("description")
        bookinfo!!.image_url =jsonobject.getString("img_url")
        bookinfo!!.average_rating=jsonobject.getString("ratings_avg").toFloat()
        bookinfo!!.isbn =jsonobject.getString("isbn").toInt()
        bookinfo!!.publication_date =jsonobject.getString("publication_date")
        bookinfo!!.publisher =jsonobject.getString("publisher")
        bookinfo!!.ratings_count =jsonobject.getString("ratings_count").toInt()
        bookinfo!!.reviewscount=jsonobject.getString("reviews_count").toInt()
        feedBookUi()
        importImage()
    }
    /**
     * fetch book info into the UI
     *
      */
    fun feedBookUi()
    {
        tiltetxtui.text= bookinfo!!.book_title
        authornametxtui.text= bookinfo!!.author_name
        ratinginfotxtui.text= bookinfo!!.average_rating.toString()+"     "+bookinfo!!.ratings_count.toString()+" ratings "
        ratingui.rating=bookinfo!!.average_rating
        bookdesctxtui.text=bookinfo!!.description
        boojsideinfotxtui.text="number of pages :"+bookinfo!!.num_pages.toString()+" . First published "+bookinfo!!.publication_month+
                " "+bookinfo!!.publication_date+" , "+bookinfo!!.publication_year+" ISBN13 "+bookinfo!!.isbn
        seeallreviewstxtui.text=bookinfo!!.reviewscount.toString()+" other community reviews"
     }




}
