package com.example.android.readaholic.profile_and_profile_settings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.readaholic.CircleTransform;
import com.example.android.readaholic.R;
import com.example.android.readaholic.VolleyHelper.volleyRequestHelper;
import com.squareup.picasso.Picasso;

/**
 * ProfileFragment class to implement fragment behaviour
 * @author Hossam Ahmed
 */
public class ProfileFragment extends Fragment implements ProfileView {

    private String mRequestUrl;
    private int mUser_Id;
    private Users mProfileUser;
  //  @BindView(R.id.profileActivity_ProfilePic_ImageView)
    private ImageView mUserImage;
   // @BindView(R.id.ProfileActivity_UserName_TextView)
    private TextView mUserName;
   // @BindView(R.id.ProfileActivity_UserBooksNumber_TextView)
    private TextView mUserBookNumber;
    private ProfilePresenter mProfilePresenter;
    private View view;
    com.example.android.readaholic.VolleyHelper.volleyRequestHelper volleyRequestHelper ;
    /**
     * OnCreateView calling when fragment view is called to inflate the layout
     * @param inflater to inflate the layout
     * @param container View group to hold the parent
     * @param savedInstanceState Bundle to have the previous data
     * @return View to inflate
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment,container,false);
        mUserImage = (ImageView)view.findViewById(R.id.profileActivity_ProfilePic_ImageView);
        mUserName =(TextView)view.findViewById(R.id.ProfileActivity_UserName_TextView);
        mUserBookNumber = (TextView)view.findViewById(R.id.ProfileActivity_UserBooksNumber_TextView);
        volleyRequestHelper = new volleyRequestHelper(view.getContext());

//        ButterKnife.bind(this,view);

        mProfilePresenter = new ProfilePresenter(view.getContext(),this);
        mRequestUrl = "https://api.myjson.com/bins/1dujwm";
        mProfileUser = mProfilePresenter.fetchData(mRequestUrl);


        UpdateData(mProfileUser);

        //Loading Fragments
        loadFragment(new books(),view.findViewById(R.id.Profile_Books_Fragment).getId());
        loadFragment(new Followers_fragment(),view.findViewById(R.id.Profile_Friends_Fragment).getId());
        loadFragment(new Updates_fragment(),view.findViewById(R.id.Profile_Updates_Fragment).getId());


        return view;

    }

    /**
     * function to return context of View
     * @return Context of view
     */
    @Nullable
    @Override
    public Context getViewContext() {
        return view.getContext();
    }

    /**
     * destory the view
     */
    @Override
    public void onDestroy() {
        mProfilePresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * loadFragment function to initialize the Fragment
     * @param fragment Fragment object to contain the layout.
     * @param ID id of the layout.
     * to initialize the Fragment argument and replace FrameLayout with it.
     * and id to assign it to certain fragment layout.
     */
    private void loadFragment(Fragment fragment,int ID) {
        // create a FragmentManager
        FragmentManager fm = getActivity().getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.add(ID, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    /**
     * implementation of UpdateDate from ProfileView interface.
     */
    @Override
    public void UpdateData(Users user) {

        Picasso.get().load(user.getmUserImageUrl()).transform(new CircleTransform()).into(mUserImage);
        mUserBookNumber.setText(user.getmUsernumberOfBooks()+" Books");
        mUserName.setText(user.getmUserName());

    }

}
