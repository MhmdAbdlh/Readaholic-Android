package com.example.android.readaholic.settings;

import com.example.android.readaholic.contants_and_static_data.WhoCanSeeContent;

public class SettingData {

    private String mUserName;
    private String mBirthDay;
    private String mImage;
    private String mLocation;

    private WhoCanSeeContent mWhoCanSeeMyBirthDay;
    private WhoCanSeeContent mWhoCanSeeMyLocation;

    public SettingData(String mUserName, String mBirthDay, String mImage, String mLocation
                       ,WhoCanSeeContent mWhoCanSeeMyBirthDay, WhoCanSeeContent mWhoCanSeeMyLocation) {
        this.mUserName = mUserName;
        this.mBirthDay = mBirthDay;
        this.mImage = mImage;
        this.mLocation = mLocation;
        this.mWhoCanSeeMyBirthDay = mWhoCanSeeMyBirthDay;
        this.mWhoCanSeeMyLocation = mWhoCanSeeMyLocation;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmBirthDay() {
        return mBirthDay;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmLocation() {
        return mLocation;
    }

    public WhoCanSeeContent getmWhoCanSeeMyBirthDay() {
        return mWhoCanSeeMyBirthDay;
    }

    public WhoCanSeeContent getmWhoCanSeeMyLocation() {
        return mWhoCanSeeMyLocation;
    }
}

