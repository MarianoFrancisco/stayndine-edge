package com.stayndine.edge.infrastructure.error;

import org.springframework.http.HttpStatus;

public class ServerErrorDownstreamException extends DownstreamException {
    public ServerErrorDownstreamException(String service, String body) {
        super(HttpStatus.BAD_GATEWAY, service, body);
    }
}