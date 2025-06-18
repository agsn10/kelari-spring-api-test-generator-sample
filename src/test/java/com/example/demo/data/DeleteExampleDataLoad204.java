package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class DeleteExampleDataLoad204 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        map.put("hardDelete", true);
        map.put("id", 204L);
        return map;
    }
}
