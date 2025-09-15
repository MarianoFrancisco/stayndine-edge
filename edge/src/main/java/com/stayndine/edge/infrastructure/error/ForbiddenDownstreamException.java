package com.stayndine.edge.infrastructure.error;

import org.springframework.http.HttpStatus;

public class ForbiddenDownstreamException extends DownstreamException {
    public ForbiddenDownstreamException(String service, String body) {
        super(HttpStatus.FORBIDDEN, service, body);
    }
}