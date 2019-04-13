package com.example.android.readaholic.profile_and_profile_settings;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Users model to hold Users Data
 * @author Hossam Ahmed
 */
public class Users {
    private int mUserId;

    @SerializedName("mUsername")
    private String mUserName;
    @SerializedName("mUsernumberofBooks")
    private int mUsernumberOfBooks;
    @SerializedName("mUserImageUrl")
    private String mUserImageUrl;
    private int mNumberOfFollowers;
    private int getmNumberOfFolloweings;
    private ArrayList<UserUpdate> mUpdates;
    private boolean mFollowerState;


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

    /**
     * set the currentlyReading image url list
     * @param mCurrentlyReadingImageUrl List of strings that hold books images urls
     */
    public void setmCurrentlyReadingImageUrl(ArrayList<String> mCurrentlyReadingImageUrl) {
        this.mCurrentlyReadingImageUrl = mCurrentlyReadingImageUrl;
    }

    /**
     * set the Read books image url list
     * @param mReadImageUrl List of strings that hold books images urls
     */

    public void setmReadImageUrl(ArrayList<String> mReadImageUrl) {
        this.mReadImageUrl = mReadImageUrl;
    }

    /**
     * set the Want To Read image url list
     * @param mWantToReadImageUrl List of strings that hold books images urls
     */
    public void setmWantToReadImageUrl(ArrayList<String> mWantToReadImageUrl) {
        this.mWantToReadImageUrl = mWantToReadImageUrl;
    }

    /**
     * get followings number
     * @return number of followings
     */
    public int getGetmNumberOfFolloweings() {
        return getmNumberOfFolloweings;
    }

    /**
     * get followers number
     * @return number of followers
     */
    public int getmNumberOfFollowers() {
        return mNumberOfFollowers;
    }

    /**
     *
     * @param getmNumberOfFolloweings
     */
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

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public int getmUserId() {
        return mUserId;
    }

    public void setmFollowerState(boolean mFollowerState) {
        this.mFollowerState = mFollowerState;
    }

    public boolean ismFollowerState() {
        return mFollowerState;
    }

    /**
     * Users Constructor
     * @param Name user name
     * @param NumberOfFolloweings number of followings
     * @param NumberOfFollowers number of followers
     * @param Image image url
     * @param UsernumberOfBooks number of books
     * @param mCurrentlyReadingImageUrl array of currently reading URLs
     * @param mReadImageUrl array of  read URLs
     * @param mWantToReadImageUrl array of Want to read URLs
     */
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

    /**
     * Empty constructor
     */
    public Users(){}

    /**
     * User Constructor with 3 parameters.
     * @param mUserName user name
     * @param mUserImageUrl image url
     * @param numberofbooks number of books.
     */
    public Users(String mUserName,String mUserImageUrl,int numberofbooks)
    {
        setmUsernumberOfBooks(numberofbooks);
        setmUserName(mUserName);
        setmUserImageUrl(mUserImageUrl);
    }

    /**
     * constructor of profile
     * @param id user id
     * @param mUserName user name
     * @param mUserImageUrl user image url
     * @param numberofbooks user number of books
     */
    public Users(int id,String mUserName,String mUserImageUrl,int numberofbooks)
    {
        setmUsernumberOfBooks(numberofbooks);
        setmUserId(id);
        setmUserName(mUserName);
        setmUserImageUrl(mUserImageUrl);
    }

    /**
     * constructor for followers
     * @param id user id
     * @param mUserName user name
     * @param mUserImageUrl user image url
     * @param numberofbooks user total number of books
     * @param mFollowerState following state
     */
    public Users(int id,String mUserName,String mUserImageUrl,int numberofbooks,boolean mFollowerState)
    {
        setmUserName(mUserName);
        setmUserId(id);
        setmUserImageUrl(mUserImageUrl);
        setmFollowerState(mFollowerState);
        setmUsernumberOfBooks(numberofbooks);
    }
}
