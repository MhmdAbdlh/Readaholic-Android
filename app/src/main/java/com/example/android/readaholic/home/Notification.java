package com.example.android.readaholic.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Notification {
    int mType;              //1 = followed 2 = liked  3 = commented
    String mImageUrl;
    String mName;
    String mBookName;
    String mFollowerName;

    public Notification(int mType,String mImageUrl,String mName){
        this.mType = mType;
        this.mImageUrl = mImageUrl;
        this.mName = mName;
    }

    public void setmBookName(String mBookName){this.mBookName = mBookName;}
    public void setmFollowerName(String mFollowerName){this.mFollowerName = mFollowerName;}

    public int getmType(){return this.mType;}
    public String getmImageUrl(){return this.mImageUrl;}
    public String getmName(){return this.mName;}
    public String getmBookName(){return this.mBookName;}
    public String getmFollowerName(){return this.mFollowerName;}
}

