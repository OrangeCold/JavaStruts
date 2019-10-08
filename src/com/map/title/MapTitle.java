package com.map.title;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTitle {

    /**
     * 遍历没有泛型的Map
     */
    @Test
    public void testMap(){

        Map map1 = new HashMap();
        map1.put("tt","12");
        map1.put("name","mike");

        Set set = map1.entrySet();
        for (Object o : set) {
            Map.Entry r = (Map.Entry) o;
            String key = (String)r.getKey();
            System.out.println("key = " + key);
            String value = (String) r.getValue();
            System.out.println("value = " + value);
        }

    }

}
