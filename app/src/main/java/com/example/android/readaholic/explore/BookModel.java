package com.example.android.readaholic.explore;

public class BookModel {
    private String mIsbn ;
    private String mTitle;
    private String mImageUrl;

    public BookModel(String mIsbn, String mTitle, String mImageUrl) {
        this.mIsbn = mIsbn;
        this.mTitle = mTitle;
        this.mImageUrl = mImageUrl;
    }

    public String getmIsbn() {
        return mIsbn;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

}
