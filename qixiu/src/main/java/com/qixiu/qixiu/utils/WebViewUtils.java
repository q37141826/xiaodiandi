package com.qixiu.qixiu.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by my on 2018/12/12.
 */

public class WebViewUtils {

    public static String resolveUrl(String baseUrl, Map<String, String> map) {
        List<String> keys = new ArrayList<>();
        Set<String> strings = map.keySet();
        for (String key : strings) {
            keys.add(key);
        }
        for (int i = 0; i < keys.size(); i++) {
            if (i == 0) {
                baseUrl=baseUrl+"?"+keys.get(i)+"="+map.get(keys.get(i));
            } else {
                baseUrl=baseUrl+"&"+keys.get(i)+"="+map.get(keys.get(i));
            }
        }
        return baseUrl;
    }

}
