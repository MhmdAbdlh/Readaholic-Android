package com.example.android.readaholic.books
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
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
import com.example.android.readaholic.contants_and_static_data.UserInfo
import com.example.android.readaholic.myshelves.ShelvesFragment

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

        Toast.makeText(this,"position number"+position.toString(),Toast.LENGTH_SHORT).show()
        if(position!=0&&position!=4)
        {
            bookreadbtnui.text=shelftype
            bookreadbtnui.setBackgroundResource(R.drawable.btnselectedshape); // From android.graphics.Color
            bookreadbtnui.setTextColor(Color.BLACK)
            setshelve()
            addBookShelf(Cbookdata.bookid,Cbookdata.shelf)
        }
        else if(position==4){
            deleteBookShelf(Cbookdata.bookid,Cbookdata.shelf)
        }
    }

    fun addBookShelf(bookid:Int,shelf:Int)
    {
        val queue = Volley.newRequestQueue(this)
        var url = Urls.addbooktoshelf(shelf.toString(),bookid.toString())
        val stringRequest = StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    var jsonresponse= JSONObject(response)
                    Toast.makeText(this,jsonresponse.getString("message"),Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {

                }
        )
        queue.add(stringRequest)
    }

    /**
     * delete a book on a shelf
     *
     * @param bookid
     * @param shelf
     */
    fun deleteBookShelf(bookid:Int,shelf:Int)
    {
        val queue = Volley.newRequestQueue(this)
        var url = Urls.deletebooktoshelf(shelf.toString(),bookid.toString())
        val stringRequest = StringRequest(Request.Method.DELETE, url,
                Response.Listener<String> { response ->
                    var jsonresponse= JSONObject(response)
                    Toast.makeText(this,jsonresponse.getString("message"),Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {

                }
        )
        queue.add(stringRequest)
    }

    override fun onRestart() {
        super.onRestart()
        getReviewforABookforAUser()
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
        bookreview= ArrayList()
        if(UserInfo.mIsGuest)
        {
            bookshelflayout.visibility=View.GONE
            writeareviewbtn.visibility=View.GONE
        }

        seeallreviewstxtui.setOnClickListener {
            Cbookdata.author_name=bookinfo!!.author_name
            Cbookdata.book_title=bookinfo!!.book_title
            Cbookdata.image_url=bookinfo!!.image_url
            var intent=Intent(this, BookReviewsActivity::class.java)
            startActivity(intent)
        }
        if(!UserInfo.ISMemic)
        {
            feedBookInfoFromApi()
            whenconnection.visibility=View.GONE
            getReviewforABookforAUser()
            getshelfname()
        }else
        {
            memic(Cbookdata.bookid)
        }

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
    }

    /**
     *
     *
     * @param view
     */
    fun refreshBookPage(view: View)
    {
        feedBookInfoFromApi()
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
                        if(jsonresponse.has("Message"))
                        {
                            Toast.makeText(this,jsonresponse.getString("Message"),Toast.LENGTH_SHORT).show()
                        }
                        else if(jsonresponse.has("errors"))
                        {

                            Toast.makeText(this,jsonresponse.getString("errors"),Toast.LENGTH_SHORT).show()
                        }

                    }
                },
                Response.ErrorListener {



                }
        )
        queue.add(stringRequest)

    }

    /**
     * this function to get the shelf name
     */

    fun getshelfname()
    {
        val queue = Volley.newRequestQueue(this)
        var url = Urls.getshelfonbook(Cbookdata.bookid.toString())
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    var jsonresponse= JSONObject(response)
                    if (jsonresponse.getString("status")=="true")
                    {
                        if(jsonresponse.has("ShelfName"))
                        {
                            Cbookdata.shelf=jsonresponse.getInt("ShelfName")
                            bookreadbtnui.setBackgroundResource(R.drawable.btnselectedshape); // From android.graphics.Color
                            bookreadbtnui.setTextColor(Color.BLACK)
                            getshelve()
                        }


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
        if(status=="success"&&!UserInfo.mIsGuest)
        {
            myreviewlayout.visibility=View.VISIBLE
            var myreview=json.getJSONArray("pages").getJSONObject(0)
            if(myreview.getString("body")=="")
            {
                myreviewbody.text="NO BODY FOR THE REVIEW"
            }
            else{
                myreviewbody.text=myreview.getString("body")
            }
            if(myreview.getString("rating").toFloat()<=0)
            {
                myrating.rating=1.0f
            }
            else{
                myrating.rating=myreview.getInt("rating").toFloat()
            }


            if(myreview.getInt("id")<0)
            {
                Cbookdata.reviewid=1

            }else{
                Cbookdata.reviewid=myreview.getInt("id")
            }

            writeareviewbtn.text="Edit Your  Review"
            // Cbookdata.shelf=myreview.getInt("shelf_name")
            //  bookreadbtnui.setBackgroundResource(R.drawable.btnselectedshape); // From android.graphics.Color
            //  bookreadbtnui.setTextColor(Color.BLACK)
            getshelve()

        }

    }

    /**
     * get the book info from the url as a json file or show error messege in failiar case     *
     */
    fun feedBookInfoFromApi()
    {
        val progressBar = findViewById(R.id.bookpage_loading_progbar) as ProgressBar
        progressBar.setVisibility(View.VISIBLE)

        val queue = Volley.newRequestQueue(this)
        var url = Urls.getShowBook(Cbookdata.bookid.toString())
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    progressBar.visibility=View.GONE
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


    /**
     * Memic the Books
     *
     * @param bookid
     */
    fun memic(bookid:Int)
    {
        var jsonresponse:JSONObject?=null
        when(bookid)
        {
            2->jsonresponse= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":2,\"author_name\":\"Meagan Spooner\",\"title\":\"Sherwood\",\"isbn\":9780062422330,\"img_url\":\"https://kbimages1-a.akamaihd.net/6954f4cc-6e4e-46e3-8bc2-81b93f57a723/353/569/90/False/sherwood-7.jpg\",\"publication_date\":\"2014-01-31\",\"publisher\":\"6tLlTx77mp\",\"language\":\"English\",\"description\":\"Robin of Locksley is dead.\n" +
                    "            Maid Marian doesn’t know how she’ll go on, but the people of Locksley town, persecuted by the Sheriff of Nottingham, need a protector. And the dreadful Guy of Gisborne, the Sheriff’s right hand, wishes to step into Robin’s shoes as Lord of Locksley and Marian’s fiancé.\n" +
                    "            Who is there to stop them?\n" +
                    "            Marian never meant to tread in Robin’s footsteps—never intended to stand as a beacon of hope to those awaiting his triumphant return. But with a sweep of his green cloak and the flash of her sword, Marian makes the choice to become her own hero: Robin Hood. \",\"reviews_count\":4,\"ratings_count\":4,\"ratings_avg\":2.25,\"author_id\":2,\"pages_no\":0,\"created_at\":\"2019-05-03 08:40:28\",\"updated_at\":\"2019-05-03 08:40:28\",\"genre\":\"Young Adult,Retellings,Fantacy\"}]}")

            3->jsonresponse= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":3,\"author_name\":\"Amy Rose Capetta\",\"title\":\"Once & Future\",\"isbn\":9780316449274,\"img_url\":\"https://images-na.ssl-images-amazon.com/images/I/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\",\"publication_date\":\"2014-01-31\",\"publisher\":\"uqM9NUq6Hw\",\"language\":\"English\",\"description\":\"I’ve been chased my whole life. As a fugitive refugee in the territory controlled by the tyrannical Mercer corporation, I’ve always had to hide who I am. Until I found Excalibur.\n" +
                    "            Now I’m done hiding.\n" +
                    "            My name is Ari Helix. I have a magic sword, a cranky wizard, and a revolution to start.   \n" +
                    "            When Ari crash-lands on Old Earth and pulls a magic sword from its ancient resting place, she is revealed to be the newest reincarnation of King Arthur. Then she meets Merlin, who has aged backward over the centuries into a teenager, and together they must break the curse that keeps Arthur coming back. Their quest? Defeat the cruel, oppressive government and bring peace and equality to all humankind.\",\"reviews_count\":3,\"ratings_count\":3,\"ratings_avg\":3,\"author_id\":3,\"pages_no\":0,\"created_at\":\"2019-05-03 08:40:28\",\"updated_at\":\"2019-05-03 08:40:28\",\"genre\":\"Young Adult,Contemporary,Fiction,\"}]}")

            1->jsonresponse= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":1,\"author_name\":\"G. Willow Wilson\",\"title\":\"The Bird King\",\"isbn\":9780802129031,\"img_url\":\"https://i5.walmartimages.com/asr/8bae6257-b3ed-43ba-b5d4-c55b6479697f_1.c6a36804e0a9cbfd0e408a4b96f8a94e.jpeg?odnHeight=560&odnWidth=560&odnBg=FFFFFF\",\"publication_date\":\"2014-01-31\",\"publisher\":\"wiHrIPJAvt\",\"language\":\"English\",\"description\":\"New from the award-winning author of Alif the Unseen and writer of the Ms. Marvel series, G. Willow Wilson\n" +
                    "             Set in 1491 during the reign of the last sultanate in the Iberian peninsula, \n" +
                    "             The Bird King is the story of Fatima, the only remaining Circassian concubine to the sultan, and her dearest friend Hassan, the palace mapmaker. \n" +
                    "              Hassan has a secret--he can draw maps of places he's never seen and bend the shape of reality.\n" +
                    "              When representatives of the newly formed Spanish monarchy arrive to negotiate the sultan's surrender, Fatima befriends one of the women, not realizing that she will see Hassan's gift as sorcery and a threat to Christian Spanish rule. With their freedoms at stake,\n" +
                    "               what will Fatima risk to save Hassan and escape the palace walls? As Fatima and Hassan traverse Spain with the help of a clever jinn to find safety, The Bird King asks us to consider what love is and the price of freedom at a time when the West and the Muslim world were not yet separate. \",\"reviews_count\":0,\"ratings_count\":0,\"ratings_avg\":0,\"author_id\":1,\"pages_no\":0,\"created_at\":\"2019-05-03 08:40:28\",\"updated_at\":\"2019-05-03 08:40:28\",\"genre\":\"Young Adult,Historical,Fiction,Adult\"}]}")

            4->jsonresponse= JSONObject("{\"status\":\"success\",\"pages\":[{\"id\":4,\"author_name\":\"Samira Ahmed\",\"title\":\"Internment\",\"isbn\":9780349003344,\"img_url\":\"https://r.wheelers.co/bk/small/978034/9780349003344.jpg\",\"publication_date\":\"2014-01-31\",\"publisher\":\"FWom1tHEid\",\"language\":\"English\",\"description\":\"Rebellions are built on hope.\n" +
                    "            Set in a horrifying near-future United States, seventeen-year-old Layla Amin and her parents are forced into an internment camp for Muslim American citizens.\n" +
                    "            With the help of newly made friends also trapped within the internment camp, her boyfriend on the outside, and an unexpected alliance, Layla begins a journey to fight for freedom, leading a revolution against the internment camp's Director and his guards.\n" +
                    "            Heart-racing and emotional, Internment challenges readers to fight complicit silence that exists in our society today.\",\"reviews_count\":1,\"ratings_count\":1,\"ratings_avg\":2.67,\"author_id\":4,\"pages_no\":0,\"created_at\":\"2019-05-03 08:40:28\",\"updated_at\":\"2019-05-03 08:40:28\",\"genre\":\"Young Adult,Contemporary,Fiction,Science Fiction > Dystopia\"}]}")
        }

        //
        feedFromApi(jsonresponse!!)

    }

}

