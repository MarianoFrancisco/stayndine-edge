package com.stayndine.edge.infrastructure.out.rest.customers.dto;

import java.time.LocalDate;
import java.util.UUID;

public record InternalCustomerCreateRequest(
        UUID userId,
        String email,
        String firstName,
        String lastName,
        String phone,
        LocalDate birthDate
) {
}