package com.stayndine.edge.infrastructure.error;

import org.springframework.http.HttpStatus;

public class NotFoundDownstreamException extends DownstreamException {
    public NotFoundDownstreamException(String service, String body) {
        super(HttpStatus.NOT_FOUND, service, body);
    }
}