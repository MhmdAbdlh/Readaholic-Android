package com.example.android.readaholic.books

/**
 * This data Class is to store The book dataand use this to show in the UI
 *
 * @property book_title
 * @property isbn
 * @property image_url
 * @property small_image_url
 * @property num_pages
 * @property publisher
 * @property publication_day
 * @property publication_year
 * @property publication_month
 * @property average_rating
 * @property ratings_count
 * @property description
 * @property author_id
 * @property author_name
 * @property genre
 * @property bookid
 * @property reviewscount
 */
class  BookPageInfo(var book_title:String="unknown",var isbn:Int=0,var image_url:String="unknown",var small_image_url:String="unknown"
,var num_pages:Int=0,var publisher:String="unknown",var publication_date:String="10-10-2019",var publication_year:Int=0
,var publication_month:Int=0,var average_rating:Float=0f,var ratings_count:Int=0,var description:String="unknown",var author_id:Int=0
,var author_name:String="unknown",var genre:String="unknown",var bookid:Int=0,var reviewscount:Int=0)
