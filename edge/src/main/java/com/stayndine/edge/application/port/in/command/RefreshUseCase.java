package com.stayndine.edge.application.port.in.command;

import com.stayndine.edge.infrastructure.in.rest.dto.auth.LoginResponse;
import com.stayndine.edge.infrastructure.in.rest.dto.auth.RefreshRequest;

public interface RefreshUseCase {
    LoginResponse handle(RefreshRequest req);
}
