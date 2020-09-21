package com.payphone.source;

import java.util.HashMap;

public class HashMapSource {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.get("1");
        map.remove("1", "1");
    }
}
