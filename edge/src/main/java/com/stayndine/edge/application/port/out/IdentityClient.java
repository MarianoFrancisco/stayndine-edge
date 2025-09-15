package com.stayndine.edge.application.port.out;

import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalAssignRolesRequest;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalCreateUserRequest;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalCreateUserResponse;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalLoginRequest;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalLoginResponse;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalRefreshRequest;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalLogoutRequest;

import java.util.UUID;

public interface IdentityClient {
    InternalCreateUserResponse createUser(InternalCreateUserRequest req);

    void assignRoles(UUID userId, InternalAssignRolesRequest req);

    InternalLoginResponse login(InternalLoginRequest req);

    InternalLoginResponse refresh(InternalRefreshRequest req);

    void logout(InternalLogoutRequest req);
}