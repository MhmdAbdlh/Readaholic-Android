package com.example.android.readaholic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.android.readaholic.R.id.tiltetxtui
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import kotlinx.android.synthetic.main.activity_book_page.*
import org.json.JSONObject

class BookPageActivity : AppCompatActivity() {
var xmlString:String?=null
var bookinfo:BookPageInfo?=null
var jsonString:JSONObject?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)
        bookinfo=BookPageInfo()
        feedUrlFromApi()
        bookreadbtnui.setOnClickListener {
            Toast.makeText(this,"Am i A joke to you ??",Toast.LENGTH_SHORT).show()
        }
        downarowui.setOnClickListener {
            Toast.makeText(this,"Am i A joke to you ??>>>>",Toast.LENGTH_SHORT).show()
        }
    }

    fun feedUrlFromApi()
    {
        val queue = Volley.newRequestQueue(this)
        val url = "https://www.goodreads.com/search/index.xml?key=ER4R6YnUGeoLQICR10aKQ&q=0439554934&search=ISN"

        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    var xmltojson= XmlToJson.Builder(response).build()
                    var jsonob= JSONObject(xmltojson.toString())
                    var jsnoob1=jsonob.getJSONObject("GoodreadsResponse").getJSONObject("search")
                            .getJSONObject("results").getJSONObject("work").getJSONObject("best_book").getString("title")
                    tiltetxtui.text = jsnoob1.toString()
                },
                Response.ErrorListener {  jsonString = JSONObject("{\n" +
                        " \"book_title\": \"Would you die for me?\",\n" +
                        " \"isbn\": \"1234\",\n" +
                        " \"image_url\": \"lookdown.jpg\",\n" +
                        " \"small_image_url\": \"xyz.com\\/images\\/uvw.jpg\",\n" +
                        " \"num_pages\": \"1000\",\n" +
                        " \"publisher\": \"dummyMan\",\n" +
                        " \"publication_day\": 13,\n" +
                        " \"publication_year\": 1932,\n" +
                        " \"publication_month\": 10,\n" +
                        " \"average_rating\": 3.532,\n" +
                        " \"ratings_count\": 1,\n" +
                        " \"description\": \"dummyasfhaDJKFHJKADGFJKGADSKHFGADJSKHFJKAhsfjhadjkfhsdjkhf\",\n" +
                        " \"author_id\": 1,\n" +
                        " \"author_name\": \"author\",\n" +
                        " \"genre\": \"action\"\n" +
                        "}\n")

                   feedFromDummey(jsonString!!)
                })
        queue.add(stringRequest)

    }
    fun feedFromDummey(dummyjson:JSONObject)
    {
        bookinfo!!.book_title =dummyjson.getString("book_title")
        bookinfo!!.author_name =dummyjson.getString("author_name")
        bookinfo!!.author_id =dummyjson.getString("author_id").toInt()
        bookinfo!!.description =dummyjson.getString("description")
        bookinfo!!.genre =dummyjson.getString("genre")
        bookinfo!!.image_url =dummyjson.getString("image_url")
        bookinfo!!.isbn =dummyjson.getString("isbn").toInt()
        bookinfo!!.average_rating =dummyjson.getString("average_rating").toFloat()
        bookinfo!!.publication_day =dummyjson.getString("publication_day").toInt()
        bookinfo!!.publication_month =dummyjson.getString("publication_month").toInt()
        bookinfo!!.publisher =dummyjson.getString("publisher")
        bookinfo!!.ratings_count =dummyjson.getString("ratings_count").toInt()
        bookinfo!!.small_image_url=dummyjson.getString("small_image_url")
        bookinfo!!.publication_year=dummyjson.getString("publication_year").toInt()
        bookinfo!!.num_pages=dummyjson.getString("num_pages").toInt()
        feedBookUi()
    }
    fun feedBookUi()
    {
        tiltetxtui.text= bookinfo!!.book_title
        authornametxtui.text= bookinfo!!.author_name
        ratingavgtxtui.text= bookinfo!!.average_rating.toString()
        ratingui.rating=bookinfo!!.average_rating
        bookdesctxtui.text=bookinfo!!.description
        boojsideinfotxtui.text=bookinfo!!.num_pages.toString()+" . First published "+bookinfo!!.publication_month+
                " "+bookinfo!!.publication_day+" , "+bookinfo!!.publication_year+" ISBN13 "+bookinfo!!.isbn
     }
}
