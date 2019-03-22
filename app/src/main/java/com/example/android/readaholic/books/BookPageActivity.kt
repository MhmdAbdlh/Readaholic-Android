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
import com.example.android.readaholic.URLS
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
            writeareviewbtn.visibility=View.VISIBLE
            rateittext.text="My Rating"
            bookreadbtnui.text="READ"
            bookreadbtnui.setBackgroundResource(R.drawable.btnselectedshape); // From android.graphics.Color
            bookreadbtnui.setTextColor(Color.BLACK)
        }
    }

    /**
     * get the book info from the url as a json file or show error messege in failiar case     *
     */
    fun feedUrlFromApi(BookId:Int)
    {
        val queue = Volley.newRequestQueue(this)
        //var url=URLS.BookPageURL+BookId.toString()
      //  var url = "http://"+"localhost"+":8000/api/books/show?book_id=1";
        var urltry="https://api.myjson.com/bins/howtu"
        val stringRequest = StringRequest(Request.Method.GET, urltry,
                Response.Listener<String> { response ->
                    var jsonresponse=JSONObject(response)
                    feedFromDummey(jsonresponse)

                },
                Response.ErrorListener {
                    var mocresponse=getdummyjson()
                    feedFromDummey(mocresponse)

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
    fun feedFromDummey(jsonobject:JSONObject)
    {
        bookinfo!!.book_title =jsonobject.getString("book_title")
        bookinfo!!.author_name =jsonobject.getString("author_name")
        bookinfo!!.author_id =jsonobject.getString("author_id").toInt()
        bookinfo!!.description =jsonobject.getString("description")
        bookinfo!!.genre =jsonobject.getString("genre")
        bookinfo!!.image_url =jsonobject.getString("image_url")
        bookinfo!!.isbn =jsonobject.getString("isbn").toInt()
        bookinfo!!.average_rating =jsonobject.getString("average_rating").toFloat()
        bookinfo!!.publication_day =jsonobject.getString("publication_day").toInt()
        bookinfo!!.publication_month =jsonobject.getString("publication_month").toInt()
        bookinfo!!.publisher =jsonobject.getString("publisher")
        bookinfo!!.ratings_count =jsonobject.getString("ratings_count").toInt()
        bookinfo!!.small_image_url=jsonobject.getString("small_image_url")
        bookinfo!!.publication_year=jsonobject.getString("publication_year").toInt()
        bookinfo!!.num_pages=jsonobject.getString("num_pages").toInt()
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
                " "+bookinfo!!.publication_day+" , "+bookinfo!!.publication_year+" ISBN13 "+bookinfo!!.isbn
        seeallreviewstxtui.text=bookinfo!!.reviewscount.toString()+" other community reviews"
     }

    /**
     * this will be called untill we call the backend(Mock Serviece)
     *
     */
    fun getdummyjson():JSONObject
    {

        return JSONObject("{\"book_title\":\"American Duchess\",\"isbn\":\"0062748343\",\"image_url\":\"https://images.gr-assets.com/books/1538445346l/36300673.jpg\",\"small_image_url\":\"https://images.gr-assets.com/books/1538445346l/36300673.jpg\",\"num_pages\":\"1000\",\"publisher\":\"dummyMan\",\"publication_day\":13,\"publication_year\":1932,\"publication_month\":10,\"average_rating\":3.532,\"reviews_count\":2,\"ratings_count\":1,\"description\":\"Before Meghan and Harry, another American ‘princess’ captured the hand of an English aristocrat. Now, Karen Harper tells the tale of Consuelo Vanderbilt, her “The Wedding of the Century” to the Duke of Marlborough, and her quest to find meaning behind “the glitter and the gold.\",\"author_id\":1,\"author_name\":\" Karen Harper\",\"genre\":\"action\"}")

    }

}
