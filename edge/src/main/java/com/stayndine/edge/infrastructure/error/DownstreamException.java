package com.stayndine.edge.infrastructure.error;

import org.springframework.http.HttpStatus;

public class DownstreamException extends RuntimeException {
    private final HttpStatus status;
    private final String service;
    private final String body;

    public DownstreamException(HttpStatus status, String service, String body) {
        super("Downstream " + service + " returned " + status.value() + " " + status + (body != null ? " -> " + body : ""));
        this.status = status;
        this.service = service;
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getService() {
        return service;
    }

    public String getBody() {
        return body;
    }
}
