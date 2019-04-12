package com.example.android.readaholic;

//@RunWith(AndroidJUnit4.class)
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
/*
public class RecyclerViewMatcher {
 */
