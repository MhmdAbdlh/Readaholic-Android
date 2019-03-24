package com.example.android.readaholic;

import android.content.Intent;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.readaholic.VolleyHelper.volleyRequestHelper;
import com.example.android.readaholic.profile_and_profile_settings.Profile;
import com.example.android.readaholic.profile_and_profile_settings.Users;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
/*
public class RecyclerViewTest {
    @ClassRule
    public static ActivityTestRule<Profile> mProfileTestRule =
            new ActivityTestRule<Profile>(Profile.class);
    public static com.example.android.readaholic.VolleyHelper.volleyRequestHelper volleyRequestHelper = new volleyRequestHelper(mProfileTestRule.getActivity());
    public static ArrayList<Users> users;
    @Before
    public void setup()
    {
        Intent MY_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), Profile.class);
        mProfileTestRule.launchActivity(MY_ACTIVITY_INTENT);
        users = new ArrayList<>();
        users = volleyRequestHelper.getUsersListofFollowings();
    }

    @Test
    public void DataIsExist() {
        onView(new RecyclerViewMatcher(R.id.FollowersFragment_FollowersList_RecyclerView).
                atPosition(0)).check(matches((isDisplayed())));
    }
    /*
    private static Matcher<View> isCorrectData()
    {
        return new TypeSafeMatcher<View>() {
            private final ArrayList<String> usersName = new ArrayList<>();
            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView recyclerView = (RecyclerView)item;
                FollowingLiastAdapter testAdpater = (FollowingLiastAdapter) recyclerView.getAdapter();
                usersName.addAll(extractUsers(users));
                return (usersName.isEmpty());
            }

            private ArrayList<String> extractUsers(ArrayList<Users> users)
            {
                ArrayList<String> names = new ArrayList<>();
                for(Users users1:users)
                {
                    names.add(users1.getmUserName());
                }
                return names;
            }
            @Override
            public void describeTo(Description description) {

            }
        };
    }
*/
    public class RecyclerViewMatcher {
        private final int recyclerViewId;

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException var4) {
                            idDescription = String.format("%s (resource name not found)",
                                    new Object[] { Integer.valueOf(recyclerViewId) });
                        }
                    }

                    description.appendText("with id: " + idDescription);
                }

                public boolean matchesSafely(View view) {

                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView =
                                (RecyclerView) view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            childView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
                        }
                        else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }

                }
            };
        }
    }

}
