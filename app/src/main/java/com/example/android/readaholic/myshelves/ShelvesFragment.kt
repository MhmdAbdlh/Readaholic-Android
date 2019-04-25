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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ShelvesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ShelvesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ShelvesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun getnumber(shelfname:String) :Int
    {
            var num_books=0
            val queue = Volley.newRequestQueue(context)
            var url = Urls.getselfbooks(shelfname)
            val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        var jsonresponse= JSONObject(response)
                        var books=jsonresponse.getJSONArray("pages")
                        num_books= books.length()
                    },
                    Response.ErrorListener {

                    }
            )
            queue.add(stringRequest)
            return num_books

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
                intent.putExtra("Shelve",0)
                startActivity(intent)


            }
        myview.Creadbtn.setOnClickListener {
            var intent=Intent(context,BookShelves::class.java)
            intent.putExtra("Shelve",1)
            startActivity(intent)


        }
        myview.Wreadbtn.setOnClickListener {
            var intent=Intent(context,BookShelves::class.java)
            intent.putExtra("Shelve",2)
            startActivity(intent)
        }
        var Rnumbooks=getnumber("read");
        var Cnumbooks=getnumber("currently_reading")
        var Wnumbooks=getnumber("to_read")
        myview.Rcountbooks.text=Rnumbooks.toString()
        myview.Ccountbooks.text=Cnumbooks.toString()
        myview.Wbooknumber.text=Wnumbooks.toString()
        return myview
    }


}
