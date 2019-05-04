package com.example.android.readaholic.explore;

public class GenreModel {
    private String mGenreName;
    private int mGenrePic;

    public GenreModel(String mGenreName, int mGenrePic) {
        this.mGenreName = mGenreName;
        this.mGenrePic = mGenrePic;
    }

    public String getmGenreName() {
        return mGenreName;
    }

    public int getmGenrePic() {
        return mGenrePic;
    }
}
