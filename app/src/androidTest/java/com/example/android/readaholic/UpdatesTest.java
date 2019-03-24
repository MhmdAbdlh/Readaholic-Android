package com.example.android.readaholic;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;

import com.example.android.readaholic.books.BookReviewsActivity;
import com.example.android.readaholic.home.UpdatePage;
import com.example.android.readaholic.home.UpdatePageFragment;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesActivity;
import com.example.android.readaholic.sign_in_up.SignIn;

import org.hamcrest.Matcher;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.dagger.internal.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class UpdatesTest {
    @Rule
    public ActivityTestRule<Main> activityTestRule =
            new ActivityTestRule<>(Main.class);



    Main mainActivity;
    HomeFragment homeFragment;
    @Before
    public void yourSetUPFragment() {
        activityTestRule.getActivity()
                .getFragmentManager().beginTransaction();
    }

    private Matcher<? extends Object> withItemContent(String comment) {
        checkNotNull(comment);
        return withItemContent(equalTo(comment));
    }

    private void startFragment(HomeFragment homeFragment) {
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(homeFragment, null );
        fragmentTransaction.commit();
    }

    @Test
    public void basicElements() throws JSONException {
        onView(withId(R.id.UpadtesActivity_updateslist_listview)).check(matches(isEnabled()));
        ArrayList<Updates> j = HomeFragment.onResposeAction(HomeFragment.jsonFile);
        for(int i = 0; i < j.size(); i++) {
            onData(allOf(is(instanceOf(Updates.class)))).atPosition(i+1).equals(j.get(i));
        }
    }

    @Test
    public void UpdatesNumber() throws JSONException {
        JSONObject i = new JSONObject(HomeFragment.jsonFile);
        ArrayList<Updates> j = HomeFragment.onResposeAction(HomeFragment.jsonFile);
        i = i.getJSONObject("updates");
        int l = i.getJSONArray("update").length()-1;
        assertEquals(l,j.size());
    }

    @Test
    public void constructortest() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        assertEquals(item.getmTypeOfUpdates(), 0);
        assertEquals(item.getmNameOfUser(), "Salma");
        assertEquals(item.getmDateOfUpdates(), "11-10");
        assertEquals(item.getmNumOfLikes(), 4);
        assertEquals(item.getmNumOfComments(), 4);
        assertEquals(item.getmUserId(), 100);
    }
    @Test
    public void authorName() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmAuthorName("aa");
        assertEquals(item.getmAuthorName(), "aa");
    }
    @Test
    public void innerUser() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmInnerUserId(1);
        assertEquals(item.getmInnerUserId(), 1);
    }

    @Test
    public void shelf() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmShelf("reading");
        assertEquals(item.getmShelf(), "reading");
    }

    @Test
    public void bookName() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmBookName("The Three Sister");
        assertEquals(item.getmBookName(), "The Three Sister");
    }

    @Test
    public void innerDate() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmInnerDate("11-10");
        assertEquals(item.getmInnerDate(), "11-10");
    }

    @Test
    public void follow() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmNameofFollow("salma");
        assertEquals(item.getmNameofFollow(), "salma");
    }
    @Test
    public void rating() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmRatingValue(5);
        assertEquals(item.getmRatingValue(), 5);
    }

    @Test
    public void comment() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmComment("great");
        assertEquals(item.getmComment(), "great");
    }

    @Test
    public void bookID() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmBookId(9);
        assertEquals(item.getmBookId(), 9);
    }
    @Test
    public void review() {
        Updates item = new Updates(0, "Salma", "11-10", 4, 4, 100);
        item.setmReview("great");
        assertEquals(item.getmReview(), "great");
    }
    @Test
    public void testMainActivity() {
        Assert.assertNotNull(activityTestRule);
    }

  /*  @Test
   public void dataview() {
        HomeFragment fragment = new HomeFragment();
        ListView list = (ListView) fragment.getView().findViewById(R.id.UpadtesActivity_updateslist_listview);
        assertNotNull(list);
    }*/

   /*  @Test
   public void updateview(){
        UpdatePageFragment fragment = new UpdatePageFragment();
        ListView list1 = (ListView) fragment.getView().findViewById(R.id.UpdatePage_listview);
        assertNotNull(list1);
    }

    @Test
   public void validData() throws JSONException {
        JSONObject i = new JSONObject(HomeFragment.jsonFile);
        ArrayList<Updates> j = HomeFragment.onResposeAction(HomeFragment.jsonFile);
        i = i.getJSONObject("updates");
        assertEquals(i.getJSONArray("update").length(),j.size());
    }*/
}
