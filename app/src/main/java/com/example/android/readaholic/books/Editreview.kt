package com.example.android.readaholic.books

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.android.readaholic.R
import com.example.android.readaholic.contants_and_static_data.Urls
import kotlinx.android.synthetic.main.activity_editreview.*
import org.json.JSONObject

class Editreview : AppCompatActivity() {
    var reviewid:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editreview)
        val toolbar = findViewById<View>(R.id.Main_toolbarr) as Toolbar
        setSupportActionBar(toolbar)
    }


    fun sendReview(view:View)
    {
        var reviewtext=writerreview.text.toString()
        if(reviewtext=="")
        {
            Toast.makeText(this,"Please write something first", Toast.LENGTH_SHORT).show()

        }
        else if(raitebookstarts.rating.toInt()==0)
        {
            Toast.makeText(this,"You have to rate at at least with one star", Toast.LENGTH_SHORT).show()
        }
        else{

            Cbookdata.bookrating=raitebookstarts.rating.toInt()
            sendReviewService(Cbookdata.bookid,reviewtext, Cbookdata.bookrating, Cbookdata.shelf.toString())
        }

    }

    /**
     * server communication to post the comment
     *
     * @param commentbody
     */
    fun sendReviewService(Book_id:Int,reviewbody:String,rating:Int,shelf:String)
    {

        val queue = Volley.newRequestQueue(this)
        var url=Urls.createreview(Book_id.toString(),reviewbody,rating.toString(),shelf)
        val stringRequest = StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    var jsonresponse=JSONObject(response)
                    if(jsonresponse.getString("status")=="true")
                    {
                        Toast.makeText(this,"Thanks for your review ", Toast.LENGTH_SHORT).show()
                        writerreview.text.clear()
                        finish()
                    }

                    else if (jsonresponse.getString("status")=="false")
                    {
                        Toast.makeText(this,jsonresponse.getString("errors"), Toast.LENGTH_SHORT).show()
                    }

                },
                Response.ErrorListener {
                    Toast.makeText(this,"connection error, please try again", Toast.LENGTH_SHORT).show()

                }

        )

        queue.add(stringRequest)

    }

}
