package com.stayndine.edge.infrastructure.out.rest.customers.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record InternalCustomerResponse(
        UUID id,
        UUID userId,
        String email,
        String firstName,
        String lastName,
        String phone,
        LocalDate birthDate,
        String preferencesJson,
        Instant createdAt,
        Instant updatedAt
) {
}