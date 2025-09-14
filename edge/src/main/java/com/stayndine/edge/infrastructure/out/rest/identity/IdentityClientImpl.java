package com.stayndine.edge.infrastructure.out.rest.identity;

import com.stayndine.edge.application.port.out.IdentityClient;
import com.stayndine.edge.infrastructure.error.*;
import com.stayndine.edge.infrastructure.out.rest.identity.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IdentityClientImpl implements IdentityClient {

    private static final String SERVICE = "identity";
    private final RestClient identityRestClient;

    @Override
    public InternalCreateUserResponse createUser(InternalCreateUserRequest req) {
        try {
            var res = identityRestClient.post()
                    .uri("/internal/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(req)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        var body = readBodySafe(response);
                        int sc = response.getStatusCode().value();
                        switch (sc) {
                            case 400 -> throw new BadRequestDownstreamException(SERVICE, body);
                            case 401 -> throw new UnauthorizedDownstreamException(SERVICE, body);
                            case 403 -> throw new ForbiddenDownstreamException(SERVICE, body);
                            case 404 -> throw new NotFoundDownstreamException(SERVICE, body);
                            case 409 -> throw new ConflictDownstreamException(SERVICE, body);
                            default -> throw new DownstreamException(HttpStatus.valueOf(sc), SERVICE, body);
                        }
                    })
                    .onStatus(HttpStatusCode::is5xxServerError,
                            (request, response) -> {
                                throw new ServerErrorDownstreamException(SERVICE, readBodySafe(response));
                            })
                    .body(InternalCreateUserResponse.class);

            if (res == null)
                throw new ServerErrorDownstreamException(SERVICE, "Empty response from identity.createUser");
            return res;
        } catch (ResourceAccessException ex) {
            throw new ServiceUnavailableDownstreamException(SERVICE, "Identity unreachable: " + ex.getMessage());
        }
    }

    @Override
    public void assignRoles(UUID userId, InternalAssignRolesRequest req) {
        try {
            identityRestClient.post()
                    .uri("/internal/manager/users/{id}/roles", userId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(req)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        var body = readBodySafe(response);
                        int sc = response.getStatusCode().value();
                        switch (sc) {
                            case 400 -> throw new BadRequestDownstreamException(SERVICE, body);
                            case 401 -> throw new UnauthorizedDownstreamException(SERVICE, body);
                            case 403 -> throw new ForbiddenDownstreamException(SERVICE, body);
                            case 404 -> throw new NotFoundDownstreamException(SERVICE, body);
                            case 409 -> throw new ConflictDownstreamException(SERVICE, body);
                            default -> throw new DownstreamException(HttpStatus.valueOf(sc), SERVICE, body);
                        }
                    })
                    .onStatus(HttpStatusCode::is5xxServerError,
                            (request, response) -> {
                                throw new ServerErrorDownstreamException(SERVICE, readBodySafe(response));
                            })
                    .toBodilessEntity();
        } catch (ResourceAccessException ex) {
            throw new ServiceUnavailableDownstreamException(SERVICE, "Identity unreachable: " + ex.getMessage());
        }
    }

    @Override
    public InternalLoginResponse login(InternalLoginRequest req) {
        try {
            var res = identityRestClient.post()
                    .uri("/internal/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(req)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        var body = readBodySafe(response);
                        int sc = response.getStatusCode().value();
                        switch (sc) {
                            case 400 -> throw new BadRequestDownstreamException(SERVICE, body);
                            case 401 -> throw new UnauthorizedDownstreamException(SERVICE, body);
                            case 403 -> throw new ForbiddenDownstreamException(SERVICE, body);
                            case 404 -> throw new NotFoundDownstreamException(SERVICE, body);
                            default -> throw new DownstreamException(HttpStatus.valueOf(sc), SERVICE, body);
                        }
                    })
                    .onStatus(HttpStatusCode::is5xxServerError,
                            (request, response) -> {
                                throw new ServerErrorDownstreamException(SERVICE, readBodySafe(response));
                            })
                    .body(InternalLoginResponse.class);

            if (res == null) throw new ServerErrorDownstreamException(SERVICE, "Empty response from identity.login");
            return res;
        } catch (ResourceAccessException ex) {
            throw new ServiceUnavailableDownstreamException(SERVICE, "Identity unreachable: " + ex.getMessage());
        }
    }

    @Override
    public InternalLoginResponse refresh(InternalRefreshRequest req) {
        try {
            var res = identityRestClient.post()
                    .uri("/internal/auth/refresh")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(req)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        var body = readBodySafe(response);
                        int sc = response.getStatusCode().value();
                        switch (sc) {
                            case 400 -> throw new BadRequestDownstreamException(SERVICE, body);
                            case 401 -> throw new UnauthorizedDownstreamException(SERVICE, body);
                            case 403 -> throw new ForbiddenDownstreamException(SERVICE, body);
                            default -> throw new DownstreamException(HttpStatus.valueOf(sc), SERVICE, body);
                        }
                    })
                    .onStatus(HttpStatusCode::is5xxServerError,
                            (request, response) -> {
                                throw new ServerErrorDownstreamException(SERVICE, readBodySafe(response));
                            })
                    .body(InternalLoginResponse.class);

            if (res == null) throw new ServerErrorDownstreamException(SERVICE, "Empty response from identity.refresh");
            return res;
        } catch (ResourceAccessException ex) {
            throw new ServiceUnavailableDownstreamException(SERVICE, "Identity unreachable: " + ex.getMessage());
        }
    }

    @Override
    public void logout(InternalLogoutRequest req) {
        try {
            identityRestClient.post()
                    .uri("/internal/auth/logout")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(req)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        var body = readBodySafe(response);
                        int sc = response.getStatusCode().value();
                        switch (sc) {
                            case 400 -> throw new BadRequestDownstreamException(SERVICE, body);
                            case 401 -> throw new UnauthorizedDownstreamException(SERVICE, body);
                            case 403 -> throw new ForbiddenDownstreamException(SERVICE, body);
                            default -> throw new DownstreamException(HttpStatus.valueOf(sc), SERVICE, body);
                        }
                    })
                    .onStatus(HttpStatusCode::is5xxServerError,
                            (request, response) -> {
                                throw new ServerErrorDownstreamException(SERVICE, readBodySafe(response));
                            })
                    .toBodilessEntity();
        } catch (ResourceAccessException ex) {
            throw new ServiceUnavailableDownstreamException(SERVICE, "Identity unreachable: " + ex.getMessage());
        }
    }

    private static String readBodySafe(org.springframework.http.client.ClientHttpResponse response) {
        try (var is = response.getBody()) {
            String status;
            try {
                status = String.valueOf(response.getStatusCode());
            } catch (IOException ignored) {
                status = "status unreadable";
            }

            if (is == null) {
                return "(" + status + " no body)";
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "(body unreadable: " + e.getMessage() + ")";
        }
    }

}
