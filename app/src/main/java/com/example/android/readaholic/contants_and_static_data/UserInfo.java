package com.example.android.readaholic.contants_and_static_data;

/**
 * this class holds the info of a user
 * we fill the data when a user signs in
 * we remove the data when the user signs out
 */
public class UserInfo {

    public static String sUserName;
    public static String sName;
    public static String sImageUrl;
    //token required for post requests
    public static String sToken;
    public static String sTokenType;
    public static boolean IsMemic = true;
    public static int Memicid = 1;

    public static Boolean mIsGuest=false;

    /**
     * adding the user data
     * called when the user data is received successfully
     * @param userName
     * @param name
     * @param imageUrl
     * @param token
     * @param tokenType
     */
    public static void addUserInfo(String userName , String name , String imageUrl
                                 , String token , String tokenType ){
        sUserName = userName;
        sName = name;
        sImageUrl = imageUrl;
        sToken = token;
        sTokenType = tokenType;
    }

    /**
     * removes the user data
     * should be called when the user signs out
     */
    public static void removeUserInfo() {
        sUserName = null;
        sName = null;
        sImageUrl = null;
        sToken = null;
        sTokenType = null;
    }



}
