package com.stayndine.edge.application.port.out;

import com.stayndine.edge.infrastructure.out.rest.customers.dto.InternalCustomerCreateRequest;
import com.stayndine.edge.infrastructure.out.rest.customers.dto.InternalCustomerResponse;

public interface CustomersClient {
    InternalCustomerResponse createCustomer(InternalCustomerCreateRequest req);
}
