package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class GetExampleDataLoad401 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 401L);
        map.put("filter", "filter401");
        map.put("X-Custom-Header", "customHeader401");
        return map;
    }
}
