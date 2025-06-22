package com.example.demo.model;

import java.util.List;

public class DataResponse {
    private String name;
    private String role;
    private String message;
    private Integer age;
    private Integer limit;
    private String email;
    private String optionalField;
    private String mandatoryField;
    private String grade;
    private String username;
    private List<String> roles;
    private Integer score;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getOptionalField() {
        return optionalField;
    }
    public void setOptionalField(String optionalField) {
        this.optionalField = optionalField;
    }

    public String getMandatoryField() {
        return mandatoryField;
    }
    public void setMandatoryField(String mandatoryField) {
        this.mandatoryField = mandatoryField;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }

    public static DataResponse createExample() {
        DataResponse response = new DataResponse();
        response.setName("John");
        response.setRole("User");
        response.setMessage("Operation successful - success");
        response.setAge(30);
        response.setLimit(50);
        response.setEmail("john.doe@example.com");
        response.setOptionalField(null);
        response.setMandatoryField("some value");
        response.setGrade("B");
        response.setUsername("user_johndoe");
        response.setRoles(List.of("admin", "editor"));
        response.setScore(85);
        return response;
    }
}

