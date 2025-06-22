package com.example.demo.resource;

import com.example.demo.matchers.IsJohnMatcher;
import com.example.demo.model.DataResponse;
import com.example.demo.model.ExampleRequest;
import com.example.demo.model.UploadRequest;
import io.github.kelari.atg.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/example")
@KelariGenerateApiTest(
        authUrl = "/api/auth/login",
        username = "admin",
        password = "admin"
)
public class ExampleResource {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @ApiTestSpec(
        scenarios = {
            @ApiTestCase(
                displayName = "✅ Should return 200 OK when 'id' is 200 and authenticated",
                order = 1,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_OK,
                dataProviderClassName = "com.example.demo.data.GetExampleDataLoad200",
                requiresAuth = true,
                repeat = 5,
                enableLogging = true,
                responseTimeoutSeconds = 5,
                expectedHeaders = {@Header(name = "testName1", value = {"testValue1", "testValue11"}),
                                   @Header(name = "testName2", value = "testValue2")},
                jsonPaths = {
                        // EQUAL_TO: o valor retornado deve ser exatamente "John"
                        @JsonPath(path = "$.name", type = MatcherType.EQUAL_TO, value = "John"),
                        // NULL_VALUE: espera que o valor seja null
                        @JsonPath(path = "$.optionalField", type = MatcherType.NULL_VALUE),
                        // NOT_NULL_VALUE: espera que o campo não seja null
                        @JsonPath(path = "$.mandatoryField", type = MatcherType.NOT_NULL_VALUE),
                        // NOT: nega o valor (ex: não deve ser "Admin")
                        @JsonPath(path = "$.role", type = MatcherType.NOT, value = "Admin"),
                        // INSTANCE_OF: espera que o valor seja do tipo Integer
                        @JsonPath(path = "$.age", type = MatcherType.INSTANCE_OF, value = "java.lang.Integer"),
                        // GREATER_THAN: espera que o valor seja maior que 10
                        @JsonPath(path = "$.score", type = MatcherType.GREATER_THAN, value = "10"),
                        // LESS_THAN: espera que o valor seja menor que 100
                        @JsonPath(path = "$.limit", type = MatcherType.LESS_THAN, value = "100"),
                        // CONTAINS_STRING: espera que o valor contenha a substring "success"
                        @JsonPath(path = "$.message", type = MatcherType.CONTAINS_STRING, value = "success"),
                        // STARTS_WITH: espera que o valor comece com "user_"
                        @JsonPath(path = "$.username", type = MatcherType.STARTS_WITH, value = "user_"),
                        // ENDS_WITH: espera que o valor termine com ".com"
                        @JsonPath(path = "$.email", type = MatcherType.ENDS_WITH, value = ".com"),
                        // ANY_OF: espera que o valor seja qualquer um da lista ["A", "B", "C"]
                        @JsonPath(path = "$.grade", type = MatcherType.ANY_OF, value = "A,B,C"),
                        // CONTAINS: espera que a lista contenha "admin"
                        @JsonPath(path = "$.roles", type = MatcherType.HAS_ITEM, value = "admin"),
                        // CUSTOM_CLASS: matcher Java personalizado
                        @JsonPath(path = "$.name", type = MatcherType.CUSTOM_CLASS, matcherClass = IsJohnMatcher.class)
                }
            ),
            @ApiTestCase(
                displayName = "❌ Should return 400 Bad Request when 'id' is 400",
                order = 2,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                dataProviderClassName = "com.example.demo.data.GetExampleDataLoad400",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "🛡️ Should return 401 Unauthorized when Authorization header is missing",
                order = 3,
                timeout = 3,
                expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED,
                dataProviderClassName = "com.example.demo.data.GetExampleDataLoad401",
                requiresAuth = false
            ),
            @ApiTestCase(
                displayName = "❌ Should return 404 Not Found when 'id' is 404",
                order = 4,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_NOT_FOUND,
                dataProviderClassName = "com.example.demo.data.GetExampleDataLoad404",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 500 Internal Server Error for unhandled 'id'",
                order = 5,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                dataProviderClassName = "com.example.demo.data.GetExampleDataLoad500",
                requiresAuth = true
            )
        }
    )
    // GET com @PathVariable e @RequestParam
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse> getExample(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String filter,
            @RequestHeader(value = "X-Custom-Header", required = false) String customHeader) {

        System.out.println("Token received: "+ httpServletRequest.getHeader("Authorization"));
        System.out.println("INPUT -> GET: id=" + id + ", filter=" + filter + ", header=" + customHeader);

        if (id == 200) {
            // Retorna com headers personalizados
            return ResponseEntity
                    .ok()
                    .header("testName1", "testValue1", "testValue11")
                    .header("testName2", "testValue2")
                    .body(DataResponse.createExample());
        }else if (id == 400)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
         else if(Objects.isNull(httpServletRequest.getHeader("Authorization")))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         else if(id==404)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ApiTestSpec(
        scenarios = {
            @ApiTestCase(
                displayName = "✅ Should return 201 Created when Example name is '201'",
                order = 6,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_CREATED,
                dataProviderClassName = "com.example.demo.data.CreateExampleDataLoad201",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 400 Bad Request when Example name is '400'",
                order = 7,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                dataProviderClassName = "com.example.demo.data.CreateExampleDataLoad400",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "🛡️ Should return 401 Unauthorized when Authorization header is missing",
                order = 8,
                timeout = 3,
                expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED,
                dataProviderClassName = "com.example.demo.data.CreateExampleDataLoad401",
                requiresAuth = false
            ),
            @ApiTestCase(
                displayName = "❌ Should return 404 Not Found when Example name is '404'",
                order = 9,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_NOT_FOUND,
                dataProviderClassName = "com.example.demo.data.CreateExampleDataLoad404",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 500 Internal Server Error when Example name is unexpected",
                order = 10,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                dataProviderClassName = "com.example.demo.data.CreateExampleDataLoad500",
                requiresAuth = true
            )
        }
    )
    // POST com @RequestBody e @RequestHeader
    @PostMapping
    public ResponseEntity<String> createExample(
            @RequestBody ExampleRequest request,
            @RequestHeader HttpHeaders headers) {

        System.out.println("Token received: "+httpServletRequest.getHeader("Authorization"));
        System.out.println("INPUT -> POST: request=" + request.toString() + ", headers=" + headers.toString());

        if(Objects.equals(request.getName(),"201"))
            return ResponseEntity.status(201).body("createExample");
        else if (Objects.equals(request.getName(),"400"))
            return ResponseEntity.status(400).body("Bad request");
        else if(Objects.isNull(httpServletRequest.getHeader("Authorization")))
            return ResponseEntity.status(401).body("Authorization header missing or invalid.");
        else if(Objects.equals(request.getName(),"404"))
            return ResponseEntity.status(404).body("Not found.");
        else
            return ResponseEntity.status(500).body("Internal error.");

    }

    @ApiTestSpec(
        scenarios = {
            @ApiTestCase(
                displayName = "✅ Should return 200 OK when Example ID is 200",
                order = 11,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_OK,
                dataProviderClassName = "com.example.demo.data.UpdateExampleDataLoad200",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 400 Bad Request when Example ID is 400",
                order = 12,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                dataProviderClassName = "com.example.demo.data.UpdateExampleDataLoad400",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "🛡️ Should return 401 Unauthorized when Authorization header is missing",
                order = 13,
                timeout = 3,
                expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED,
                dataProviderClassName = "com.example.demo.data.UpdateExampleDataLoad401",
                requiresAuth = false
            ),
            @ApiTestCase(
                displayName = "❌ Should return 404 Not Found when Example ID is 404",
                order = 14,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_NOT_FOUND,
                dataProviderClassName = "com.example.demo.data.UpdateExampleDataLoad404",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 500 Internal Server Error when Example ID is unexpected",
                order = 15,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                dataProviderClassName = "com.example.demo.data.UpdateExampleDataLoad500",
                requiresAuth = true
            )
        }
    )
    // PUT com @PathVariable e @RequestBody
    @PutMapping("/{id}")
    public ResponseEntity<String> updateExample(
            @PathVariable Long id,
            @RequestBody ExampleRequest request) {

        System.out.println("Token received: "+httpServletRequest.getHeader("Authorization"));
        System.out.println("INPUT -> PUT: request=" + request.toString() + ", id=" + id);

        if(id == 200L)
            return ResponseEntity.ok("updateExample");
        else if (id == 400L)
            return ResponseEntity.status(400).body("Bad request");
        else if(Objects.isNull(httpServletRequest.getHeader("Authorization")))
            return ResponseEntity.status(401).body("Authorization header missing or invalid.");
        else if(id == 404L)
            return ResponseEntity.status(404).body("Not found.");
        else
            return ResponseEntity.status(500).body("Internal error.");
    }


    @ApiTestSpec(
        scenarios = {
            @ApiTestCase(
                displayName = "✅ Should return 200 OK when Example ID is 200",
                order = 16,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_OK,
                dataProviderClassName = "com.example.demo.data.PatchExampleDataLoad200",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 400 Bad Request when Example ID is 400",
                order = 17,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                dataProviderClassName = "com.example.demo.data.PatchExampleDataLoad400",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "🛡️ Should return 401 Unauthorized when Authorization header is missing",
                order = 18,
                timeout = 3,
                expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED,
                dataProviderClassName = "com.example.demo.data.PatchExampleDataLoad401",
                requiresAuth = false
            ),
            @ApiTestCase(
                displayName = "❌ Should return 404 Not Found when Example ID is 404",
                order = 19,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_NOT_FOUND,
                dataProviderClassName = "com.example.demo.data.PatchExampleDataLoad404",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 500 Internal Server Error when Example ID is unexpected",
                order = 20,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                dataProviderClassName = "com.example.demo.data.PatchExampleDataLoad500",
                requiresAuth = true
            )
        }
    )
    // PATCH com @PathVariable e campos parciais no body (como Map)
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchExample(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        System.out.println("Token received: "+httpServletRequest.getHeader("Authorization"));
        System.out.println("INPUT -> PATCH: updates=" + updates.toString() + ", id=" + id);

        if(id == 200L)
            return ResponseEntity.ok("patchExample");
        else if (id == 400L)
            return ResponseEntity.status(400).body("Bad request");
        else if(Objects.isNull(httpServletRequest.getHeader("Authorization")))
            return ResponseEntity.status(401).body("Authorization header missing or invalid.");
        else if(id == 404L)
            return ResponseEntity.status(404).body("Not found.");
        else
            return ResponseEntity.status(500).body("Internal error.");

    }


    @ApiTestSpec(
        scenarios = {
            @ApiTestCase(
                displayName = "✅ Should return 204 No Content when Example ID is 204",
                order = 21,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_NO_CONTENT,
                dataProviderClassName = "com.example.demo.data.DeleteExampleDataLoad204",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 400 Bad Request when Example ID is 400",
                order = 22,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                dataProviderClassName = "com.example.demo.data.DeleteExampleDataLoad400",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "🛡️ Should return 401 Unauthorized when Authorization header is missing",
                order = 23,
                timeout = 3,
                expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED,
                dataProviderClassName = "com.example.demo.data.DeleteExampleDataLoad401",
                requiresAuth = false
            ),
            @ApiTestCase(
                displayName = "❌ Should return 404 Not Found when Example ID is 404",
                order = 24,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_NOT_FOUND,
                dataProviderClassName = "com.example.demo.data.DeleteExampleDataLoad404",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 500 Internal Server Error when Example ID is unexpected",
                order = 25,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                dataProviderClassName = "com.example.demo.data.DeleteExampleDataLoad500",
                requiresAuth = true
            )
        }
    )
    // DELETE com @PathVariable e @RequestParam
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExample(
            @PathVariable Long id,
            @RequestParam(required = false) boolean hardDelete) {
        System.out.println("Token received: "+httpServletRequest.getHeader("Authorization"));
        System.out.println("INPUT -> DELETE: id=" + id + ", hardDelete=" + hardDelete);

        if(id == 204L)
            return ResponseEntity.status(204).body("deleteExample");
        else if (id == 400L)
            return ResponseEntity.status(400).body("Bad request");
        else if(Objects.isNull(httpServletRequest.getHeader("Authorization")))
            return ResponseEntity.status(401).body("Authorization header missing or invalid.");
        else if(id == 404L)
            return ResponseEntity.status(404).body("Not found.");
        else
            return ResponseEntity.status(500).body("Internal error.");

    }


    @ApiTestSpec(
            scenarios = {
                    @ApiTestCase(
                            displayName = "✅ Should return 200 OK when name is '200' and file is uploaded successfully",
                            order = 26,
                            timeout = 5,
                            expectedStatusCode = HttpURLConnection.HTTP_OK,
                            dataProviderClassName = "com.example.demo.data.UpdateExampleUploadDataLoad200",
                            requiresAuth = true
                    ),
                    @ApiTestCase(
                            displayName = "❌ Should return 400 Bad Request when name is '400' (invalid input)",
                            order = 27,
                            timeout = 5,
                            expectedStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                            dataProviderClassName = "com.example.demo.data.UpdateExampleUploadDataLoad400"
                    ),
                    @ApiTestCase(
                            displayName = "🛡️ Should return 401 Unauthorized when Authorization header is missing",
                            order = 28,
                            timeout = 3,
                            expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED,
                            dataProviderClassName = "com.example.demo.data.UpdateExampleUploadDataLoad401",
                            requiresAuth = false
                    ),
                    @ApiTestCase(
                            displayName = "❌ Should return 404 Not Found when name is '404' and resource does not exist",
                            order = 29,
                            timeout = 5,
                            expectedStatusCode = HttpURLConnection.HTTP_NOT_FOUND,
                            dataProviderClassName = "com.example.demo.data.UpdateExampleUploadDataLoad404",
                            requiresAuth = true
                    ),
                    @ApiTestCase(
                            displayName = "❌ Should return 500 Internal Server Error for unknown name",
                            order = 30,
                            timeout = 5,
                            expectedStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                            dataProviderClassName = "com.example.demo.data.UpdateExampleUploadDataLoad500",
                            requiresAuth = true
                    )
            }
    )
    // POST com @ModelAttribute (form data) e upload de arquivo
    @PostMapping("/upload")
    public ResponseEntity<String> uploadExample(
            @ModelAttribute UploadRequest uploadRequest,
            @RequestPart(required = false) MultipartFile file) {

        System.out.println("Token received: "+httpServletRequest.getHeader("Authorization"));
        System.out.println("INPUT -> POST: UPLOAD: name=" + uploadRequest.getName() + ", file=" + (file != null ? file.getOriginalFilename() : "none"));

        if(Objects.equals(uploadRequest.getName(), "200"))
            return ResponseEntity.ok("uploadExample");
        else if (Objects.equals(uploadRequest.getName(), "400"))
            return ResponseEntity.status(400).body("Bad request");
        else if(Objects.isNull(httpServletRequest.getHeader("Authorization")))
            return ResponseEntity.status(401).body("Authorization header missing or invalid.");
        else if(Objects.equals(uploadRequest.getName(), "404"))
            return ResponseEntity.status(404).body("Not found.");
        else
            return ResponseEntity.status(500).body("Internal error.");

    }

    @ApiTestSpec(
        scenarios = {
            @ApiTestCase(
                displayName = "✅ Should return 200 OK when color matrix variable is '200'",
                order = 31,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_OK,
                dataProviderClassName = "com.example.demo.data.GetExampleWithMatrixDataLoad200",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 400 Bad Request when color matrix variable is '400'",
                order = 32,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                dataProviderClassName = "com.example.demo.data.GetExampleWithMatrixDataLoad400",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "🛡️ Should return 401 Unauthorized when Authorization header is missing",
                order = 33,
                timeout = 3,
                expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED,
                dataProviderClassName = "com.example.demo.data.GetExampleWithMatrixDataLoad401",
                requiresAuth = false
            ),
            @ApiTestCase(
                displayName = "❌ Should return 404 Not Found when color matrix variable is '404'",
                order = 34,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_NOT_FOUND,
                dataProviderClassName = "com.example.demo.data.GetExampleWithMatrixDataLoad404",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 500 Internal Server Error when color matrix variable is unexpected",
                order = 35,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                dataProviderClassName = "com.example.demo.data.GetExampleWithMatrixDataLoad500",
                requiresAuth = true
            )
        }
    )
    // Para acessar: GET /api/example/matrix/filters;color=red;size=large
    @GetMapping("/matrix/{filters}")
    public ResponseEntity<String> getExampleWithMatrix(
            @PathVariable String filters,
            @MatrixVariable(pathVar = "filters", name = "color", required = false) String color,
            @MatrixVariable(pathVar = "filters", name = "size", required = false) String size) {

        System.out.println("Token received: "+httpServletRequest.getHeader("Authorization"));
        System.out.println("INPUT -> GET: Matrix filters=" + filters + ", color=" + color + ", size=" + size);

        if(Objects.equals(color, "200"))
            return ResponseEntity.ok("getExampleWithMatrix");
        else if (Objects.equals(color,"400"))
            return ResponseEntity.status(400).body("Bad request");
        else if(Objects.isNull(httpServletRequest.getHeader("Authorization")))
            return ResponseEntity.status(401).body("Authorization header missing or invalid.");
        else if(Objects.equals(color,"404"))
            return ResponseEntity.status(404).body("Not found.");
        else
            return ResponseEntity.status(500).body("Internal error.");
    }

    @ApiTestSpec(
        scenarios = {
            @ApiTestCase(
                displayName = "✅ Should return 200 OK when ID is 200 and Authorization is present",
                order = 36,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_OK,
                dataProviderClassName = "com.example.demo.data.GetExampleWithCookieDataLoad200",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 400 Bad Request when ID is 400",
                order = 37,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                dataProviderClassName = "com.example.demo.data.GetExampleWithCookieDataLoad400",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "🛡️ Should return 401 Unauthorized when Authorization header is missing",
                order = 38,
                timeout = 3,
                expectedStatusCode = HttpURLConnection.HTTP_UNAUTHORIZED,
                dataProviderClassName = "com.example.demo.data.GetExampleWithCookieDataLoad401",
                requiresAuth = false
            ),
            @ApiTestCase(
                displayName = "❌ Should return 404 Not Found when ID is 404",
                order = 39,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_NOT_FOUND,
                dataProviderClassName = "com.example.demo.data.GetExampleWithCookieDataLoad404",
                requiresAuth = true
            ),
            @ApiTestCase(
                displayName = "❌ Should return 500 Internal Server Error when ID is not handled",
                order = 40,
                timeout = 5,
                expectedStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                dataProviderClassName = "com.example.demo.data.GetExampleWithCookieDataLoad500",
                requiresAuth = true
            )
        }
    )
    @GetMapping("/cookie/{id}")
    public ResponseEntity<String> getExampleWithCookie(
            @CookieValue(value = "sessionId", defaultValue = "unknown") String sessionId,
            @RequestParam(required = false) String filter,
            @RequestHeader(value = "X-Custom-Header", required = false) String customHeader,
            @PathVariable(value = "id") Long id) {

        System.out.println("Token received: "+httpServletRequest.getHeader("Authorization"));
        System.out.println("INPUT -> GET: Cookie sessionId=" + sessionId + ", filter=" + filter + ", customHeader=" + customHeader + ", id=" + id);

        if(id == 200L)
            return ResponseEntity.ok("getExampleWithCookie");
        else if (id == 400L)
            return ResponseEntity.status(400).body("Bad request");
        else if(Objects.isNull(httpServletRequest.getHeader("Authorization")))
            return ResponseEntity.status(401).body("Authorization header missing or invalid.");
        else if(id == 404L)
            return ResponseEntity.status(404).body("Not found.");
        else
            return ResponseEntity.status(500).body("Internal error.");
    }

}