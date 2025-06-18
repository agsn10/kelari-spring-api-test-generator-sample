package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class GetExampleWithMatrixDataLoad401 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        map.put("color", "401");
        map.put("size", "XL");
        // Valor completo da URI após /matrix/
        //map.put("filters", "filters;color=200;size=XL");
        return map;
    }
}
