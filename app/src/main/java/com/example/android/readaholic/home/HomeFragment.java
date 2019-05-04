package com.example.android.readaholic.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.readaholic.R;
import com.example.android.readaholic.contants_and_static_data.Urls;
import com.example.android.readaholic.contants_and_static_data.UserInfo;
import com.example.android.readaholic.home.Updates;
import com.example.android.readaholic.home.UpdatesAdapter;
import com.example.android.readaholic.profile_and_profile_settings.Followers_fragment;
import com.example.android.readaholic.profile_and_profile_settings.ProfileFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public static ArrayList<Updates> arrayOfUpadates1 = new ArrayList<Updates>();
    public static Context context;
    private static RequestQueue queue ;
    static UpdatesAdapter adapter = null;
    static ProgressBar loading ;
    static ListView listUpadtes;
    static SwipeRefreshLayout refresh;
    View view;
    public static String jsonFile = "[{\"id\":28,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":4,\"name\":\"Nour\",\"followed_id\":5,\"followed_name\":\"Salma\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":4,\"body\":\"NzY1KE4hwW\",\"rating\":1,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":2,\"title\":\"Sherwood\",\"description\":\"Robin of Locksley is dead.\\n            Maid Marian doesn\\u2019t know how she\\u2019ll go on, but the people of Locksley town, persecuted by the Sheriff of Nottingham, need a protector. And the dreadful Guy of Gisborne, the Sheriff\\u2019s right hand, wishes to step into Robin\\u2019s shoes as Lord of Locksley and Marian\\u2019s fianc\\u00e9.\\n            Who is there to stop them?\\n            Marian never meant to tread in Robin\\u2019s footsteps\\u2014never intended to stand as a beacon of hope to those awaiting his triumphant return. But with a sweep of his green cloak and the flash of her sword, Marian makes the choice to become her own hero: Robin Hood. \",\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":40,\"ratings_count\":40,\"ratings_avg\":3.67,\"pages_no\":0,\"user_id\":4,\"name\":\"Nour\",\"author_name\":\"Meagan Spooner\",\"update_type\":0,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":6,\"body\":\"amYagyPR7A\",\"rating\":5,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":2,\"title\":\"Sherwood\",\"description\":\"Robin of Locksley is dead.\\n            Maid Marian doesn\\u2019t know how she\\u2019ll go on, but the people of Locksley town, persecuted by the Sheriff of Nottingham, need a protector. And the dreadful Guy of Gisborne, the Sheriff\\u2019s right hand, wishes to step into Robin\\u2019s shoes as Lord of Locksley and Marian\\u2019s fianc\\u00e9.\\n            Who is there to stop them?\\n            Marian never meant to tread in Robin\\u2019s footsteps\\u2014never intended to stand as a beacon of hope to those awaiting his triumphant return. But with a sweep of his green cloak and the flash of her sword, Marian makes the choice to become her own hero: Robin Hood. \",\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":40,\"ratings_count\":40,\"ratings_avg\":3.67,\"pages_no\":0,\"user_id\":3,\"name\":\"waleed\",\"author_name\":\"Meagan Spooner\",\"update_type\":0,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":7,\"body\":\"Xtgc4FFFhU\",\"rating\":5,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":2,\"title\":\"Sherwood\",\"description\":\"Robin of Locksley is dead.\\n            Maid Marian doesn\\u2019t know how she\\u2019ll go on, but the people of Locksley town, persecuted by the Sheriff of Nottingham, need a protector. And the dreadful Guy of Gisborne, the Sheriff\\u2019s right hand, wishes to step into Robin\\u2019s shoes as Lord of Locksley and Marian\\u2019s fianc\\u00e9.\\n            Who is there to stop them?\\n            Marian never meant to tread in Robin\\u2019s footsteps\\u2014never intended to stand as a beacon of hope to those awaiting his triumphant return. But with a sweep of his green cloak and the flash of her sword, Marian makes the choice to become her own hero: Robin Hood. \",\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":40,\"ratings_count\":40,\"ratings_avg\":3.67,\"pages_no\":0,\"user_id\":2,\"name\":\"ta7a\",\"author_name\":\"Meagan Spooner\",\"update_type\":0,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":11,\"body\":\"gGYj6XXMKr\",\"rating\":2,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":3,\"title\":\"Once & Future\",\"description\":\"I\\u2019ve been chased my whole life. As a fugitive refugee in the territory controlled by the tyrannical Mercer corporation, I\\u2019ve always had to hide who I am. Until I found Excalibur.\\n            Now I\\u2019m done hiding.\\n            My name is Ari Helix. I have a magic sword, a cranky wizard, and a revolution to start.   \\n            When Ari crash-lands on Old Earth and pulls a magic sword from its ancient resting place, she is revealed to be the newest reincarnation of King Arthur. Then she meets Merlin, who has aged backward over the centuries into a teenager, and together they must break the curse that keeps Arthur coming back. Their quest? Defeat the cruel, oppressive government and bring peace and equality to all humankind.\",\"img_url\":\"https:\\/\\/images-na.ssl-images-amazon.com\\/images\\/I\\/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\",\"reviews_count\":2,\"ratings_count\":2,\"ratings_avg\":3.57,\"pages_no\":0,\"user_id\":5,\"name\":\"Salma\",\"author_name\":\"Amy Rose Capetta\",\"update_type\":0,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":13,\"body\":\"FegvKn1OEJ\",\"rating\":4,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":2,\"title\":\"Sherwood\",\"description\":\"Robin of Locksley is dead.\\n            Maid Marian doesn\\u2019t know how she\\u2019ll go on, but the people of Locksley town, persecuted by the Sheriff of Nottingham, need a protector. And the dreadful Guy of Gisborne, the Sheriff\\u2019s right hand, wishes to step into Robin\\u2019s shoes as Lord of Locksley and Marian\\u2019s fianc\\u00e9.\\n            Who is there to stop them?\\n            Marian never meant to tread in Robin\\u2019s footsteps\\u2014never intended to stand as a beacon of hope to those awaiting his triumphant return. But with a sweep of his green cloak and the flash of her sword, Marian makes the choice to become her own hero: Robin Hood. \",\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":40,\"ratings_count\":40,\"ratings_avg\":3.67,\"pages_no\":0,\"user_id\":3,\"name\":\"waleed\",\"author_name\":\"Meagan Spooner\",\"update_type\":0,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":15,\"body\":\"KyHGXphyYw\",\"rating\":3,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":1,\"title\":\"The Bird King\",\"description\":\"New from the award-winning author of Alif the Unseen and writer of the Ms. Marvel series, G. Willow Wilson\\n             Set in 1491 during the reign of the last sultanate in the Iberian peninsula, \\n             The Bird King is the story of Fatima, the only remaining Circassian concubine to the sultan, and her dearest friend Hassan, the palace mapmaker. \\n              Hassan has a secret--he can draw maps of places he's never seen and bend the shape of reality.\\n              When representatives of the newly formed Spanish monarchy arrive to negotiate the sultan's surrender, Fatima befriends one of the women, not realizing that she will see Hassan's gift as sorcery and a threat to Christian Spanish rule. With their freedoms at stake,\\n               what will Fatima risk to save Hassan and escape the palace walls? As Fatima and Hassan traverse Spain with the help of a clever jinn to find safety, The Bird King asks us to consider what love is and the price of freedom at a time when the West and the Muslim world were not yet separate. \",\"img_url\":\"https:\\/\\/i5.walmartimages.com\\/asr\\/8bae6257-b3ed-43ba-b5d4-c55b6479697f_1.c6a36804e0a9cbfd0e408a4b96f8a94e.jpeg?odnHeight=560&odnWidth=560&odnBg=FFFFFF\",\"reviews_count\":0,\"ratings_count\":0,\"ratings_avg\":0,\"pages_no\":0,\"user_id\":4,\"name\":\"Nour\",\"author_name\":\"G. Willow Wilson\",\"update_type\":0,\"shelf\":3,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":17,\"body\":\"P4ZuDamEuE\",\"rating\":4,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":2,\"title\":\"Sherwood\",\"description\":\"Robin of Locksley is dead.\\n            Maid Marian doesn\\u2019t know how she\\u2019ll go on, but the people of Locksley town, persecuted by the Sheriff of Nottingham, need a protector. And the dreadful Guy of Gisborne, the Sheriff\\u2019s right hand, wishes to step into Robin\\u2019s shoes as Lord of Locksley and Marian\\u2019s fianc\\u00e9.\\n            Who is there to stop them?\\n            Marian never meant to tread in Robin\\u2019s footsteps\\u2014never intended to stand as a beacon of hope to those awaiting his triumphant return. But with a sweep of his green cloak and the flash of her sword, Marian makes the choice to become her own hero: Robin Hood. \",\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":40,\"ratings_count\":40,\"ratings_avg\":3.67,\"pages_no\":0,\"user_id\":5,\"name\":\"Salma\",\"author_name\":\"Meagan Spooner\",\"update_type\":0,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":18,\"body\":\"bKAWwhGGx6\",\"rating\":5,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":2,\"title\":\"Sherwood\",\"description\":\"Robin of Locksley is dead.\\n            Maid Marian doesn\\u2019t know how she\\u2019ll go on, but the people of Locksley town, persecuted by the Sheriff of Nottingham, need a protector. And the dreadful Guy of Gisborne, the Sheriff\\u2019s right hand, wishes to step into Robin\\u2019s shoes as Lord of Locksley and Marian\\u2019s fianc\\u00e9.\\n            Who is there to stop them?\\n            Marian never meant to tread in Robin\\u2019s footsteps\\u2014never intended to stand as a beacon of hope to those awaiting his triumphant return. But with a sweep of his green cloak and the flash of her sword, Marian makes the choice to become her own hero: Robin Hood. \",\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":40,\"ratings_count\":40,\"ratings_avg\":3.67,\"pages_no\":0,\"user_id\":4,\"name\":\"Nour\",\"author_name\":\"Meagan Spooner\",\"update_type\":0,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":19,\"body\":\"6cRMqKgoA8\",\"rating\":5,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":3,\"title\":\"Once & Future\",\"description\":\"I\\u2019ve been chased my whole life. As a fugitive refugee in the territory controlled by the tyrannical Mercer corporation, I\\u2019ve always had to hide who I am. Until I found Excalibur.\\n            Now I\\u2019m done hiding.\\n            My name is Ari Helix. I have a magic sword, a cranky wizard, and a revolution to start.   \\n            When Ari crash-lands on Old Earth and pulls a magic sword from its ancient resting place, she is revealed to be the newest reincarnation of King Arthur. Then she meets Merlin, who has aged backward over the centuries into a teenager, and together they must break the curse that keeps Arthur coming back. Their quest? Defeat the cruel, oppressive government and bring peace and equality to all humankind.\",\"img_url\":\"https:\\/\\/images-na.ssl-images-amazon.com\\/images\\/I\\/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\",\"reviews_count\":2,\"ratings_count\":2,\"ratings_avg\":3.57,\"pages_no\":0,\"user_id\":5,\"name\":\"Salma\",\"author_name\":\"Amy Rose Capetta\",\"update_type\":0,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":2,\"shelf_type\":2,\"updated_at\":\"2019-04-27 17:28:34\",\"likes_count\":null,\"comments_count\":null,\"book_id\":1,\"title\":\"The Bird King\",\"description\":\"New from the award-winning author of Alif the Unseen and writer of the Ms. Marvel series, G. Willow Wilson\\n             Set in 1491 during the reign of the last sultanate in the Iberian peninsula, \\n             The Bird King is the story of Fatima, the only remaining Circassian concubine to the sultan, and her dearest friend Hassan, the palace mapmaker. \\n              Hassan has a secret--he can draw maps of places he's never seen and bend the shape of reality.\\n              When representatives of the newly formed Spanish monarchy arrive to negotiate the sultan's surrender, Fatima befriends one of the women, not realizing that she will see Hassan's gift as sorcery and a threat to Christian Spanish rule. With their freedoms at stake,\\n               what will Fatima risk to save Hassan and escape the palace walls? As Fatima and Hassan traverse Spain with the help of a clever jinn to find safety, The Bird King asks us to consider what love is and the price of freedom at a time when the West and the Muslim world were not yet separate. \",\"img_url\":\"https:\\/\\/i5.walmartimages.com\\/asr\\/8bae6257-b3ed-43ba-b5d4-c55b6479697f_1.c6a36804e0a9cbfd0e408a4b96f8a94e.jpeg?odnHeight=560&odnWidth=560&odnBg=FFFFFF\",\"reviews_count\":0,\"ratings_count\":0,\"ratings_avg\":0,\"pages_no\":0,\"user_id\":3,\"name\":\"waleed\",\"author_name\":\"G. Willow Wilson\",\"update_type\":1,\"shelf\":3,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":3,\"shelf_type\":1,\"updated_at\":\"2019-04-27 17:28:34\",\"likes_count\":null,\"comments_count\":null,\"book_id\":2,\"title\":\"Sherwood\",\"description\":\"Robin of Locksley is dead.\\n            Maid Marian doesn\\u2019t know how she\\u2019ll go on, but the people of Locksley town, persecuted by the Sheriff of Nottingham, need a protector. And the dreadful Guy of Gisborne, the Sheriff\\u2019s right hand, wishes to step into Robin\\u2019s shoes as Lord of Locksley and Marian\\u2019s fianc\\u00e9.\\n            Who is there to stop them?\\n            Marian never meant to tread in Robin\\u2019s footsteps\\u2014never intended to stand as a beacon of hope to those awaiting his triumphant return. But with a sweep of his green cloak and the flash of her sword, Marian makes the choice to become her own hero: Robin Hood. \",\"img_url\":\"https:\\/\\/kbimages1-a.akamaihd.net\\/6954f4cc-6e4e-46e3-8bc2-81b93f57a723\\/353\\/569\\/90\\/False\\/sherwood-7.jpg\",\"reviews_count\":40,\"ratings_count\":40,\"ratings_avg\":3.67,\"pages_no\":0,\"user_id\":2,\"name\":\"ta7a\",\"author_name\":\"Meagan Spooner\",\"update_type\":1,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":1,\"shelf_type\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"likes_count\":null,\"comments_count\":null,\"book_id\":3,\"title\":\"Once & Future\",\"description\":\"I\\u2019ve been chased my whole life. As a fugitive refugee in the territory controlled by the tyrannical Mercer corporation, I\\u2019ve always had to hide who I am. Until I found Excalibur.\\n            Now I\\u2019m done hiding.\\n            My name is Ari Helix. I have a magic sword, a cranky wizard, and a revolution to start.   \\n            When Ari crash-lands on Old Earth and pulls a magic sword from its ancient resting place, she is revealed to be the newest reincarnation of King Arthur. Then she meets Merlin, who has aged backward over the centuries into a teenager, and together they must break the curse that keeps Arthur coming back. Their quest? Defeat the cruel, oppressive government and bring peace and equality to all humankind.\",\"img_url\":\"https:\\/\\/images-na.ssl-images-amazon.com\\/images\\/I\\/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\",\"reviews_count\":2,\"ratings_count\":2,\"ratings_avg\":3.57,\"pages_no\":0,\"user_id\":3,\"name\":\"waleed\",\"author_name\":\"Amy Rose Capetta\",\"update_type\":1,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":4,\"shelf_type\":2,\"updated_at\":\"2019-04-27 17:28:34\",\"likes_count\":null,\"comments_count\":null,\"book_id\":4,\"title\":\"Internment\",\"description\":\"Rebellions are built on hope.\\n            Set in a horrifying near-future United States, seventeen-year-old Layla Amin and her parents are forced into an internment camp for Muslim American citizens.\\n            With the help of newly made friends also trapped within the internment camp, her boyfriend on the outside, and an unexpected alliance, Layla begins a journey to fight for freedom, leading a revolution against the internment camp's Director and his guards.\\n            Heart-racing and emotional, Internment challenges readers to fight complicit silence that exists in our society today.\",\"img_url\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\",\"reviews_count\":0,\"ratings_count\":0,\"ratings_avg\":0,\"pages_no\":0,\"user_id\":3,\"name\":\"waleed\",\"author_name\":\"Samira Ahmed\",\"update_type\":1,\"shelf\":3,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":3,\"body\":\"4s6wvlvAf2\",\"rating\":3,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":3,\"title\":\"Once & Future\",\"description\":\"I\\u2019ve been chased my whole life. As a fugitive refugee in the territory controlled by the tyrannical Mercer corporation, I\\u2019ve always had to hide who I am. Until I found Excalibur.\\n            Now I\\u2019m done hiding.\\n            My name is Ari Helix. I have a magic sword, a cranky wizard, and a revolution to start.   \\n            When Ari crash-lands on Old Earth and pulls a magic sword from its ancient resting place, she is revealed to be the newest reincarnation of King Arthur. Then she meets Merlin, who has aged backward over the centuries into a teenager, and together they must break the curse that keeps Arthur coming back. Their quest? Defeat the cruel, oppressive government and bring peace and equality to all humankind.\",\"img_url\":\"https:\\/\\/images-na.ssl-images-amazon.com\\/images\\/I\\/51Jb2iLFuXL._SX329_BO1,204,203,200_.jpg\",\"reviews_count\":2,\"ratings_count\":2,\"ratings_avg\":3.57,\"pages_no\":0,\"user_id\":5,\"name\":\"Salma\",\"author_name\":\"Amy Rose Capetta\",\"update_type\":0,\"shelf\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":1,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":2,\"name\":\"ta7a\",\"followed_id\":1,\"followed_name\":\"test\",\"update_type\":2,\"auth_user_following\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":3,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":4,\"name\":\"Nour\",\"followed_id\":1,\"followed_name\":\"test\",\"update_type\":2,\"auth_user_following\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":4,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":5,\"name\":\"Salma\",\"followed_id\":1,\"followed_name\":\"test\",\"update_type\":2,\"auth_user_following\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":8,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":3,\"name\":\"waleed\",\"followed_id\":2,\"followed_name\":\"ta7a\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":9,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":4,\"name\":\"Nour\",\"followed_id\":2,\"followed_name\":\"ta7a\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":10,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":5,\"name\":\"Salma\",\"followed_id\":2,\"followed_name\":\"ta7a\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":14,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":2,\"name\":\"ta7a\",\"followed_id\":3,\"followed_name\":\"waleed\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":15,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":4,\"name\":\"Nour\",\"followed_id\":3,\"followed_name\":\"waleed\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":16,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":5,\"name\":\"Salma\",\"followed_id\":3,\"followed_name\":\"waleed\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":20,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":2,\"name\":\"ta7a\",\"followed_id\":4,\"followed_name\":\"Nour\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":21,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":3,\"name\":\"waleed\",\"followed_id\":4,\"followed_name\":\"Nour\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":22,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":5,\"name\":\"Salma\",\"followed_id\":4,\"followed_name\":\"Nour\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":26,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":2,\"name\":\"ta7a\",\"followed_id\":5,\"followed_name\":\"Salma\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":27,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":3,\"name\":\"waleed\",\"followed_id\":5,\"followed_name\":\"Salma\",\"update_type\":2,\"auth_user_following\":1,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":2,\"updated_at\":\"2019-04-27 17:28:34\",\"user_id\":3,\"name\":\"waleed\",\"followed_id\":1,\"followed_name\":\"test\",\"update_type\":2,\"auth_user_following\":0,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\",\"followed_image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"},{\"id\":1,\"body\":\"ISbcsc8JPG\",\"rating\":5,\"likes_count\":0,\"comments_count\":0,\"updated_at\":\"2019-04-27 17:28:34\",\"book_id\":4,\"title\":\"Internment\",\"description\":\"Rebellions are built on hope.\\n            Set in a horrifying near-future United States, seventeen-year-old Layla Amin and her parents are forced into an internment camp for Muslim American citizens.\\n            With the help of newly made friends also trapped within the internment camp, her boyfriend on the outside, and an unexpected alliance, Layla begins a journey to fight for freedom, leading a revolution against the internment camp's Director and his guards.\\n            Heart-racing and emotional, Internment challenges readers to fight complicit silence that exists in our society today.\",\"img_url\":\"https:\\/\\/r.wheelers.co\\/bk\\/small\\/978034\\/9780349003344.jpg\",\"reviews_count\":0,\"ratings_count\":0,\"ratings_avg\":0,\"pages_no\":0,\"user_id\":4,\"name\":\"Nour\",\"author_name\":\"Samira Ahmed\",\"update_type\":0,\"shelf\":3,\"image_link\":\"http:\\/\\/ec2-52-90-5-77.compute-1.amazonaws.com\\/storage\\/avatars\\/default.jpg\"}]";

    TabItem mUpdtesfragment ;
    TabItem mNotificationfragment;
    @Nullable
    @Override
    /**
     * Called when the activity is first created.
     * Creating in it ListView contians the array of Updates for the currently User
     *
     * @param inflater LayoutInflater:The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container  ViewGroup:the parent view that the fragment's UI should be attached to
     * @param savedInstanceState  Bundle:this fragment is being re-constructed from a previous saved state as given here.
     *
     * @return 	Return the View for the fragment's UI, or null.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.activity_updates,container,false);
       // adapter = new MaterialAdapter(getContext(),MaterialList);
       // ListView list = (ListView) myview.findViewById(R.id.MaterialstList);
       // list.setAdapter(adapter);
        //final SwipeRefreshLayout swipe = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

        loading = (ProgressBar) view.findViewById(R.id.UpdatesActivity_loading_progbar);
        adapter = new UpdatesAdapter(getContext(), arrayOfUpadates1);

        listUpadtes = (ListView) view.findViewById(R.id.UpadtesActivity_updateslist_listview);
        listUpadtes.setEmptyView(view.findViewById(R.id.empty));
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

        listUpadtes.setVisibility(View.GONE);
        refresh.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
       refresh.setColorSchemeResources(R.color.colorRed);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        request();

                        refresh.setRefreshing(false);
                    }
                },3000);
            }
        });

        return view;
    }



    @Override
    /**
     * Called when the activity is first created.
     * Calling in it the funcion the create the array of updates to give it to adapter.
     *
    * @param savedInstanceState  Bundle:this fragment is being re-constructed from a previous saved state as given here.
     *
     */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = HomeFragment.context ;
        queue = Volley.newRequestQueue(getContext());
        request();
        //arrayOfUpadates1 = onResposeAction(newjson);
        //arrayOfUpadates1 = onResposeAction1(jsonFile);
        /*if(UserInfo.IsMemic == false) {
            request();
        }else{
            String s = Memic.getUpdates(UserInfo.Memicid);
            onResposeAction(s);
            showlist();

        }*/
       // Toast.makeText(getContext(),"salma",Toast.LENGTH_SHORT).show();

    }

    /**
     * request the json file of updates list to be displayed.
     * in the response we call the function that create the array of updates
     *
     */
    public String request(){
        String url = Urls.ROOT+"/api/updates?token="+ UserInfo.sToken +"&type="+UserInfo.sTokenType;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                       // jsonFile = response;
                        arrayOfUpadates1 = onResposeAction(response);
                        adapter = new UpdatesAdapter(getContext(), arrayOfUpadates1);
                        listUpadtes.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        loading.setVisibility(View.GONE);
                        listUpadtes.setVisibility(View.VISIBLE);
                        refresh.setVisibility(View.VISIBLE);
                        Log.d(response,response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return jsonFile;
    }

    /**
     * Create array of updates of different types.
     * @param response the json string that contains array of updates
     * @return Arraylist contains updates of the user that was extract from json.
     */
    static public ArrayList<Updates> onResposeAction(String response){
        ArrayList<Updates> arrayOfUpadates = new ArrayList<Updates>();
        JSONObject root = null;
        String name = "hh";
        JSONArray updatesArray  = null;

        try {
            updatesArray = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < updatesArray.length(); i++) {
            try {
                JSONObject updateItemJson = updatesArray.getJSONObject(i);

                Updates updateItem = new Updates(updateItemJson.getInt("update_type"), updateItemJson.getString("name"),updateItemJson.getString("updated_at"),9,10,updateItemJson.getInt("user_id"));
                if(updateItemJson.getString("image_link") == null){updateItem.setmUserimg("0");}
                else {
                    updateItem.setmUserimg(updateItemJson.getString("image_link"));
                }
                switch (updateItem.getmTypeOfUpdates()){
                    //review or raring update
                    case 0:
                        updateItem.setmUserShelf(updateItemJson.getInt("shelf"));
                        updateItem.setmReviewID(updateItemJson.getInt("id"));
                        updateItem.setmBookCover(updateItemJson.getString("img_url"));
                        updateItem.setmBookName(updateItemJson.getString("title"));
                        updateItem.setmRatingValue(updateItemJson.getInt("rating"));
                        updateItem.setmAuthorName(updateItemJson.getString("author_name"));
                        updateItem.setmBookId(updateItemJson.getInt("book_id"));
                        //if type of only revies Disable rating value and assign review
                        updateItem.setmReview(updateItemJson.getString("body"));
                        break;
                     //shelves
                    case 1:
                        updateItem.setmUserShelf(updateItemJson.getInt("shelf"));
                        updateItem.setmBookCover(updateItemJson.getString("img_url"));
                        updateItem.setmBookName(updateItemJson.getString("title"));
                        updateItem.setmAuthorName(updateItemJson.getString("author_name"));
                        updateItem.setmBookId(updateItemJson.getInt("book_id"));
                        updateItem.setmShelfUpdateType(updateItemJson.getInt("shelf_type"));//shelf
                        break;
                    //follwing
                    case 2:
                        updateItem.setmNameofFollow(updateItemJson.getString("followed_name"));
                        updateItem.setmInnerUserId(updateItemJson.getInt("followed_id"));
                        break;
                    //liked or commented on post
                    case 3: case 4:
                        updateItem.setmInnerUpdate(0);//always in reviews
                        updateItem.setmNameofFollow(updateItemJson.getString("rev_user_name"));
                        updateItem.setmInnerDate(updateItemJson.getString("review_updated_at"));
                        //type of the inner post
                        updateItem.setmUserShelf(updateItemJson.getInt("shelf"));
                        updateItem.setmReviewID(updateItemJson.getInt("review_id"));
                        updateItem.setmBookCover(updateItemJson.getString("img_url"));
                        updateItem.setmBookName(updateItemJson.getString("title"));
                        updateItem.setmInnerImgUrl(updateItemJson.getString("rev_user_imageLink"));
                        updateItem.setmAuthorName(updateItemJson.getString("author_name"));
                        updateItem.setmBookId(updateItemJson.getInt("book_id"));
                        updateItem.setmReview(updateItemJson.getString("body"));
                        updateItem.setmInnerUserId(updateItemJson.getInt("rev_user_id"));
                        updateItem.setmRatingValue(updateItemJson.getInt("rating"));
                        //commented on post assign comment to show it
                        if(updateItem.getmTypeOfUpdates() == 4){
                            updateItem.setmComment(updateItemJson.getString("comment_body"));
                        }
                        break;
                }
                arrayOfUpadates.add(updateItem);
                //adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayOfUpadates;
    }
    static public ArrayList<Updates> onResposeAction1(String response){
        ArrayList<Updates> arrayOfUpadates = new ArrayList<Updates>();
        JSONObject root = null;
        String name = "hh";
        JSONArray updatesArray  = null;

        try {
            root = new JSONObject(response);
            JSONObject update = root.getJSONObject("updates");
            updatesArray = update.getJSONArray("update");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < updatesArray.length(); i++) {
            try {

                JSONObject updateItemJson = updatesArray.getJSONObject(i);
                JSONObject actor = updateItemJson.getJSONObject("actor");
                JSONObject action = updateItemJson.getJSONObject("action");


                Updates updateItem = new Updates(action.getInt("type"), actor.getString("name"),updateItemJson.getString("updated_at"),updateItemJson.getInt("numLikes"),updateItemJson.getInt("numComments"),actor.getInt("id"));
                switch (updateItem.getmTypeOfUpdates()){
                    //review or raring update
                    case 0:
                        JSONObject book = action.getJSONObject("book");
                        updateItem.setmBookCover(book.getString("imgUrl"));
                        updateItem.setmBookName(book.getString("title"));
                        updateItem.setmRatingValue(action.getInt("rating"));
                        updateItem.setmAuthorName(book.getString("author"));
                        if(updateItem.getmRatingValue() == 0){
                            //if type of only revies Disable rating value and assign review
                            updateItem.setmReview(action.getString("review"));
                        }
                        break;
                    //shelves
                    case 1:
                        JSONObject book1 = action.getJSONObject("book");
                        updateItem.setmBookCover(book1.getString("imgUrl"));
                        updateItem.setmBookName(book1.getString("title"));
                        updateItem.setmAuthorName(book1.getString("author"));
                        updateItem.setmShelfUpdateType(action.getInt("shelf_type"));//shelf
                        break;
                    //follwing
                    case 2:
                        JSONObject user = action.getJSONObject("user");
                        updateItem.setmNameofFollow(user.getString("name"));
                        break;
                    //liked or commented on post
                    case 3: case 4:
                        JSONObject innerupdate = action.getJSONObject("update");
                        JSONObject inneraction = innerupdate.getJSONObject("action");
                        JSONObject inneractor = innerupdate.getJSONObject("actor");
                        updateItem.setmInnerUserId(inneractor.getInt("id"));
                        updateItem.setmInnerUpdate(inneraction.getInt("type"));
                        updateItem.setmNameofFollow(inneractor.getString("name"));
                        updateItem.setmInnerDate(innerupdate.getString("updated_at"));
                        //type of the inner post
                        switch (updateItem.getmInnerUpdate()) {
                            //review or rating
                            case 0:
                                JSONObject innerbook = inneraction.getJSONObject("book");
                                updateItem.setmBookCover(innerbook.getString("imgUrl"));
                                updateItem.setmBookName(innerbook.getString("title"));
                                updateItem.setmRatingValue(inneraction.getInt("rating"));
                                updateItem.setmAuthorName(innerbook.getString("author"));
                                if (updateItem.getmRatingValue() == 0) {
                                    updateItem.setmReview(action.getString("review"));
                                }
                                break;
                            //shelves
                            case 1:
                                JSONObject innerbook1 = inneraction.getJSONObject("book");
                                updateItem.setmBookCover(innerbook1.getString("imgUrl"));
                                updateItem.setmBookName(innerbook1.getString("title"));
                                updateItem.setmAuthorName(innerbook1.getString("author"));
                                updateItem.setmShelf(inneraction.getString("shelf"));
                                break;
                            //follwing
                            case 2:
                                JSONObject user1 = inneraction.getJSONObject("user");
                                updateItem.setmNameofFollow(user1.getString("name"));
                                break;
                        }
                        //commented on post assign comment to show it
                        if(updateItem.getmTypeOfUpdates() == 4){
                            updateItem.setmComment(action.getString("comment"));
                        }
                        break;
                }
                arrayOfUpadates.add(updateItem);
                adapter.notifyDataSetChanged();
                listUpadtes.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return arrayOfUpadates;
    }
    @Override
    public void onResume() {
        request();
        super.onResume();
    }
    /*
    * */
    public void showlist(){
        listUpadtes = (ListView) view.findViewById(R.id.UpadtesActivity_updateslist_listview);
        adapter = new UpdatesAdapter(getContext(), arrayOfUpadates1);
        listUpadtes.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        loading.setVisibility(View.GONE);
        listUpadtes.setVisibility(View.VISIBLE);
        refresh.setVisibility(View.VISIBLE);

    }

}

