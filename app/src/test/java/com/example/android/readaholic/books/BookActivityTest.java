package com.example.android.readaholic.books;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class BookActivityTest {


    @Test
    public void DateFormattcorrect() throws Exception {
        BookReviewsActivity b = new BookReviewsActivity();
        String s = b.checkformat("123123214235");
        assertEquals(s, "2019-12-12");
        s = b.checkformat("24-03-2019");
        assertEquals(s, "24-03-2019");
    }
    @Test
    public void nonNigative() throws  Exception
    {
        BookReviewsActivity b = new BookReviewsActivity();
        int i=b.checknotnigativeintegers(5);
        assertEquals(i,5);
        i=b.checknotnigativeintegers(-10);
        assertEquals(i,1);

    }

    @Test
    public void myreview() throws  Exception
    {
        BookPageActivity b = new BookPageActivity();
        String test1="{\"status\":\"success\",\"pages\":[{\"id\":4,\"rating\":-1,\"shelf_name\":5,\"body\":\"Mahazaaaaq\"}]}";
        String test2="{\"status\":\"success\",\"pages\":[{\"id\":5,\"rating\":-2,\"shelf_name\":5,\"body\":\"Mahazaaaaq\"}]}";
        JSONObject  t1=new JSONObject(test1);
        b.myReview(t1);
     //   assertEquals(Cbookdata.INSTANCE.getShelf(),1);
     //   assertEquals(Cbookdata.INSTANCE.getShelf(),1);
      //  assertEquals(Cbookdata.INSTANCE.getShelf(),1);
       // assertEquals(Cbookdata.INSTANCE.getReviewid(),1);
    }



    @Test
    public void getbookInfotest() throws Exception {
        BookPageInfo b = new BookPageInfo();
        b.setAuthor_id(1);
        assertEquals(b.getAuthor_id(), 1);
        b.setIsbn(4);
        assertEquals(b.getIsbn(), 4);
        b.setAuthor_name("Ahmed");
        assertEquals(b.getAuthor_name(), "Ahmed");
        b.setBook_title("Prince");
        assertEquals(b.getBook_title(), "Prince");
        b.setImage_url("http/ahmed.jpg");
        assertEquals(b.getImage_url(), "http/ahmed.jpg");
        b.setNum_pages(8);
        assertEquals(b.getNum_pages(), 8);
        b.setRatings_count(100);
        assertEquals(b.getRatings_count(), 100);
        b.setGenre("Action");
        assertEquals(b.getGenre(), "Action");
    }



}
