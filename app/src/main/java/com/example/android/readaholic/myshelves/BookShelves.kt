package com.example.android.readaholic.myshelves
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.example.android.readaholic.R
import com.example.android.readaholic.books.BookPageInfo
import kotlinx.android.synthetic.main.activity_book_shelves.*
import kotlinx.android.synthetic.main.bookticket.view.*


class BookShelves : AppCompatActivity() {
protected var booklist:ArrayList<BookPageInfo>?=null
protected var booklistadapter:BookistAdapter?=null
protected var shelvetype:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_shelves)
        shelvetype=Intent().getIntExtra("Shelve",2)
        Toast.makeText(this,shelvetype.toString(),Toast.LENGTH_SHORT).show()
        booklist= ArrayList()
        booklist!!.add(BookPageInfo())
        booklist!!.add(BookPageInfo())
        booklist!!.add(BookPageInfo())
        booklist!!.add(BookPageInfo())
        booklist!!.add(BookPageInfo())
        booklistadapter=BookistAdapter()
        booklistui.adapter=booklistadapter
    }



     inner class BookistAdapter(): BaseAdapter()
    {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myview=layoutInflater.inflate(R.layout.bookticket,null)
            if(shelvetype==0)
            {
                myview.noratedpartui.visibility=View.GONE
                myview.ratedpartui.visibility=View.VISIBLE
            }
            else
            {
                myview.ratedpartui.visibility=View.GONE
                myview.noratedpartui.visibility=View.VISIBLE
            }
            return myview
        }

        override fun getItem(position: Int): Any {
            return  booklist!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return booklist!!.size
        }

    }
}
