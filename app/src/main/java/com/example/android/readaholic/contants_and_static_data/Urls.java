package com.example.android.readaholic.contants_and_static_data;



public class Urls {
    public static final String ROOT = "http://be1989bd.ngrok.io";
    //Login url
    public static String LOG_IN = "http://be1989bd.ngrok.io/api/login";

    public static String getShowBook(String Bookid)
    {
        return ROOT+"/api/books/show?book_id="+Bookid+"&token="+UserInfo.sToken+"&type="+UserInfo.sTokenType;

    }
    public static String getShowReviewsForaBook(String Bookid)
    {
        return ROOT+"/api/showReviewsForABook?book_id=1&token="+UserInfo.sToken+"&type="+UserInfo.sTokenType;

    }
    public static String createreview(String Bookid,String reviewbody,String rating,String shelf)
    {
        return ROOT+"/api/reviwes/create?bookId="+Bookid+"&body="+reviewbody+"&rating="+rating+"&shelf="+shelf+"&token="+UserInfo.sToken+"&type="+UserInfo.sTokenType;

    }


}
