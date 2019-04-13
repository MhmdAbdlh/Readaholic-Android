package com.example.android.readaholic.settings;

import com.example.android.readaholic.contants_and_static_data.WhoCanSeeContent;

public class SettingData {

    private String mName;
    private String mBirthDay;
    private String mImage;
    private String mCountry;

    private WhoCanSeeContent mWhoCanSeeMyBirthDay;
    private WhoCanSeeContent mWhoCanSeeMyLocation;

    public SettingData(String mUserName, String mBirthDay, String mImage, String mLocation
                       ,WhoCanSeeContent mWhoCanSeeMyBirthDay, WhoCanSeeContent mWhoCanSeeMyLocation) {
        this.mName= mUserName;
        this.mBirthDay = mBirthDay;
        this.mImage = mImage;
        this.mCountry = mLocation;
        this.mWhoCanSeeMyBirthDay = mWhoCanSeeMyBirthDay;
        this.mWhoCanSeeMyLocation = mWhoCanSeeMyLocation;
    }

    public String getmName() {
        return mName;
    }

    public String getmBirthDay() {
        return mBirthDay;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmCountry() {
        return mCountry;
    }

    public WhoCanSeeContent getmWhoCanSeeMyBirthDay() {
        return mWhoCanSeeMyBirthDay;
    }

    public WhoCanSeeContent getmWhoCanSeeMyLocation() {
        return mWhoCanSeeMyLocation;
    }
}

