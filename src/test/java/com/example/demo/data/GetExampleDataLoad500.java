package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class GetExampleDataLoad500 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 500L);
        map.put("filter", "filter500");
        map.put("X-Custom-Header", "customHeader500");
        return map;
    }
}
