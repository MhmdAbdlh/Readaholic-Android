package com.example.android.readaholic

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.android.readaholic.books.Creviewdata.reviewid
import com.example.android.readaholic.contants_and_static_data.Urls
import kotlinx.android.synthetic.main.activity_book_page.*
import kotlinx.android.synthetic.main.activity_editreview.*
import org.json.JSONObject

class Editreview : AppCompatActivity() {
    var reviewid:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editreview)
        var intent=Intent()
         reviewid=intent.getIntExtra("REVIEWID",0)
    }


    fun saveReview(view:View)
    {
        var rating=editratingbar.rating.toInt()
        var body=reviewbody.text.toString()
        editReview(rating,body)

    }

    fun editReview(rating:Int,body:String)
    {
        val queue = Volley.newRequestQueue(this)
        var url = Urls.editreview(reviewid!!.toString(),rating.toString(),body)

        val stringRequest = StringRequest(Request.Method.PUT, url,
                Response.Listener<String> { response ->

                    var jsonresponse= JSONObject(response)


                },
                Response.ErrorListener {


                }
        )

        queue.add(stringRequest)




    }
}
