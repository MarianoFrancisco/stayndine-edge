package com.stayndine.edge.infrastructure.error;

import org.springframework.http.HttpStatus;

public class ConflictDownstreamException extends DownstreamException {
    public ConflictDownstreamException(String service, String body) {
        super(HttpStatus.CONFLICT, service, body);
    }
}