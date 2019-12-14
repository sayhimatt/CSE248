package com.guidi.collegesearch.backCode.model;

public class Username {
    private String email;

    public Username(String e) {
        this.email = e;
    }

    @Override
    public String toString() {
        return "Username: " + email;
    }

    public String getUsername() {
        return email;
    }
}
