package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class GetExampleWithCookieDataLoad400 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 400L);
        map.put("X-Custom-Header", "customHeader400");
        map.put("sessionId", "sessionId400");
        map.put("filter", "filter400");
        return map;
    }
}
