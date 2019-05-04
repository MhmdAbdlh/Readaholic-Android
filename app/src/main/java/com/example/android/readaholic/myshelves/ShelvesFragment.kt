package com.example.android.readaholic.myshelves
import android.content.Intent
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
import com.example.android.readaholic.books.Cbookdata
import com.example.android.readaholic.contants_and_static_data.Urls
import com.example.android.readaholic.contants_and_static_data.UserInfo
import kotlinx.android.synthetic.main.fragment_shelves.view.*
import org.json.JSONObject
class ShelvesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!UserInfo.ISMemic)
        {
            getnumber(0)
            getnumber(1)
            getnumber(2)
        }
    }

    /**
     * set the number of books given shelf number
     * @param num_books number of books
     * @param shelfname shelf number
     */

    fun setnumbers(num_books:Int,shelfname:Int)
    {
        when(shelfname)
        {
            0->view!!.Rcountbooks.text=num_books.toString()

            1->view!!.Ccountbooks.text=num_books.toString()

            2->view!!.Wbooknumber.text=num_books.toString()

        }
    }

    override fun onStop() {
        super.onStop()
        UserInfo.USER_ID=-1
    }

    override fun onDestroy() {
        super.onDestroy()
        UserInfo.USER_ID=-1
    }
    /**
     * get the number of books for every shelf
     *
     * @param shelftype
     */
    fun getnumber(shelftype:Int)
    {
        val queue = Volley.newRequestQueue(context)
        var url:String=""
        if(UserInfo.USER_ID==-1)
        {
             url = Urls.getselfbooks(shelftype.toString())
        }
        else{
            url = Urls.getselfbooksanotheruser(shelftype.toString(),UserInfo.USER_ID.toString())
        }

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

    override fun onResume() {
        super.onResume()
        if(UserInfo.ISMemic)
        {
            view!!.Rcountbooks.text=0.toString()

            view!!.Ccountbooks.text=2.toString()

            view!!.Wbooknumber.text=2.toString()

        }
        else
        {
            getnumber(0)
            getnumber(1)
            getnumber(2)

        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var myview= inflater.inflate(com.example.android.readaholic.R.layout.fragment_shelves, container, false)



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
        if(UserInfo.ISMemic)
        {
            myview.Rcountbooks.text=0.toString()

            myview.Ccountbooks.text=2.toString()

            myview.Wbooknumber.text=1.toString()

        }
        else
        {
            getnumber(0)
            getnumber(1)
            getnumber(2)

        }

        return myview
    }



}
