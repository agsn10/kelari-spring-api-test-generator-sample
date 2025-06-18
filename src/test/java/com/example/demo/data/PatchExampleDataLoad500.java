package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class PatchExampleDataLoad500 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "500");
        updates.put("value", 42);
        updates.put("active", true);
        map.put("updates", updates);
        map.put("id", 500L);
        return map;
    }
}
