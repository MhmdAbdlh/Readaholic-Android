package com.example.android.readaholic.home;


import android.os.Parcel;
import android.os.Parcelable;

public class Updates implements Parcelable {

    private int mTypeOfUpdates;
    private String mNameOfUser;
    private String mNameofFollow;
    private String mInnerImgUrl;
    private String mBookName;
    private String mReview = "";
    private String mDateOfUpdates;
    private String mAuthorName;
    private String mComment;
    private int mInnerUpdate;
    private int mNumOfLikes;
    private int mNumOfComments;
    private int mRatingValue = 0;
    private String mShelf;
    private int mUserShelf;
    private String mInnerDate;
    private int mBookId;
    private int mReviewID;
    private int mNewActivity = 0;

    private String mBookCover;
    private String mUserimg;

    private int mUserId = 0;
    private int mInnerUserId;

    private int mShelfUpdateType;
    /**
     * Constructor of Updates object.
     *
     * @param mTypeOfUpdates int: Type of Updates(0:review or rating , 1:add to shelf, 2:following, 3:Like update, 4:comment on updates)
     * @param mNameOfUser String: the name of user makes that update.
     * @param mDateOfUpdates String: the date of update.
     * @param mNumOfLikes int: number of likes in this update.
     * @param mNumOfComments int: number of comments in this update.
     * @param mUserId int: the id of the user makes that update.
     */
    public Updates(int mTypeOfUpdates,String mNameOfUser,String mDateOfUpdates,int mNumOfLikes,int mNumOfComments,int mUserId){
        this.mTypeOfUpdates = mTypeOfUpdates;
        this.mNameOfUser = mNameOfUser;
        this.mDateOfUpdates = mDateOfUpdates;
        this.mNumOfLikes = mNumOfLikes;
        this.mNumOfComments = mNumOfComments;
        this.mUserId = mUserId;
    }

    /**
     * Constructor of Updates object to be parcel to move to another fragment.
     *
     * @param in Parcel: the object that will be filled with the info of updates.
     */
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
        mInnerImgUrl = in.readString();
        mReviewID = in.readInt();
        mUserShelf = in.readInt();
    }

    public static final Creator<Updates> CREATOR = new Creator<Updates>() {
        @Override
        /**
         *Create a new instance of Upadtes class to be passed from fragment to another.
         * @param Parcel The Parcel to read the object's data from.
         * @return 	Returns a new instance of the Parcelable class (Upadtes).
         */
        public Updates createFromParcel(Parcel in) {
            return new Updates(in);
        }

        @Override
        /**
         *Create a new array of Upadtes class to be passed from fragment to another.
         * @param size int: Size of the array.
         * @return 	Returns a new array of Upadtes.
         */
        public Updates[] newArray(int size) {
            return new Updates[size];
        }
    };

    /**
     *Set Inner user Id that makes update and another user like it or comment on it.
     * @param mInnerUserId int: Inner User ID.
     */
    public void setmInnerUserId(int mInnerUserId){this.mInnerUserId = mInnerUserId;}
    /**
     *Set newActivity equal 1 when clicking in comment button in update page fragment.
     * @param mNewActivity int: defult zero in HOmeFragment or Profile fragment
     */

    public void setmNewActivity(int mNewActivity) {this.mNewActivity = mNewActivity;}

    public void setmUserShelf(int mUserShelf){this.mUserShelf = mUserShelf;}
    public void setmReviewID(int mReviewID){this.mReviewID = mReviewID;}
    public void setmShelfUpdateType(int mShelfUpdateType){this.mShelfUpdateType = mShelfUpdateType;}
    public void setmUserimg(String mUserimg){this.mUserimg = mUserimg;}
    public void setmInnerImgUrl(String mInnerImgUrl){this.mInnerImgUrl = mInnerImgUrl;}
    public void setmBookCover(String mBookCover){this.mBookCover = mBookCover;}
    /**
     *Set review when type of update equals 0 .
     * @param mReview String: review on the book.
     */
    public void setmReview(String mReview){ this.mReview = mReview; }
    /**
     * Set book id to give it to book fragment to know which book was clicked.
     * @param  mBookId int: book id in the update.
     */
    public void setmBookId(int mBookId){this.mBookId = mBookId;}
    /**
     *Set shelf of the book (want to read ,currently reading ,read)
     * @param mShelf String: defult zero in HOmeFragment or Profile fragment
     */
    public void setmShelf(String mShelf){ this.mShelf = mShelf; }
    /**
     *Set comment on the update of type 4.
     * @param mComment String: comment on update.
     */
    public void setmComment(String mComment){ this.mComment = mComment; }
    /**
     * Set Name of the book .
     * @param mBookName String: the name of the book in the update.
     */
    public void setmBookName(String mBookName){ this.mBookName = mBookName; }
    /**
     *Set AuthorName of the book.
     * @param mAuthorName String: Author Name of the book in the update.
     */
    public void setmAuthorName(String mAuthorName){ this.mAuthorName = mAuthorName; }
    /**
     * Set rating value.
     * @param mRatingValue int: rating on the book in update.
     */
    public void setmRatingValue(int mRatingValue){ this.mRatingValue = mRatingValue; }
    /**
     * Set Type of inner update (0:review or rating , 1:add to shelf, 2:following)
     * @param mInnerUpdate int: the type of inner updates when user like or comment an update.
     */
    public void setmInnerUpdate(int mInnerUpdate){ this.mInnerUpdate = mInnerUpdate; }
    /**
     *Set Name of follwers in the update of type 2.
     * @param mNameofFollow String: name of the person that has been followed.
     */
    public void setmNameofFollow(String mNameofFollow){ this.mNameofFollow = mNameofFollow; }
    /**
     * Set InnerDate of the inner update.
     * @param mInnerDate String: InnerDate of the inner update
     */
    public void setmInnerDate(String mInnerDate){ this.mInnerDate = mInnerDate; }


    /**
     * get DateOfUpdates .
     * @return DateOfUpdates
     */
    public String getmDateOfUpdates(){ return mDateOfUpdates; }
    public String getmBookCover() { return mBookCover; }
    public int getmUserShelf(){return mUserShelf;}
    public int getmReviewID(){return mReviewID;}
    public String getmUserimg(){return mUserimg;}
    public int getmShelfUpdateType(){return mShelfUpdateType;}
    public String getmInnerImgUrl(){return mInnerImgUrl;}
    /**
     * get BookId of the update.
     * @return BookId
     */
    public int getmBookId() { return mBookId; }
    /**
     * Set InnerDate of the inner update.
     * @return DateOfUpdates
     */
    public int getmNumOfLikes(){ return mNumOfLikes; }
    /**
     * get Comment of the update of type 4.
     * @return Comment of update
     */
    public String getmComment(){ return mComment; }
    /**
     * get Inner Date of the inner update.
     * @return  Inner Date.
     */
    public String getmInnerDate(){ return mInnerDate; }
    /**
     * get Number Of Comments of update.
     * @return NumOfComments.
     */
    public int getmNumOfComments(){ return mNumOfComments; }
    /**
     * get RatingValue of the update of type 0.
     * @return rating value of the book.
     */
    public int getmRatingValue(){ return mRatingValue; }
    /**
     * get Name of a person that hve been followed in update of type 2.
     * @return Name of a person that hve been followed
     */
    public String getmNameofFollow(){ return mNameofFollow; }
    /**
     * get Type Of Updates (0:review or rating , 1:add to shelf, 2:following, 3:Like update, 4:comment on updates).
     * @return Type of update
     */
    public int getmTypeOfUpdates(){ return mTypeOfUpdates; }
    /**
     * get Type Of inner Updates when somesome like or comment on update (0:review or rating , 1:add to shelf, 2:following).
     * @return Type of inner update
     */
    public int getmInnerUpdate(){ return  mInnerUpdate; }
    /**
     * get the name of the user that have made the update.
     * @return the name of the user.
     */
    public String getmNameOfUser(){ return mNameOfUser; }
    /**
     * get the Author Name of the book in update.
     * @return the Author Name
     */
    public String getmAuthorName(){ return mAuthorName; }
    /**
     * get Book Name of the updates contains book.
     * @return Book Name.
     */
    public String getmBookName(){ return mBookName; }
    /**
     * get review on the book in update of type 0.
     *      * @return review on the book.
     */
    public String getmReview(){ return mReview; }
    /**
     * get shelf of the book in update of type 1.
     * @return shelf of the book.
     */
    public String getmShelf(){return mShelf;}
    /**
     * get NewActivity that identify which fragment we are in(0: homefragment or profilefragment, 1:updatepagefragment) .
     * @return int new activity
     */
    public int getmNewActivity(){return mNewActivity;}
    /**
     * Set InnerDate of the inner update.
     * @return DateOfUpdates
     */
    public int getmUserId(){return mUserId;}
    /**
     * Set UserId of the inner update in updates of type 3,4.
     * @return UserId
     */
    public int getmInnerUserId(){return mInnerUserId;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    /**
     *Putting this object into a parcle with all it's attributes to be passed
     * @param dest 	Parcel: The Parcel in which the object should be written
     * @param flags int: Additional flags about how the object should be written
     */
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
        dest.writeInt(mReviewID);
    }
}
