package com.example.android.readaholic;

import com.example.android.readaholic.books.BookPageActivity;
import com.example.android.readaholic.books.BookReviewsActivity;

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

    @Test
    public void DateFormattcorrect() throws Exception
    {
        BookReviewsActivity b=new BookReviewsActivity();
        String s=b.checkformat("123123214235");
       assertEquals(s,"2019-12-12");

    }


    @Test
    public void notnigative() throws Exception
    {
        BookReviewsActivity b=new BookReviewsActivity();
        int s=b.checknotnigativeintegers(-5);
        assertEquals(s,1);

    }

}
