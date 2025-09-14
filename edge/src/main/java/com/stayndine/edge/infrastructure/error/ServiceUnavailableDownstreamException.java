package com.stayndine.edge.infrastructure.error;

import org.springframework.http.HttpStatus;

public class ServiceUnavailableDownstreamException extends DownstreamException {
    public ServiceUnavailableDownstreamException(String service, String body) {
        super(HttpStatus.SERVICE_UNAVAILABLE, service, body);
    }
}