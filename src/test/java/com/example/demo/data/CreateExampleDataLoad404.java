package com.example.demo.data;

import com.example.demo.model.ExampleRequest;
import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class CreateExampleDataLoad404 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        ExampleRequest request = new ExampleRequest();
        request.setName("404");
        request.setValue(42);
        request.setActive(true);
        map.put("request", request);
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Custom-Header", "custom value");
        map.put("headers", headers);
        return map;
    }
}
