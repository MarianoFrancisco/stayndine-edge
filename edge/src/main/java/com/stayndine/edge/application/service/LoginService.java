package com.stayndine.edge.application.service;

import com.stayndine.edge.application.port.in.command.LoginUseCase;
import com.stayndine.edge.application.port.out.IdentityClient;
import com.stayndine.edge.infrastructure.in.rest.dto.auth.LoginRequest;
import com.stayndine.edge.infrastructure.in.rest.dto.auth.LoginResponse;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final IdentityClient identity;

    @Override
    public LoginResponse handle(LoginRequest req) {
        var res = identity.login(new InternalLoginRequest(req.email(), req.password()));
        return new LoginResponse(res.accessToken(), res.refreshToken(), res.userId(), res.roles());
    }
}