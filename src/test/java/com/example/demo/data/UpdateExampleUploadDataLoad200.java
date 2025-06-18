package com.example.demo.data;

import io.github.kelari.atg.data.DataLoad;
import org.springframework.core.io.ByteArrayResource;

import java.util.HashMap;
import java.util.Map;

public class UpdateExampleUploadDataLoad200 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();

        // File simulation with ByteArrayResource
        byte[] content = "content file".getBytes();
        ByteArrayResource fileResource = new ByteArrayResource(content) {
            @Override
            public String getFilename() {
                return "file_exemplo.txt";
            }
        };

        map.put("name", "200");
        map.put("description", "description test");
        map.put("uploader", "Antonio Neto");
        map.put("file", fileResource);

        return map;
    }
}
