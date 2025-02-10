package com.eazybytes.loans.query.controller;


import com.eazybytes.loans.dto.*;
import com.eazybytes.loans.query.*;
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
public class LoanQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam("mobileNumber")
                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {
        FindLoanQuery findLoanQuery = new FindLoanQuery(mobileNumber);
        LoansDto loan = queryGateway.query(findLoanQuery, ResponseTypes.instanceOf(LoansDto.class)).join();
        return ResponseEntity.status(HttpStatus.OK).body(loan);
    }
}
