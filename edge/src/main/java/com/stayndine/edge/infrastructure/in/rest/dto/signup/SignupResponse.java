package com.stayndine.edge.infrastructure.in.rest.dto.signup;

import java.util.Set;
import java.util.UUID;

public record SignupResponse(
        String accessToken,
        String refreshToken,
        UUID userId,
        UUID customerId,
        Set<String> roles
) {
}