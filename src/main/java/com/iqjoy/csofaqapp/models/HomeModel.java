package com.iqjoy.cso.models;

public class HomeModel {

    private String message;

    public HomeModel(String message) {
        System.out.println(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
