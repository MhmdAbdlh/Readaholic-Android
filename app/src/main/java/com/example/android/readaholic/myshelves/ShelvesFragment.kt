package com.example.android.readaholic.myshelves

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

import com.example.android.readaholic.R
import com.example.android.readaholic.books.BookPageActivity
import com.example.android.readaholic.books.BookPageInfo
import kotlinx.android.synthetic.main.activity_book_page.*
import kotlinx.android.synthetic.main.fragment_shelves.*
import kotlinx.android.synthetic.main.fragment_shelves.view.*
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       var myview= inflater.inflate(R.layout.fragment_shelves, container, false)

            YoYo.with(Techniques.FadeIn)
                    .duration(1000)
                    .playOn(myview.readbooks);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(myview.cuurentlyreading);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(myview.wanttoread);

            myview.readbooks.setOnClickListener {
                var intent=Intent(context,BookShelves::class.java)
                intent.putExtra("Shelve",0)
                startActivity(intent)


            }
        myview.cuurentlyreading.setOnClickListener {
            var intent=Intent(context,BookShelves::class.java)
            intent.putExtra("Shelve",1)
            startActivity(intent)


        }
        myview.wanttoread.setOnClickListener {
            var intent=Intent(context,BookShelves::class.java)
            intent.putExtra("Shelve",2)
            startActivity(intent)


        }
        return myview
    }


}
