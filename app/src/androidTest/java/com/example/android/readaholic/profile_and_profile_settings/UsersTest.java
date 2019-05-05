package com.example.android.readaholic.profile_and_profile_settings;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UsersTest {

private Users user;

@Before
public void init()
{
    user = new Users();
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
    public void testUserImageUrl()
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
    public void testConstructorOfUser()
    {
      Users testUser = new Users("Hossam Ahmed","https://images.gr-assets.com/users/1551035887p3/27948863.jpg",10);
      user.setmUserName("Hossam Ahmed");
      user.setmUsernumberOfBooks(10);
      user.setmUserImageUrl("https://images.gr-assets.com/users/1551035887p3/27948863.jpg");
      assertEquals(user.getmUserName(),testUser.getmUserName());
      assertEquals(user.getmUserImageUrl(),testUser.getmUserImageUrl());
      assertEquals(user.getmUsernumberOfBooks(),testUser.getmUsernumberOfBooks());
    }

}
