package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class PatchExampleDataLoad200 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "200");
        updates.put("value", 42);
        updates.put("active", true);
        map.put("updates", updates);
        map.put("id", 200L);
        return map;
    }
}
