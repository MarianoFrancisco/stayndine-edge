package com.stayndine.edge.application.service;

import com.stayndine.edge.application.port.in.command.SignupUseCase;
import com.stayndine.edge.application.port.out.CustomersClient;
import com.stayndine.edge.application.port.out.IdentityClient;
import com.stayndine.edge.infrastructure.in.rest.dto.signup.SignupRequest;
import com.stayndine.edge.infrastructure.in.rest.dto.signup.SignupResponse;
import com.stayndine.edge.infrastructure.out.rest.customers.dto.InternalCustomerCreateRequest;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalAssignRolesRequest;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalCreateUserRequest;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.InternalLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignupService implements SignupUseCase {

    private final IdentityClient identity;
    private final CustomersClient customers;

    @Override
    public SignupResponse handle(SignupRequest req) {
        var userRes = identity.createUser(new InternalCreateUserRequest(req.email(), req.password()));

        identity.assignRoles(userRes.id(), new InternalAssignRolesRequest(Set.of("CUSTOMER")));

        var custRes = customers.createCustomer(new InternalCustomerCreateRequest(
                userRes.id(), req.email(), req.firstName(), req.lastName(), req.phone(), req.birthDate()
        ));

        var loginRes = identity.login(new InternalLoginRequest(req.email(), req.password()));

        return new SignupResponse(
                loginRes.accessToken(),
                loginRes.refreshToken(),
                userRes.id(),
                custRes.id(),
                loginRes.roles()
        );
    }
}