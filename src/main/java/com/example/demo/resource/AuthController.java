package com.example.demo.resource;

import com.example.demo.model.LoginRequest;
import io.github.kelari.atg.annotation.ApiTestCase;
import io.github.kelari.atg.annotation.ApiTestSpec;
import io.github.kelari.atg.annotation.KelariGenerateApiTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller to simulate user authentication and return a mock JWT token.
 * Useful for testing purposes in generated API tests.
 */
@RestController
@RequestMapping("/api/auth")
@KelariGenerateApiTest
public class AuthController {

    /**
     * Simulates login and returns a mock token.
     *
     * @param loginRequest the login request payload containing username and password
     * @return a JSON containing a fake JWT token if credentials match
     */
    @ApiTestSpec(
            scenarios = {
                    @ApiTestCase(
                            displayName = "✅ Should return 200 OK with a token when valid credentials are provided",
                            order = 1,
                            timeout = 5,
                            expectedStatusCode = HttpURLConnection.HTTP_OK,
                            dataProviderClassName = "com.example.demo.data.LoginDataLoad200",
                            requiresAuth = false
                    ),
                    @ApiTestCase(
                            displayName = "🛡️ Should return 401 Unauthorized when invalid credentials are provided",
                            order = 2,
                            timeout = 3,
                            expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED,
                            dataProviderClassName = "com.example.demo.data.LoginDataLoad401",
                            requiresAuth = false
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if ("admin".equals(loginRequest.getUsername()) && "admin".equals(loginRequest.getPassword())) {
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", "fake-jwt-token-123456789");
            return ResponseEntity.ok(tokenMap);
        } else
            return ResponseEntity.status(401).body("Invalid credentials");
    }

}
