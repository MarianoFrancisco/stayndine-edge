package com.stayndine.edge.application.port.in.command;

import com.stayndine.edge.infrastructure.in.rest.dto.signup.SignupRequest;
import com.stayndine.edge.infrastructure.in.rest.dto.signup.SignupResponse;

public interface SignupUseCase {
    SignupResponse handle(SignupRequest req);
}
