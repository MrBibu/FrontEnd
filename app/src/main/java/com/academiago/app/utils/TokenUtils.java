package com.academiago.app.utils;

import android.util.Base64;
import org.json.JSONObject;

public class TokenUtils {

    // Read primary role from JWT. Supports role, roles[], authorities[]
    public static String getRole(String token) {
        try {
            String payloadJson = decodePayload(token);
            if (payloadJson == null) return null;

            JSONObject payload = new JSONObject(payloadJson);

            if (payload.has("roles")) {
                return payload.getJSONArray("roles").optString(0, null);
            }
            if (payload.has("authorities")) {
                return payload.getJSONArray("authorities").optString(0, null);
            }
            return payload.optString("role", null);
        } catch (Exception e) {
            return null;
        }
    }

    // Check if JWT is expired using 'exp' (seconds since epoch)
    public static boolean isTokenExpired(String token) {
        try {
            String payloadJson = decodePayload(token);
            if (payloadJson == null) return true;

            JSONObject payload = new JSONObject(payloadJson);
            long exp = payload.optLong("exp", 0L);     // seconds
            long now = System.currentTimeMillis() / 1000; // seconds

            return exp == 0L || exp < now;
        } catch (Exception e) {
            return true;
        }
    }

    private static String decodePayload(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;

            return new String(Base64.decode(
                    parts[1],
                    Base64.URL_SAFE | Base64.NO_WRAP | Base64.NO_PADDING
            ));
        } catch (Exception e) {
            return null;
        }
    }
}
