package com.example.android.readaholic.home;


import android.os.Parcel;
import android.os.Parcelable;

public class Updates implements Parcelable {

    private int mTypeOfUpdates;
    private String mNameOfUser;
    private String mNameofFollow;
    private String mBookName;
    private String mReview;
    private String mDateOfUpdates;
    private String mAuthorName;
    private String mComment;
    private int mInnerUpdate;
    private int mNumOfLikes;
    private int mNumOfComments;
    private int mRatingValue;
    private String mShelf;
    private String mInnerDate;
    private int mBookId;
    private int mNewActivity = 0;
    private int mUserId = 0;
    private int mInnerUserId;

    public Updates(int mTypeOfUpdates,String mNameOfUser,String mDateOfUpdates,int mNumOfLikes,int mNumOfComments,int mUserId){
        this.mTypeOfUpdates = mTypeOfUpdates;
        this.mNameOfUser = mNameOfUser;
        this.mDateOfUpdates = mDateOfUpdates;
        this.mNumOfLikes = mNumOfLikes;
        this.mNumOfComments = mNumOfComments;
        this.mUserId = mUserId;
    }

    protected Updates(Parcel in) {
        mTypeOfUpdates = in.readInt();
        mNameOfUser = in.readString();
        mNameofFollow = in.readString();
        mBookName = in.readString();
        mReview = in.readString();
        mDateOfUpdates = in.readString();
        mAuthorName = in.readString();
        mComment = in.readString();
        mInnerUpdate = in.readInt();
        mNumOfLikes = in.readInt();
        mNumOfComments = in.readInt();
        mRatingValue = in.readInt();
        mShelf = in.readString();
        mInnerDate = in.readString();
        mBookId = in.readInt();
    }

    public static final Creator<Updates> CREATOR = new Creator<Updates>() {
        @Override
        public Updates createFromParcel(Parcel in) {
            return new Updates(in);
        }

        @Override
        public Updates[] newArray(int size) {
            return new Updates[size];
        }
    };

    public void setmInnerUserId(int mInnerUserId){this.mInnerUserId = mInnerUserId;}
    public void setmNewActivity(int mNewActivity) {this.mNewActivity = mNewActivity;}
    public void setmReview(String mReview){ this.mReview = mReview; }
    public void setmBookId(int mBookId){this.mBookId = mBookId;}
    public void setmShelf(String mShelf){ this.mShelf = mShelf; }
    public void setmComment(String mComment){ this.mComment = mComment; }
    public void setmBookName(String mBookName){ this.mBookName = mBookName; }
    public void setmAuthorName(String mAuthorName){ this.mAuthorName = mAuthorName; }
    public void setmRatingValue(int mRatingValue){ this.mRatingValue = mRatingValue; }
    public void setmInnerUpdate(int mInnerUpdate){ this.mInnerUpdate = mInnerUpdate; }
    public void setmNameofFollow(String mNameofFollow){ this.mNameofFollow = mNameofFollow; }
    public void setmInnerDate(String mInnerDate){ this.mInnerDate = mInnerDate; }

    public String getmDateOfUpdates(){ return mDateOfUpdates; }
    public int getmBookId() { return mBookId; }
    public int getmNumOfLikes(){ return mNumOfLikes; }
    public String getmComment(){ return mComment; }
    public String getmInnerDate(){ return mInnerDate; }
    public int getmNumOfComments(){ return mNumOfComments; }
    public int getmRatingValue(){ return mRatingValue; }
    public String getmNameofFollow(){ return mNameofFollow; }
    public int getmTypeOfUpdates(){ return mTypeOfUpdates; }
    public int getmInnerUpdate(){ return  mInnerUpdate; }
    public String getmNameOfUser(){ return mNameOfUser; }
    public String getmAuthorName(){ return mAuthorName; }
    public String getmBookName(){ return mBookName; }
    public String getmReview(){ return mReview; }
    public String getmShelf(){return mShelf;}
    public int getmNewActivity(){return mNewActivity;}
    public int getmUserId(){return mUserId;}
    public int getmInnerUserId(){return mInnerUserId;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mTypeOfUpdates);
        dest.writeString(mNameOfUser);
        dest.writeString(mNameofFollow);
        dest.writeString(mBookName);
        dest.writeString(mReview);
        dest.writeString(mDateOfUpdates);
        dest.writeString(mAuthorName);
        dest.writeString(mComment);
        dest.writeInt(mInnerUpdate);
        dest.writeInt(mNumOfLikes);
        dest.writeInt(mNumOfComments);
        dest.writeInt(mRatingValue);
        dest.writeString(mShelf);
        dest.writeString(mInnerDate);
        dest.writeInt(mBookId);
    }
}
