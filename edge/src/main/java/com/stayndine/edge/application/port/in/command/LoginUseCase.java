package com.stayndine.edge.application.port.in.command;

import com.stayndine.edge.infrastructure.in.rest.dto.auth.LoginRequest;
import com.stayndine.edge.infrastructure.in.rest.dto.auth.LoginResponse;

public interface LoginUseCase {
    LoginResponse handle(LoginRequest req);
}
