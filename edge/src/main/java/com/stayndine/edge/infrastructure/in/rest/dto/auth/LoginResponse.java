package com.stayndine.edge.infrastructure.in.rest.dto.auth;

import java.util.Set;
import java.util.UUID;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        UUID userId,
        Set<String> roles
) {
}