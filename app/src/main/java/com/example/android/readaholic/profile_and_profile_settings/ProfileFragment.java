package com.example.android.readaholic.profile_and_profile_settings;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    private RequestQueue mRequestQueue;
    private String mRequestUrl;
    private int mUser_Id;
    private  Users mProfileUser;
    private ImageView mUserImage;
    private TextView mUserName;
    private TextView mUserBookNumber;
    private TextView profileSettingtofollowingState;
    private ImageView profilerEditToRightSign;
    private static ProgressDialog mProgressDialog;

    ArrayList<Updates> arayOfUpdates = new ArrayList<Updates>();
    private ListView mListOfUpdates;
    private UpdatesAdapter adapterForUpdatesList;
    private int userFollowingState;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment,container,false);
        mUserImage = (ImageView)view.findViewById(R.id.profileActivity_ProfilePic_ImageView);
        mUserName =(TextView)view.findViewById(R.id.ProfileActivity_UserName_TextView);
        mUserBookNumber = (TextView)view.findViewById(R.id.ProfileActivity_UserBooksNumber_TextView);
        profilerEditToRightSign = (ImageView)view.findViewById(R.id.Profile_Settings_ImageView);
        profileSettingtofollowingState = (TextView)view.findViewById(R.id.Profile_Settings_TextView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mUser_Id = bundle.getInt("UserId");
        }

        if(savedInstanceState == null) {
            if (getArguments() == null)
                mUser_Id = 0;
            else {
                mUser_Id = getArguments().getInt("user-id");
                userFollowingState = getArguments().getInt("followingState");
            }
            Log.e("userid", Integer.toString(mUser_Id));
            requestProfileView(mUser_Id);
        }

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * loadFragment function to initialize the Fragment
     * @param fragment Fragment object to contain the layout.
     * @param ID id of the layout.
     * to initialize the Fragment argument and replace FrameLayout with it.
     * and id to assign it to certain fragment layout.
     */
    private void loadFragment(Fragment fragment,int ID,int userID) {
        // create a FragmentManager
        FragmentManager fm = getActivity().getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("user-id",userID);
        if(ID == view.findViewById(R.id.Profile_Books_Fragment).getId())
            bundle.putInt("books-num",mProfileUser.getmUsernumberOfBooks());
        else if(ID == view.findViewById(R.id.Profile_Friends_Fragment).getId())
        {
            bundle.putInt("followers-num",mProfileUser.getmNumberOfFollowers());
            bundle.putInt("followers-num",mProfileUser.getGetmNumberOfFolloweings());
        }
        fragment.setArguments(bundle);
        // replace the FrameLayout with new Fragment
        fragmentTransaction.add(ID, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    /**
     *Update the view with data of user
     * @param user holding data from request.
     */
    public void UpdateData(Users user ,int id) {
        removeSimpleProgressDialog();
        if(id != 0)
        {
            if(userFollowingState == 1)//user is following this profile.
                {
                    profileSettingtofollowingState.setText(" FOLLOWING");
                    profilerEditToRightSign.setImageResource(R.drawable.check);
                }
                else //user is not following this profile user.
                    {
                        profileSettingtofollowingState.setText(" FOLLOW");
                        profilerEditToRightSign.setImageResource(R.drawable.check);
                    }

        }
        else
        {

        }

            Picasso.get().load(user.getmUserImageUrl()).transform(new CircleTransform()).into(mUserImage);
        mUserBookNumber.setText(user.getmUsernumberOfBooks()+" Books");
        Log.e("UserNameInProfile",user.getmUserName());

        mUserName.setText(user.getmUserName());

    }

    /**
     * function to doing the request to get user info and update the view with it.
     */
    public void requestProfileView(final int user_id)
{
    if(user_id != 0)
    mRequestUrl =Urls.ROOT + "/api/showProfile?"+"id="+Integer.toString(user_id)+"&token="+ UserInfo.sToken+"&type="+ UserInfo.sTokenType;

    else
        mRequestUrl = Urls.ROOT  + "/api/showProfile?"+"token="+ UserInfo.sToken+"&type="+ UserInfo.sTokenType;

    mProfileUser = new Users();
    DiskBasedCache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024);
    BasicNetwork network = new BasicNetwork(new HurlStack());
    mRequestQueue = new RequestQueue(cache, network);
    mRequestQueue.start();
    showSimpleProgressDialog(getContext(),"Loading.....","Loading Profile",false);
    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mRequestUrl, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject Response) {

            ExtractUser(Response);
            UpdateData(mProfileUser,user_id);

            //Loading Fragments
            loadFragment(new books(),view.findViewById(R.id.Profile_Books_Fragment).getId(),user_id);
            loadFragment(new Followers_fragment(),view.findViewById(R.id.Profile_Friends_Fragment).getId(),user_id);
            loadFragment(new Updates_fragment(),view.findViewById(R.id.Profile_Updates_Fragment).getId(),user_id);

            mRequestQueue.stop();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            //Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
           // mProfileUser=null;

            mProfileUser=null;
            mRequestQueue.stop();
        }
    });

    mRequestQueue.add(jsonObjectRequest);
}

    /**
     * extract user info from response.
     * @param Response
     * @return user object holding the user data.
     */
    public Users ExtractUser(JSONObject Response)
{
    Users users = new Users();
    Log.e("profileResponse",Response.toString());
    users.setmUserName(Response.optString("name"));
    Log.e("Test" ,users.getmUserName());
    users.setmUserImageUrl(Response.optString("image_link"));
    users.setmUsernumberOfBooks(Response.optInt("books_count"));
    users.setmNumberOfFollowers(Response.optInt("followers_count"));
    users.setGetmNumberOfFolloweings(Response.optInt("following_count"));
    Log.e("showprofileResponseName",users.getmUserName());
    mProfileUser = users;
    return users;

}

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
