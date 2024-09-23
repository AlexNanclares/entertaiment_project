package com.example.streamingPlatform.util;

import com.example.streamingPlatform.persistence.entity.User;

import java.util.HashMap;

public class Utilities {

    public static HashMap<String, Object> buildClaimsUser(User user){
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", user.getId());
        result.put("firstName", user.getFirstName());
        result.put("lastName", user.getLastName());
        result.put("username", user.getUsername());

        return result;
    }

    public static Boolean isNullOrEmpty(Object obj) {
        if (obj instanceof String) {
            return obj == null || obj.equals("");
        } else {
            return obj == null;
        }
    }

}
