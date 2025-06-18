package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class GetExampleDataLoad400 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 400);
        map.put("filter", "filter400");
        map.put("X-Custom-Header", "customHeader400");
        return map;
    }
}
