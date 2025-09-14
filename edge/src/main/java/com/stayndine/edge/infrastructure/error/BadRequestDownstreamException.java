package com.stayndine.edge.infrastructure.error;

import org.springframework.http.HttpStatus;

public class BadRequestDownstreamException extends DownstreamException {
    public BadRequestDownstreamException(String service, String body) {
        super(HttpStatus.BAD_REQUEST, service, body);
    }
}
