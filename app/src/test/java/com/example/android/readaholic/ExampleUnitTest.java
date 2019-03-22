package com.example.android.readaholic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*volleyRequestHelper helper;
    protected  Assert anAssert ;
    Pattern userName = Pattern.compile("^[a-z0-9_-]{6,14}$");

    @Test
    public void userNameTest()
    {
        Users user = helper.getmUser();
        Matcher matcher = userName.matcher(user.getmUserName());
        assertEquals(null,matcher.matches());

    }
    @Test
    public void imageUrlTest()
    {
        Users user = helper.getmUser();
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
