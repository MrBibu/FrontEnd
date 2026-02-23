package com.academiago.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {

    private static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
    }

    public static void saveToken(String token) {
        prefs.edit().putString("token", token).apply();
    }

    public static String getToken() {
        return prefs.getString("token", null);
    }

    public static void saveRole(String role) {
        prefs.edit().putString("role", role).apply();
    }

    public static String getRole() {
        return prefs.getString("role", null);
    }

    public static void clear() {
        prefs.edit().clear().apply();
    }
}