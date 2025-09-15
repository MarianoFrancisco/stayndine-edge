package com.stayndine.edge.application.port.in.command;

import com.stayndine.edge.infrastructure.in.rest.dto.auth.LogoutRequest;

public interface LogoutUseCase {
    void handle(LogoutRequest req);
}
