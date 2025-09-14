package com.stayndine.edge.infrastructure.out.rest.identity.dto;

import java.util.Set;
import java.util.UUID;

public record InternalLoginResponse(
        String accessToken,
        String refreshToken,
        UUID userId,
        String email,
        Set<String> roles
) {
}