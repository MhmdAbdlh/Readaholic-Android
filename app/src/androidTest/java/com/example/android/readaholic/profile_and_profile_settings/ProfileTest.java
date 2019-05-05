package com.example.android.readaholic.profile_and_profile_settings;

import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.example.android.readaholic.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProfileTest {
    public String authUserShowProfileResponse="{\"id\":1,\"name\":\"test\",\"username\":\"test\",\"email\":\"test@yahoo.com\",\"email_verified_at\":null,\"link\":null,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"small_image_link\":null,\"about\":null,\"age\":21,\"gender\":\"female\",\"country\":\"Canada\",\"city\":\"Atawwa\",\"joined_at\":\"2019-05-03\",\"followers_count\":-2,\"following_count\":-1,\"rating_avg\":5,\"rating_count\":21,\"book_count\":0,\"birthday\":\"1998-02-21\",\"see_my_birthday\":\"Everyone\",\"see_my_country\":\"Everyone\",\"see_my_city\":\"Everyone\",\"forgot_password_token\":null,\"verified_token\":null,\"verified\":0,\"created_at\":null,\"updated_at\":null}";
    public String UserShowProfileResponse="{\"id\":5,\"name\":\"Salma\",\"username\":\"Salma\",\"email\":\"Salma@yahoo.com\",\"email_verified_at\":null,\"link\":null,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"small_image_link\":null,\"about\":null,\"age\":21,\"gender\":\"female\",\"country\":\"Egypt\",\"city\":\"Cairo\",\"joined_at\":\"2019-05-03\",\"followers_count\":0,\"following_count\":0,\"rating_avg\":0,\"rating_count\":0,\"book_count\":0,\"birthday\":\"1998-02-21\",\"see_my_birthday\":\"Everyone\",\"see_my_country\":\"Everyone\",\"see_my_city\":\"Everyone\",\"forgot_password_token\":\"eyJpdiI6ImV3Qjlqb2cyVjh3UG1FbHRDS0F2YWc9PSIsInZhbHVlIjoiSVQ4elQwVVZ6YzlMOStrclJaTHBoQT09IiwibWFjIjoiMDdiNTQyY2MyMzkxMzE4ZjU2ODkyMzJiNmI5NjlmOTA4ZGUzZWI1MzQ4YTQ3YjQzODhjYmJhNzEzMzEwNGVkNCJ9\",\"verified_token\":null,\"verified\":0,\"created_at\":null,\"updated_at\":null,\"is_followed\":1}";

    @Rule
    public ActivityTestRule<Profile> activityTestRule =
            new ActivityTestRule<>(Profile.class);

    private ProfileFragment fragment = null;

@Before
    public void yourSetUPFragment() {
        activityTestRule.getActivity()
                .getFragmentManager().beginTransaction();
    }

    private void startFragment(ProfileFragment profileFragment) {
        fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
         bundle.putInt("user-idFromFollowingList",0);
        bundle.putBoolean("followingState",true);

        FragmentManager fragmentManager = activityTestRule.getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null );
        fragmentTransaction.replace(R.id.ProfileLayout,
                fragment).commit();
    }

    @Test
    public void testlist(){
        JSONObject response1 = null;
        try {
            response1 = new JSONObject(authUserShowProfileResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        startFragment(fragment);

        Users user =fragment.ExtractUser(response1);
        fragment.UpdateData(user,0);
        TextView Name =(TextView)fragment.getView().findViewById(R.id.ProfileActivity_UserName_TextView);
        assertEquals("test",Name.getText());
    }
}