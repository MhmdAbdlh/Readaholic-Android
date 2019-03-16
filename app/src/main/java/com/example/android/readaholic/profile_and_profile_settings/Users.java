package com.example.android.readaholic.profile_and_profile_settings;

import java.util.PriorityQueue;

public class Users {
    private String mUserName;
    private int mUsernumberOfBooks;
    private String mUserImageUrl;
    private int mNumberOfFollowers;
    private int getmNumberOfFolloweings;

    public int getGetmNumberOfFolloweings() {
        return getmNumberOfFolloweings;
    }

    public int getmNumberOfFollowers() {
        return mNumberOfFollowers;
    }

    public void setGetmNumberOfFolloweings(int getmNumberOfFolloweings) {
        this.getmNumberOfFolloweings = getmNumberOfFolloweings;
    }

    public String getmUserImageUrl() {
        return mUserImageUrl;
    }

    public String getmUserName() {
        return mUserName;
    }


    public void setmUserImageUrl(String mUserImageUrl) {
        this.mUserImageUrl = mUserImageUrl;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public void setmUsernumberOfBooks(int mUsernumberOfBooks) {
        this.mUsernumberOfBooks = mUsernumberOfBooks;
    }

    public void setmNumberOfFollowers(int mNumberOfFollowers) {
        this.mNumberOfFollowers = mNumberOfFollowers;
    }

    public int getmUsernumberOfBooks() {
        return mUsernumberOfBooks;
    }


    public Users(String Name, int NumberOfFolloweings,int NumberOfFollowers,String Image,int UsernumberOfBooks)
    {
        setGetmNumberOfFolloweings(NumberOfFolloweings);
        setmNumberOfFollowers(NumberOfFollowers);
        setmUserImageUrl(Image);
        setmUserName(Name);
        setmUsernumberOfBooks(UsernumberOfBooks);
    }
}
