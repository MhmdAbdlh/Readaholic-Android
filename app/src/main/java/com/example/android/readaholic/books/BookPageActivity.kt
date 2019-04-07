package com.example.android.readaholic.books

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.android.readaholic.R
import android.widget.BaseAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.android.readaholic.URLS
import com.example.android.readaholic.contants_and_static_data.Urls
import com.example.android.readaholic.contants_and_static_data.UserInfo
import com.example.android.readaholic.profile_and_profile_settings.books
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_page.*
import kotlinx.android.synthetic.main.bookreview.view.*
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
        var SS:String=parent!!.getItemAtPosition(position).toString()
        bookreadbtnui.text=SS
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

        bookinfo= BookPageInfo()
        //this to get the passed book id from the other activities
        var intent:Intent= Intent()
        bookinfo!!.bookid=intent.getIntExtra("BookId",0)
        ////
        //this code to  hundle the button spinner thing (want to read /read /currently reading)
        var spinneradapter:ArrayAdapter<CharSequence> =ArrayAdapter.createFromResource(this, R.array.Shelves,android.R.layout.simple_spinner_item)
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activitybook_sheleve_spinner.adapter=spinneradapter
        activitybook_sheleve_spinner.onItemSelectedListener=this
        ////
        //Here to go to the list of reviews and send important info to them
        seeallreviewstxtui.setOnClickListener {

            var intent=Intent(this, BookReviewsActivity::class.java)
            Cbookdata.author_name=bookinfo!!.author_name
            Cbookdata.book_title=bookinfo!!.book_title
            Cbookdata.bookid=bookinfo!!.bookid
            Cbookdata.image_url=bookinfo!!.small_image_url
            startActivity(intent)
        }

        bookreview= ArrayList()
        feedUrlFromApi(bookinfo!!.bookid)
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
            "Wnat to read" ->  shelvetype=2
        }
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
        //var url=URLS.BookPageURL+BookId.toString()
        var url = Urls.ROOT+"/api/books/show?book_id=2&token=" + UserInfo.sToken + "&type="+ UserInfo.sTokenType;
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this,UserInfo.sToken,Toast.LENGTH_LONG).show()
                    Toast.makeText(this,response,Toast.LENGTH_LONG).show()
                    var jsonresponse=JSONObject(response)
                    feedFromDummey(jsonresponse)
                },
                Response.ErrorListener {
                 //   var mocresponse=getdummyjson()
                   // feedFromDummey(mocresponse)

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

        var jsonobject=jsonresponce.getJSONObject("pages")
        bookinfo!!.book_title =jsonobject.getString("title")
//        bookinfo!!.author_name =jsonobject.getString("author_name")
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
