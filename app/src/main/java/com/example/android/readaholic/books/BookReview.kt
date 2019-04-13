package com.example.android.readaholic.books

import android.media.Rating

/**
 * This Class is to hold the review data
 *
 * @property name
 */
data class BookReview(var reviewid:Int=0,var userId:Int=0,var bookId:Int=0,var reviewbody:String="",var rating:Int=0,
var numberoflikes:Int=0,var numberofcomments:Int=0,var username:String="",var userimageurl:String="",var createdate:String="")