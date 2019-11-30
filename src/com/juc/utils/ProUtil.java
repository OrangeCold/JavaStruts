package com.juc.utils;

import java.util.HashMap;
import java.util.Map;

public class ProUtil {
    public volatile static Map<String, String> map = null;

    public static final String OPEN = "OPEN";

    public static final String READ = "READ";

    static {
        map = new HashMap<String, String>();
        map.put(OPEN,"");
        map.put(READ,"");
    }
}
