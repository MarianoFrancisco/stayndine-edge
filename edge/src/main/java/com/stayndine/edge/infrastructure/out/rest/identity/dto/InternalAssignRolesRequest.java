package com.stayndine.edge.infrastructure.out.rest.identity.dto;

import java.util.Set;

public record InternalAssignRolesRequest(Set<String> roles) {
}