package com.example.android.readaholic;

import android.support.v4.app.Fragment;

import com.example.android.readaholic.VolleyHelper.volleyRequestHelper;
import com.example.android.readaholic.profile_and_profile_settings.ProfileFragment;
import com.example.android.readaholic.profile_and_profile_settings.Users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ExampleUnitTest  {

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

    @Test
    public void testVolleyUserName()
    {
        Users testUser = new Users();
        testUser = volleyRequestHelper.;

        user.setmUserName("Hossam Ahmed");
        assertEquals(user.getmUserName(),testUser.getmUserName());

    }



    @Test
    public void testVolleyUserImageView()
    {
        Users testUser = new Users();
        testUser = volleyRequestHelper.getmUser();

        user.setmUserImageUrl("https://s.gr-assets.com/assets/nophoto/user/u_111x148-9394ebedbb3c6c218f64be9549657029.png");
        assertEquals(user.getmUserImageUrl(),testUser.getmUserImageUrl());

    }


    @Test
    public void testVolleyUserNumberOfBooks()
    {
        Users testUser = new Users();
        testUser = volleyRequestHelper.getmUser();
        user.setmUsernumberOfBooks(7);
        assertEquals(user.getmUsernumberOfBooks(),testUser.getmUsernumberOfBooks());
    }




}
