package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.SoldMovedOut;
import com.mc.HouseManagement.entity.User;

import java.util.HashMap;
import java.util.Map;

public enum  ClassTypes {
    OWNER,
    USER,
    SOLD_MOVED_OUT;

    // Map to store string to Class<?> associations
    private static final Map<String, Class<?>> classTypeMap = new HashMap<>();

    // Static block to populate the map
    static {
        classTypeMap.put("OWNER", Owner.class);
        classTypeMap.put("USER", User.class);
        classTypeMap.put("SOLD_MOVED_OUT", SoldMovedOut.class);
    }

    // Static method to get Class<?> based on string input
    public static Class<?> getClassType(String input) {
        return classTypeMap.get(input.toUpperCase());
    }
}
