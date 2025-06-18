package com.example.demo.data;

import com.example.demo.model.LoginRequest;
import io.github.kelari.atg.data.DataLoad;

import java.util.HashMap;
import java.util.Map;

public class LoginDataLoad401 implements DataLoad {
    @Override
    public Map<String, Object> load() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("admin1");
        Map<String, Object> map = new HashMap<>();
        map.put("loginRequest", loginRequest);
        return map;
    }
}
