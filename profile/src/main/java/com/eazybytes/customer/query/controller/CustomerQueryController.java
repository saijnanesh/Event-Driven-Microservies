package com.eazybytes.customer.query.controller;

import com.eazybytes.customer.dto.*;
import com.eazybytes.customer.query.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.axonframework.messaging.responsetypes.*;
import org.axonframework.queryhandling.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class CustomerQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam("mobileNumber")
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                            String mobileNumber) {
        FindCustomerQuery findCustomerQuery = new FindCustomerQuery(mobileNumber);
        CustomerDto customerDto = queryGateway.query(findCustomerQuery, ResponseTypes.instanceOf(CustomerDto.class)).join();
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
}
