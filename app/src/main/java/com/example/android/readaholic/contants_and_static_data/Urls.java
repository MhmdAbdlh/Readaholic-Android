package com.example.android.readaholic.contants_and_static_data;



public class Urls {
    public static final String ROOT = "http://972c6e5d.ngrok.io";
    //Login url
    public static String LOG_IN = "http://972c6e5d.ngrok.io/api/login";

    public static String getShowBook(String Bookid)
    {
        return ROOT+"/api/books/show?book_id="+Bookid+"&token="+UserInfo.sToken+"&type="+UserInfo.sTokenType;

    }
    public static String getShowReviewsForaBook(String Bookid)
    {
        return ROOT+"/api/showReviewsForABook?bookId="+Bookid+"&token="+UserInfo.sToken+"&type="+UserInfo.sTokenType;

    }
    public static String createreview(String Bookid,String reviewbody,String rating,String shelf)
    {
        return ROOT+"/api/reviwes/create?bookId="+Bookid+"&body="+reviewbody+"&rating="+rating+"&shelf="+shelf+"&token="+UserInfo.sToken+"&type="+UserInfo.sTokenType;

    }
    public static String deletereview(String reviewid)
    {
        return ROOT+"/api/reviwes/delete?reviewId="+reviewid+"&token="+UserInfo.sToken+"&type="+UserInfo.sTokenType;
    }
    public static String editreview(String reviewid,String rating,String body)
    {
        return ROOT+"api/reviwes/edit?reviewId="+reviewid+"&body="+body+"&rating="+rating+"&token="+UserInfo.sToken+"&type="+UserInfo.sTokenType;
    }

    public static String getShowReview(String review_id)
    {
        return ROOT+"/api/showReviewOfBook?reviewId="+review_id+"&token="+UserInfo.sToken+"&type="+UserInfo.sTokenType;

    }

}
