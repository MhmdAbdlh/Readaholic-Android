package com.example.android.readaholic.myshelves

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.android.readaholic.R
import com.example.android.readaholic.books.BookPageActivity
import com.example.android.readaholic.books.BookPageInfo
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
        myview.booknavbtn.setOnClickListener {
            var intent=Intent(context,BookPageActivity::class.java)
            startActivity(intent)
        }

        return myview
    }


}
