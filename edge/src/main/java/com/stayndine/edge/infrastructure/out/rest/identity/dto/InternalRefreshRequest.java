package com.stayndine.edge.infrastructure.out.rest.identity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InternalRefreshRequest(
        String refreshToken
) {
}
