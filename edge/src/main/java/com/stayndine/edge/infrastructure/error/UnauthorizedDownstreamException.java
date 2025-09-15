package com.stayndine.edge.infrastructure.error;

import org.springframework.http.HttpStatus;

public class UnauthorizedDownstreamException extends DownstreamException {
    public UnauthorizedDownstreamException(String service, String body) {
        super(HttpStatus.UNAUTHORIZED, service, body);
    }
}