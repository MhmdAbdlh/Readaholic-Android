package com.example.android.readaholic;

import com.example.android.readaholic.VolleyHelper.volleyRequestHelper;
import com.example.android.readaholic.books.BookReviewsActivity;
import com.example.android.readaholic.profile_and_profile_settings.Users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
@RunWith(RobolectricTestRunner.class)
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

/*    volleyRequestHelper helper;
    protected Assert anAssert ;
    Users user;

    public ExampleUnitTest()
    {
        user = helper.getmUser();

    }
*/
private Users user;
private volleyRequestHelper volleyRequestHelper;

@Before
public void init()
{
    user = new Users();
    volleyRequestHelper = new volleyRequestHelper();
}
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testUserName()
    {
        user.setmUserName("Hossam Ahmed");
        assertEquals("Hossam Ahmed",user.getmUserName());
    }
    @Test
    public void testUserImageurl()
    {
        user.setmUserImageUrl("https://images.gr-assets.com/users/1551035887p3/27948863.jpg");
        assertEquals("https://images.gr-assets.com/users/1551035887p3/27948863.jpg",user.getmUserImageUrl());
    }
    @Test
    public void testUserNumberOfBooks()
    {
        user.setmUsernumberOfBooks(10);
        assertEquals(10,user.getmUsernumberOfBooks());
    }
    @Test
    public void testUserNumberOfFollowers()
    {
        user.setmNumberOfFollowers(20);
        assertEquals(20,user.getmNumberOfFollowers());
    }

    @Test
    public void testUserNumberOfFollowings()
    {
        user.setGetmNumberOfFolloweings(20);
        assertEquals(20,user.getGetmNumberOfFolloweings());
    }

    @Test
    public void testConstructorofUser()
    {
      Users testUser = new Users("Hossam Ahmed","https://images.gr-assets.com/users/1551035887p3/27948863.jpg",10);
      user.setmUserName("Hossam Ahmed");
      user.setmUsernumberOfBooks(10);
      user.setmUserImageUrl("https://images.gr-assets.com/users/1551035887p3/27948863.jpg");
      assertEquals(user.getmUserName(),testUser.getmUserName());
      assertEquals(user.getmUserImageUrl(),testUser.getmUserImageUrl());
      assertEquals(user.getmUsernumberOfBooks(),testUser.getmUsernumberOfBooks());
    }

    @Test
    public void testVolleyUser()
    {
        Users testUser = new Users();
        testUser = volleyRequestHelper.getmUser();

        user.setmUserName("Hossam Ahmed");
        assertEquals(user.getmUserName(),testUser.getmUserName());

    }

    @Test
    public void testNotCorrectVolleyUser()
    {
        Users testUser = new Users();
        testUser = volleyRequestHelper.getmUser();

        user.setmUserName("Ahmed");
        assertEquals(user.getmUserName(),testUser.getmUserName());

    }


    /*

    @Test
    public void userNameTest()
    {
        Pattern userName = Pattern.compile("^[a-z0-9_-]{6,14}$");
        Matcher matcher = userName.matcher(user.getmUserName());
        assertEquals(null,matcher.matches());

        Espresso.onView(withId(R.id.SignIn_error_textview))
                .check(matches(withText("Please check your email and password")));

    }
    @Test
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

}
