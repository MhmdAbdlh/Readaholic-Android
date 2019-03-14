package com.example.android.readaholic.books

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.android.readaholic.R
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.bookreview.view.*

class ReviewActivity : AppCompatActivity() {
    var CommentList:ArrayList<CommentInfo>?=null
    var commentadapter: CommentsAdabterlist?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        CommentList= ArrayList()
        CommentList!!.add(CommentInfo("sadfas"))
        CommentList!!.add(CommentInfo("sadfas"))
        CommentList!!.add(CommentInfo("sadfas"))
        CommentList!!.add(CommentInfo("sadfas"))
        CommentList!!.add(CommentInfo("sadfas"))
        CommentList!!.add(CommentInfo("sadfas"))
        CommentList!!.add(CommentInfo("sadfas"))
        commentadapter= CommentsAdabterlist()
        commentlist.adapter=commentadapter
    }

    inner class CommentsAdabterlist(): BaseAdapter()
    {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myview=layoutInflater.inflate(R.layout.commentticket,null)
            var currentcomment= CommentList!![position]
            myview.reviwernametxtui.text=currentcomment.name
            return myview
        }

        override fun getItem(position: Int): Any {
            return  CommentList!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return CommentList!!.size
        }


    }
}
