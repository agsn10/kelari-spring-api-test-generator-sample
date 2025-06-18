package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class GetExampleWithCookieDataLoad404 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 404L);
        map.put("X-Custom-Header", "customHeader404");
        map.put("sessionId", "sessionId404");
        map.put("filter", "filter404");
        return map;
    }
}
