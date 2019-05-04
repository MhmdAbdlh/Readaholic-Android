package com.example.android.readaholic.contants_and_static_data;





import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import com.example.android.readaholic.R;
public class Urls {

    private Activity mActivity;
    private Context mContext;

    public static final String ROOT ="http://ec2-52-90-5-77.compute-1.amazonaws.com";
   // public static final String ROOT ="http://4404e3cc.nrgok.io";

    //Login url
    public static String LOG_IN = "/api/login";
    //Logout url
    public static String LOG_OUT = "/api/logout";
    //sign up url
    public static String SIGN_UP = "/api/signup";
    //fogot password url
    public static String FORGOT_PASSWORD = "/api/forgotpassword";
    //show settings url
    public static String SHOW_SETTINGS = "/api/showsetting";
    //change birthday url
    public static String CHANGE_BIRTHDAY = "/api/changebirthday";
    // who can see my birthday url
    public static String WHO_CAN_SEE_MY_BIRTHDAY = "/api/whocanseemybirthday";
    //change country url
    public static String CHANGE_COUNTRY = "/api/changecountry";
    //change password url
    public static String CHANGE_PASSWORD = "/api/changepassword";
    //change image url
    public static String CHANGE_IMAGE = "/api/changeimage";
    //who can see my country url
    public static String WHO_CAN_SEE_MY_COUNTRY = "/api/whocanseemycountry";
    //change name
    public static String CHANGE_NAME = "/api/changename";
    //search for books by author
    public static String SEARCH_BY_AUTHOR = "/api/Books/book_Authorname";
    //search for book by its title
    public static String SEARCH_BY_TITLE = "/api/Books/book_title";
    //search for book by its genre
    public static String SEARCH_BY_GENRE = "/api/books/genre";

    public Urls(Activity activity, Context context) {
        mActivity = activity;
        mContext = context;
    }

    public static String getShowBook(String Bookid) {
        return ROOT + "/api/books/show?book_id=" + Bookid + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;

    }

    public static String getShowReviewsForaBook(String Bookid) {
        return ROOT + "/api/showReviewsForABook?bookId=" + Bookid + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;

    }

    public static String createreview(String Bookid, String reviewbody, String rating, String shelf) {
        return ROOT + "/api/reviwes/create?bookId=" + Bookid + "&body=" + reviewbody + "&rating=" + rating + "&shelf=" + shelf + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;

    }

    public static String editreview(String reviewid, String rating, String body) {
        return ROOT + "/api/reviwes/edit?reviewId=" + reviewid + "&body=" + body + "&rating=" + rating + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }

    public static String getShowReview(String review_id) {
        return ROOT + "/api/showReviewOfBook?reviewId=" + review_id + "&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;

    }
    public static String getShowReviewForBookForUser(String Book_id) {
        return ROOT + "/api/showReviewForBookForUser?bookId="+Book_id+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;

    }
    public static String deleteMyReview(String review_id) {
        return ROOT + "/api/reviwes/delete?reviewId="+review_id+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;

    }
    public static String makeLikeUnlike(String review_id) {
        return ROOT + "/api/LikeOrUnLike?id="+review_id+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }
    public static String makecomment(String review_id,String body) {
        return ROOT + "/api/makeComment?id="+review_id+"&type=0&body="+body+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }
    public static String deletecomment(String commentid) {
        return ROOT + "/api/deleteComment?id="+commentid+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }
    public static String getselfbooks(String shlef_name) {
        return ROOT + "/api/shelf?shelf_name="+shlef_name+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }

    public static String getselfbooksanotheruser(String shlef_name,String user_id) {
        return ROOT + "/api/shelf?shelf_name="+shlef_name+"&user_id="+user_id+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }
    public static String addbooktoshelf(String shelf_id,String book_id) {
        return ROOT + "/api/shelf/add_book?shelf_id="+shelf_id+"&book_id="+book_id+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }

    public static String deletebooktoshelf(String shelf_id,String book_id) {
        return ROOT + "/api/shelf/remove_book?shelf_id="+shelf_id+"&book_id="+book_id+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }
    public static String getlistofcomments(String book_id) {
        return ROOT + "/api/listComments?id="+book_id+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }

    public static String getshelfonbook(String book_id) {
        return ROOT + "/api/showShelf?bookId="+book_id+"&token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;
    }
    public String constructTokenParameters() {
        String parameters = "";

        parameters += "token=" + UserInfo.sToken + "&type=" + UserInfo.sTokenType;

        return parameters;
    }

    //region login and signup

    public String getLogInParameters() {
        String parameters = "";

        //getting email and password entered by user
        String email = ((EditText) mActivity.findViewById(R.id.SignUp_email_edittext)).getText().toString();
        String password = ((EditText) mActivity.findViewById(R.id.SignIn_password_edittext)).getText().toString();


        //constructing the parameters
        parameters += "email=" + email + "&password=" + password;

        return parameters;
    }

    public String getSignUpParameters() {
        String parameters = "";

        //getting data entered by user
        /*****************************************************************************************/
        String email = ((EditText) mActivity.findViewById(R.id.SignUp_email_edittext)).getText().toString();
        String password = ((EditText) mActivity.findViewById(R.id.SignUp_Pass_edittext)).getText().toString();
        String confPassword = ((EditText) mActivity.findViewById(R.id.SignUp_rePass_edittext)).getText().toString();
        String birthday = ((Button) mActivity.findViewById(R.id.SignUp_birthday_Button)).getText().toString();
        String location = ((Spinner) mActivity.findViewById(R.id.SignUp_location_Spinner)).getSelectedItem().toString();
        String gender = ((Spinner) mActivity.findViewById(R.id.SignUp_gender_Spinner)).getSelectedItem().toString();
        String firstName = ((EditText) mActivity.findViewById(R.id.SignUp_firstName_edittext)).getText().toString();
        String lastName = ((EditText) mActivity.findViewById(R.id.SignUp_lastName_edittext)).getText().toString();

        //getting full name from first and last name
        String name = firstName + " " + lastName;

        /***************************************************************************************/


        //constructing the parameters
        parameters += "email=" + email +  "&fullName=" + name + "&password=" + password
                    + "&password_confirmation=" + confPassword + "&gender=" + gender
                    + "&location=" + location + "&birthday=" + birthday;
        return parameters;
    }

    //endregion

    //region settings

    public String getChangeBirthdayParameters() {
        String parameters = "";

        String birthday = ((Button) mActivity.findViewById(R.id.Birthday_Button)).getText().toString();

        parameters += "newBirthday=" + birthday;

        return parameters;
    }

    public String getWhoCanSeeMyBirthdayParameters() {
        String parameters = "";

        //getting the radio buttons for who can see my birth day
        /***************************************************************************************/
        RadioButton everyone = ((RadioButton) mActivity.findViewById(R.id.Birthday_everyone_RadioButton));
        RadioButton onlyme = ((RadioButton) mActivity.findViewById(R.id.Birthday_onlyMe_RadioButton));
        /***************************************************************************************/

        //checking which radio button is clicked
        /******************************************/
        String whoCanSeeMyBirthday ="";
        if(everyone.isChecked()) {
            whoCanSeeMyBirthday = "Everyone";
        } else {
            whoCanSeeMyBirthday = "onlyMe";
        }
        /*****************************************/


        //constructing the parameters
        parameters += "seeMyBirthday=" + whoCanSeeMyBirthday;

        return parameters;
    }

    public String getChangeLocationParameters() {
        String parameters = "";

        String location = ((Spinner) mActivity.findViewById(R.id.Location_spinner)).getSelectedItem().toString();

        parameters += "location=" + location;

        return parameters;
    }

    public String getWhoCanSeeMyLocationParameters() {
        String parameters = "";

        //getting the radio buttons for who can see my birth day
        /***************************************************************************************/
        RadioButton everyone = ((RadioButton) mActivity.findViewById(R.id.Location_everynone_RadioButton));
        RadioButton onlyme = ((RadioButton) mActivity.findViewById(R.id.Location_onlyMe_RadioButton));
        /***************************************************************************************/

        //checking which radio button is clicked
        /******************************************/
        String whoCanSeeMyLocation ="";
        if(everyone.isChecked()) {
            whoCanSeeMyLocation = "Everyone";
        } else {
            whoCanSeeMyLocation = "onlyMe";
        }
        /*****************************************/


        //constructing the parameters
        parameters += "seeMyCountry=" + whoCanSeeMyLocation;

        return parameters;
    }
}







