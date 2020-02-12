package com.thoughtworks.todolist.exception;

import lombok.Getter;
import lombok.Setter;

public class Response {

    @Getter @Setter
    private int statusCode;

    @Getter @Setter
    private String message;

    public Response() {
    }

    public Response(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Response(String message) {
        this.message = message;
    }
}
