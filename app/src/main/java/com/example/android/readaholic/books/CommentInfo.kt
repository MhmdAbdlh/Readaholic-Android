package com.example.android.readaholic.books

/**
 * This class to hold data of the comment
 *
 * @property commentid
 * @property userid
 * @property username
 * @property cuserimageurl
 * @property commentbody
 * @property commentdate
 */
data class CommentInfo(var commentid:Int=0,var userid:Int=0,var username:String=""
,var cuserimageurl:String="",var commentbody:String,var commentdate:String="",var havethecomment:Boolean=false)