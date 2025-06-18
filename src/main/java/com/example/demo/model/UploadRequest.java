package com.example.demo.model;

public class UploadRequest {

    private String name;
    private String description;
    private String uploader;

    public UploadRequest() {
    }

    public UploadRequest(String name, String description, String uploader) {
        this.name = name;
        this.description = description;
        this.uploader = uploader;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        return "UploadRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uploader='" + uploader + '\'' +
                '}';
    }
}