package com.example.demo.data;

import com.example.demo.model.UploadRequest;
import io.github.kelari.atg.data.DataLoad;
import org.springframework.core.io.ByteArrayResource;

import java.util.HashMap;
import java.util.Map;

public class UpdateExampleUploadDataLoad400 implements DataLoad {
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

        map.put("name", "400");
        map.put("description", "description test");
        map.put("uploader", "Antonio Neto");
        map.put("file", fileResource);

        return map;
    }
}
