package com.stayndine.edge.application.service;

import com.stayndine.edge.application.port.in.command.LogoutUseCase;
import com.stayndine.edge.application.port.out.IdentityClient;
import com.stayndine.edge.infrastructure.in.rest.dto.auth.LogoutRequest;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalLogoutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {

    private final IdentityClient identity;

    @Override
    public void handle(LogoutRequest req) {
        identity.logout(new InternalLogoutRequest(req.refreshToken()));
    }
}