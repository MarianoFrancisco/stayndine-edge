package com.stayndine.edge.infrastructure.out.rest.customers;

import com.stayndine.edge.application.port.out.CustomersClient;
import com.stayndine.edge.infrastructure.out.rest.customers.dto.InternalCustomerCreateRequest;
import com.stayndine.edge.infrastructure.out.rest.customers.dto.InternalCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class CustomersClientImpl implements CustomersClient {

    private final RestClient customersRestClient;

    @Override
    public InternalCustomerResponse createCustomer(InternalCustomerCreateRequest req) {
        return customersRestClient.post()
                .uri("/internal/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .body(req)
                .retrieve()
                .body(InternalCustomerResponse.class);
    }
}