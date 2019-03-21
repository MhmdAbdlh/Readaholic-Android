package com.example.android.readaholic.profile_and_profile_settings;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Users {
    private String mUserName;
    private int mUsernumberOfBooks;
    private String mUserImageUrl;
    private int mNumberOfFollowers;
    private int getmNumberOfFolloweings;
    private ArrayList<UserUpdate> mUpdates;


    private ArrayList<String> mCurrentlyReadingImageUrl;
    private ArrayList<String> mWantToReadImageUrl;
    private ArrayList<String> mReadImageUrl;

    public ArrayList<String> getmCurrentlyReadingImageUrl() {
        return mCurrentlyReadingImageUrl;
    }

    public ArrayList<String> getmReadImageUrl() {
        return mReadImageUrl;
    }

    public ArrayList<String> getmWantToReadImageUrl() {
        return mWantToReadImageUrl;
    }

    public void setmCurrentlyReadingImageUrl(ArrayList<String> mCurrentlyReadingImageUrl) {
        this.mCurrentlyReadingImageUrl = mCurrentlyReadingImageUrl;
    }

    public void setmReadImageUrl(ArrayList<String> mReadImageUrl) {
        this.mReadImageUrl = mReadImageUrl;
    }

    public void setmWantToReadImageUrl(ArrayList<String> mWantToReadImageUrl) {
        this.mWantToReadImageUrl = mWantToReadImageUrl;
    }

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


    public Users(String Name, int NumberOfFolloweings,int NumberOfFollowers,String Image,int UsernumberOfBooks,
                 ArrayList<String> mCurrentlyReadingImageUrl,ArrayList<String>mReadImageUrl,ArrayList<String>mWantToReadImageUrl)
    {
        setGetmNumberOfFolloweings(NumberOfFolloweings);
        setmNumberOfFollowers(NumberOfFollowers);
        setmUserImageUrl(Image);
        setmUserName(Name);
        setmUsernumberOfBooks(UsernumberOfBooks);
        setmWantToReadImageUrl(mWantToReadImageUrl);
        setmReadImageUrl(mReadImageUrl);
        setmCurrentlyReadingImageUrl(mCurrentlyReadingImageUrl);
    }
    public Users(){}
    public Users(String mUserName,String mUserImageUrl,int numberofbooks)
    {
        setmUsernumberOfBooks(numberofbooks);
        setmUserName(mUserName);
        setmUserImageUrl(mUserImageUrl);
    }
}
