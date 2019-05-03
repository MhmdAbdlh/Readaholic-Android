package com.example.android.readaholic.myshelves

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

import com.example.android.readaholic.R
import com.example.android.readaholic.books.BookPageActivity
import com.example.android.readaholic.books.BookPageInfo
import com.example.android.readaholic.books.Cbookdata
import com.example.android.readaholic.contants_and_static_data.Urls
import kotlinx.android.synthetic.main.activity_book_page.*
import kotlinx.android.synthetic.main.fragment_shelves.*
import kotlinx.android.synthetic.main.fragment_shelves.view.*
import org.json.JSONObject


class ShelvesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setnumbers(num_books:Int,shelfname:Int)
    {
        when(shelfname)
        {
            0->view!!.Rcountbooks.text=num_books.toString()

            1->view!!.Ccountbooks.text=num_books.toString()

            2->view!!.Wbooknumber.text=num_books.toString()


        }
    }

    fun getnumber(shelftype:Int)
    {

        val queue = Volley.newRequestQueue(context)
        var url = Urls.getselfbooks(shelftype.toString())
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    var jsonresponse= JSONObject(response)
                    var books=jsonresponse.getJSONArray("pages")
                    setnumbers(books.length(),shelftype)
                },
                Response.ErrorListener {

                }
        )
        queue.add(stringRequest)


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var myview= inflater.inflate(R.layout.fragment_shelves, container, false)
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(myview.Readlayout);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(myview.Clayout);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(myview.Wlayout);

        myview.Readnbtn.setOnClickListener {
            var intent=Intent(context,BookShelves::class.java)
            Cbookdata.shelf=0
            startActivity(intent)


        }
        myview.Creadbtn.setOnClickListener {
            var intent=Intent(context,BookShelves::class.java)
            Cbookdata.shelf=1
            startActivity(intent)


        }
        myview.Wreadbtn.setOnClickListener {
            var intent=Intent(context,BookShelves::class.java)
            Cbookdata.shelf=2
            startActivity(intent)
        }
        getnumber(0)
        getnumber(1)
        getnumber(2)
        return myview
    }


}
