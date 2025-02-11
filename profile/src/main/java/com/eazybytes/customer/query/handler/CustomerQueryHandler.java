package com.eazybytes.customer.query.handler;

import com.eazybytes.customer.dto.*;
import com.eazybytes.customer.query.*;
import com.eazybytes.customer.service.*;
import lombok.*;
import org.axonframework.queryhandling.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class CustomerQueryHandler {

    private final ICustomerService iCustomerService;

    @QueryHandler
    public CustomerDto findCustomer(FindCustomerQuery findCustomerQuery) {
        return iCustomerService.fetchCustomer(findCustomerQuery.getMobileNumber());
    }
}
