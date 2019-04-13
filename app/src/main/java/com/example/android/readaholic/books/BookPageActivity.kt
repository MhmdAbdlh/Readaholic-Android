package com.example.android.readaholic.books
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.RequiresPermission
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.android.readaholic.R
import com.example.android.readaholic.contants_and_static_data.Urls
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_page.*
import org.json.JSONObject
import com.android.volley.Request
import com.android.volley.Response
import com.example.android.readaholic.Editreview
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_book_reviews.*


/**
 * This Activity is for showing book information
 *
 */
class BookPageActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener {
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)
        var spinneradapter: ArrayAdapter<CharSequence> =ArrayAdapter.createFromResource(this, R.array.Shelves,android.R.layout.simple_spinner_item)
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activitybook_sheleve_spinner1.adapter=spinneradapter
        activitybook_sheleve_spinner1.onItemSelectedListener=this
        /////////////////////////////
        bookinfo= BookPageInfo()
        var intent:Intent= Intent()
         Cbookdata!!.bookid=intent.getIntExtra("BookId",1)
        bookreview= ArrayList()
        feedBookInfoFromApi(Cbookdata.bookid)

        seeallreviewstxtui.setOnClickListener {
            Cbookdata.author_name=bookinfo!!.author_name
            Cbookdata.book_title=bookinfo!!.book_title
            Cbookdata.image_url=bookinfo!!.image_url
            var intent=Intent(this, BookReviewsActivity::class.java)
            startActivity(intent)
        }
        getReviewforABookforAUser()
    }

    /**
     * Set the shelf type
     *
     */
    fun setshelve()
    {
        when( bookreadbtnui.text) {
            "READ" -> Cbookdata.shelf=0
            "CURRENTLY READING"->   Cbookdata.shelf=1
            "WANT TO READ" ->   Cbookdata.shelf=2
        }
        Toast.makeText(this,Cbookdata.shelf.toString(),Toast.LENGTH_SHORT).show()
    }

    fun getshelve()
    {
        when( Cbookdata.shelf) {
            0 ->bookreadbtnui.text ="READ"
            1-> bookreadbtnui.text = "CURRENTLY READING"
            2-> bookreadbtnui.text  = "WANT TO READ"
        }
        Toast.makeText(this,Cbookdata.shelf.toString(),Toast.LENGTH_SHORT).show()
    }

    /**
     *
     *
     * @param view
     */
    fun refreshBookPage(view: View)
    {
        feedBookInfoFromApi(Cbookdata.bookid)
    }

    /**
     * Got to write review activity
     *
     * @param view
     */

    fun writeReview(view:View)
    {
        var intent=Intent(this, Editreview::class.java)
        setshelve()
        startActivity(intent)
    }

    /**
     * get a the review for a user for a book
     *
     */
    fun getReviewforABookforAUser()
    {
        val queue = Volley.newRequestQueue(this)
        var url = Urls.getShowReviewForBookForUser(Cbookdata.bookid.toString())
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    var jsonresponse= JSONObject(response)
                    myReview(jsonresponse)

                },
                Response.ErrorListener {

                }
        )
        queue.add(stringRequest)
    }

    /**
     * deleting my review
     *
     * @param view
     */

    fun deletemyreview(view: View)
    {
        val queue = Volley.newRequestQueue(this)
        var url = Urls. deleteMyReview(Cbookdata.reviewid.toString())
        val stringRequest = StringRequest(Request.Method.DELETE, url,
                Response.Listener<String> { response ->
                    var jsonresponse= JSONObject(response)
                    if(jsonresponse.getString("status")=="true")
                    {
                     Toast.makeText(this,"Your review has been deleted",Toast.LENGTH_SHORT).show()
                        finish();
                        startActivity(getIntent());
                    }
                    else{
                        Toast.makeText(this,jsonresponse.getString("Message"),Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {



                }
        )
        queue.add(stringRequest)




    }

    /**
     * take my review data from json and feed the UI
     *
     * @param json
     */

    fun myReview(json:JSONObject)
    {
        var status=json.getString("status")
        if(status=="success")
        {
            myreviewlayout.visibility=View.VISIBLE
           var myreview=json.getJSONArray("pages").getJSONObject(0)
            myreviewbody.text=myreview.getString("body")
            myrating.rating=myreview.getInt("rating").toFloat()
            writeareviewbtn.text="Edit Your  Review"
            Cbookdata.shelf=myreview.getInt("shelf_name")
            Cbookdata.reviewid=myreview.getInt("id")
            getshelve()
        }

    }



    /**
     * get the book info from the url as a json file or show error messege in failiar case     *
     */
    fun feedBookInfoFromApi(BookId:Int)
    {
        val queue = Volley.newRequestQueue(this)
        var url = Urls.getShowBook(Cbookdata.bookid.toString())
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    whenconnection.visibility=View.VISIBLE
                    noconnection.visibility=View.GONE
                    var jsonresponse= JSONObject(response)
                    feedFromApi(jsonresponse)

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
    fun feedFromApi(jsonresponce:JSONObject)
    {

        var jsonobject=jsonresponce.getJSONArray("pages").getJSONObject(0)
        bookinfo!!.book_title =jsonobject.getString("title")
        bookinfo!!.author_name =jsonobject.getString("author_name")
        bookinfo!!.author_id =jsonobject.getString("author_id").toInt()
        bookinfo!!.description =jsonobject.getString("description")
        bookinfo!!.image_url =jsonobject.getString("img_url")
        bookinfo!!.average_rating=jsonobject.getString("ratings_avg").toFloat()
        bookinfo!!.isbn =jsonobject.getInt("isbn")
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
        seeallreviewstxtui.text=bookinfo!!.reviewscount.toString()+" community reviews"
     }

}
