package com.example.afinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class preferences {
    private static final String STUDENT_NAME = "studentName";
    private static final String IS_LOGGED_IN = "isLoggedIn";
    private static final String EMAIL = "email";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            editor = sharedPreferences.edit();
        }
    }

    public static void saveSession(Context context, String Email) {
        getSharedPreferences(context);
        editor.putString(EMAIL, Email);
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.apply();
    }

    public static boolean isNotLoggedIn(Context context) {
        getSharedPreferences(context);
        return !sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public static String getEmail(Context context) {
        getSharedPreferences(context);
        return sharedPreferences.getString(EMAIL, null);
    }

    public static void clearSession(Context context) {
        getSharedPreferences(context);
        editor.clear();
        editor.apply();
    }

}