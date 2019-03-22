package com.example.android.readaholic;


import com.example.android.readaholic.books.BookPageActivity;
import com.example.android.readaholic.books.BookReviewsActivity;

import org.junit.Test;

import android.util.Patterns;

import com.example.android.readaholic.VolleyHelper.volleyRequestHelper;
import com.example.android.readaholic.profile_and_profile_settings.Users;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
@RunWith(BlockJUnit4ClassRunner.class)
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    volleyRequestHelper helper;
    protected Assert anAssert;
    Users user;

    public ExampleUnitTest() {
        user = helper.getmUser();

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void DateFormattcorrect() throws Exception {
        BookReviewsActivity b = new BookReviewsActivity();
        String s = b.checkformat("123123214235");
        assertEquals(s, "2019-12-12");

    }


    @Test
    public void notnigative() throws Exception {
        BookReviewsActivity b = new BookReviewsActivity();
        int s = b.checknotnigativeintegers(-5);
        assertEquals(s, 1);
    }

        @Test
        public void userNameTest ()
        {
/*        Pattern userName = Pattern.compile("^[a-z0-9_-]{6,14}$");
        Matcher matcher = userName.matcher(user.getmUserName());
        assertEquals(null,matcher.matches());
*/
//        Espresso.onView(withId(R.id.SignIn_error_textview))
            //              .check(matches(withText("Please check your email and password")));

        }
        @Test
        public void imageUrlTest ()
        {

            assertEquals(null, Patterns.WEB_URL.matcher(user.getmUserImageUrl()).matches());
        }
        @Test
        public void bookNumberTest ()
        {
            Users user = helper.getmUser();
            assertEquals(9, user.getmUsernumberOfBooks());

        }

    }

