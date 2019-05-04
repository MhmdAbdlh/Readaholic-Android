package com.example.android.readaholic.explore;

/**
 * holds the data of books
 */
public class BookModel {
    private String mIsbn ;
    private String mTitle;
    private String mImageUrl;
    private int mId;

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

    public int getmId() {
        return mId;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setmIsbn(String mIsbn) {
        this.mIsbn = mIsbn;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public BookModel(){}

}
