package com.eazybytes.accounts.query.controller;

import com.eazybytes.accounts.dto.*;
import com.eazybytes.accounts.query.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.axonframework.commandhandling.gateway.*;
import org.axonframework.messaging.responsetypes.*;
import org.axonframework.queryhandling.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class AccountQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("/fetch")
    public ResponseEntity<AccountsDto> fetchAccountDetails(@RequestParam("mobileNumber")
                                                           @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        FindAccountQuery findAccountQuery = new FindAccountQuery(mobileNumber);
        AccountsDto customer = queryGateway.query(findAccountQuery, ResponseTypes.instanceOf(AccountsDto.class)).join();
        return ResponseEntity.status(org.springframework.http.HttpStatus.OK).body(customer);
    }
}
