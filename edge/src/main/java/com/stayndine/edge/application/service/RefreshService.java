package com.stayndine.edge.application.service;

import com.stayndine.edge.application.port.in.command.RefreshUseCase;
import com.stayndine.edge.application.port.out.IdentityClient;
import com.stayndine.edge.infrastructure.in.rest.dto.auth.LoginResponse;
import com.stayndine.edge.infrastructure.in.rest.dto.auth.RefreshRequest;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalRefreshRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService implements RefreshUseCase {

    private final IdentityClient identity;

    @Override
    public LoginResponse handle(RefreshRequest req) {
        var res = identity.refresh(new InternalRefreshRequest(req.refreshToken()));
        return new LoginResponse(res.accessToken(), res.refreshToken(), res.userId(), res.roles());
    }
}
