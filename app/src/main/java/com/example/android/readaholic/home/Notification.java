package com.example.android.readaholic.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Notification {
    private int mType;              //1 = followed 2 = liked  3 = commented
    private String mImageUrl;
    private String mName;
    private String mBookName;
    private String mFollowerName;
    private String mDate;
    private String mUserName;
    private int mUserID;
    private int mReviewId;
    private int muserid;
    private int mid;
    int mreadornot = 0;//0 = not raed, 1 = read
    private String read;

    public Notification(int mreadornot ,int mid,int mType,String mImageUrl,String mName,int muserid){
        this.mreadornot = mreadornot;
        this.mType = mType;
        this.mImageUrl = mImageUrl;
        this.mName = mName;
        this.muserid = muserid;
        this.mid = mid;
    }

    public void setMreadornot(int mreadornot) { this.mreadornot = mreadornot; }
    public void setmBookName(String mBookName){this.mBookName = mBookName;}
    public void setmFollowerName(String mFollowerName){this.mFollowerName = mFollowerName;}
    public void setmDate(String mDate){this.mDate = mDate;}
    public void setmReviewId(int mReviewId){this.mReviewId = mReviewId;}
    public void setmUserName(String mUserName){this.mUserName = mUserName;}
    public void setmUserID(int mUserID) { this.mUserID = mUserID; }

    public int getmType(){return this.mType;}
    public String getmImageUrl(){return this.mImageUrl;}
    public String getmName(){return this.mName;}
    public String getmBookName(){return this.mBookName;}
    public String getmDate(){return this.mDate;}
    public String getmFollowerName(){return this.mFollowerName;}
    public int getmReviewId(){return this.mReviewId;}
    public String getmUserName(){return this.mUserName;}
    public int getMid() { return mid; }
    public int getMuserid(){return this.muserid;}
    public int getmUserID(){return this.mUserID;}
    public int getmreadornot(){return this.mreadornot;}
}

