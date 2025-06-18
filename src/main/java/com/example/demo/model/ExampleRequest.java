package com.example.demo.model;

public class ExampleRequest {

    private String name;
    private int value;
    private boolean active;

    public ExampleRequest() {
    }

    public ExampleRequest(String name, int value, boolean active) {
        this.name = name;
        this.value = value;
        this.active = active;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // toString (opcional, útil para log/debug)
    @Override
    public String toString() {
        return "ExampleRequest{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", active=" + active +
                '}';
    }
}