package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class GetExampleDataLoad200 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 200L);
        map.put("filter", "filter200");
        map.put("X-Custom-Header", "customHeader200");
        return map;
    }
}
