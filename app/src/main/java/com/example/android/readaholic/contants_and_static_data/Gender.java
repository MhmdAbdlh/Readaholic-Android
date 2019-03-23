package com.example.android.readaholic.contants_and_static_data;

public class Gender {
    public static String[] genders = new String[]{"Male" , "Female" , "Other"};

    public static int getCountryIndex(String gender){
        for (int i =0 ; i < genders.length ; i++)
        {
            if(genders[i].equals(gender)) {
                return i;
            }
        }
        return -1;
    }

}
