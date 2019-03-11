package com.example.android.readaholic

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_book_reviews.*
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.bookreview.view.*

class ReviewActivity : AppCompatActivity() {
    var bookr:ArrayList<CommentInfo>?=null
    var adapter: CommentsAdabterlist1?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        bookr= ArrayList()
        bookr!!.add(CommentInfo("sadfas"))
        bookr!!.add(CommentInfo("sadfas"))
        bookr!!.add(CommentInfo("sadfas"))
        bookr!!.add(CommentInfo("sadfas"))
        bookr!!.add(CommentInfo("sadfas"))
        bookr!!.add(CommentInfo("sadfas"))
        bookr!!.add(CommentInfo("sadfas"))
        adapter= CommentsAdabterlist1()
        commentlist.adapter=adapter
    }

    inner class CommentsAdabterlist1(): BaseAdapter()
    {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myview=layoutInflater.inflate(R.layout.commentticket,null)
            var currentreview= bookr!![position]
            myview.reviwernametxtui.text=currentreview.name

            return myview
        }

        override fun getItem(position: Int): Any {
            return  bookr!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return bookr!!.size
        }


    }
}
