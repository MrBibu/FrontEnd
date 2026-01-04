package com.academiago.app.utils;

import android.util.Base64;
import org.json.JSONObject;

public class TokenUtils {
    public static String getRole(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;
            String payloadJson = new String(Base64.decode(parts[1],
                    Base64.URL_SAFE | Base64.NO_WRAP | Base64.NO_PADDING));
            JSONObject payload = new JSONObject(payloadJson);

            // Try common claim names
            if (payload.has("roles")) return payload.getJSONArray("roles").optString(0, null);
            if (payload.has("authorities")) return payload.getJSONArray("authorities").optString(0, null);
            return payload.optString("role", null);
        } catch (Exception e) {
            return null;
        }
    }

    public static long getExpiry(String token) {
        try {
            String[] parts = token.split("\\.");
            String payloadJson = new String(Base64.decode(parts[1],
                    Base64.URL_SAFE | Base64.NO_WRAP | Base64.NO_PADDING));
            JSONObject payload = new JSONObject(payloadJson);
            return payload.optLong("exp", 0L); // seconds since epoch
        } catch (Exception e) {
            return 0L;
        }
    }
}
