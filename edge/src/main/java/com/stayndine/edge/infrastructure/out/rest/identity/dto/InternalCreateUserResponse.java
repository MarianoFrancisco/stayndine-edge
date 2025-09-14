package com.stayndine.edge.infrastructure.out.rest.identity.dto;

import java.util.UUID;

public record InternalCreateUserResponse(UUID id, String email) {
}
