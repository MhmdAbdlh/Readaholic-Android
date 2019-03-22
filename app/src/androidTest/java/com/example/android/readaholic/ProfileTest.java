package com.example.android.readaholic;

import android.support.test.espresso.Espresso;

import com.example.android.readaholic.profile_and_profile_settings.ProfileFragment;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ProfileTest {


        @Rule
        public ActivityT<ProfileFragment> mSignInTestRule =
                new ActivityTestRule<ProfileFragment>(ProfileFragment.class);

        @Test
        public void userNameTest()
        {
/*        Pattern userName = Pattern.compile("^[a-z0-9_-]{6,14}$");
        Matcher matcher = userName.matcher(user.getmUserName());
        assertEquals(null,matcher.matches());
*/
            Espresso.onView(withId(R.id.ProfileActivity_UserName_TextView))
                    .check(matches(withText("Hossam ahmed")));

        }

 /*       @Test
        public void imageUrlTest()
        {

            assertEquals(null, Patterns.WEB_URL.matcher(user.getmUserImageUrl()).matches());
        }
        @Test
        public void bookNumberTest()
        {
            Users user = helper.getmUser();
            assertEquals(9,user.getmUsernumberOfBooks());
        }

*/

}
