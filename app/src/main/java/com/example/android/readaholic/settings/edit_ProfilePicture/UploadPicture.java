package com.example.android.readaholic.settings.edit_ProfilePicture;
public class UploadPicture {
    private String mName;
    private String mImageUrl;

    public UploadPicture() {
        //empty constructor needed
    }

    public UploadPicture(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}

