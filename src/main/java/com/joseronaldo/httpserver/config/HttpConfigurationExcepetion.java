package com.joseronaldo.httpserver.config;

public class HttpConfigurationExcepetion extends RuntimeException {

    public HttpConfigurationExcepetion() {
    }

    public HttpConfigurationExcepetion(String message) {
        super(message);
    }

    public HttpConfigurationExcepetion(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpConfigurationExcepetion(Throwable cause) {
        super(cause);
    }
}
