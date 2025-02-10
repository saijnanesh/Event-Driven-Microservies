package com.eazybytes.cards.query.controller;

import com.eazybytes.cards.dto.*;
import com.eazybytes.cards.query.*;
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
public class CardsQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam("mobileNumber")
                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                     String mobileNumber) {

        FindCardQuery findCardQuery = new FindCardQuery(mobileNumber);
        CardsDto card = queryGateway.query(findCardQuery, ResponseTypes.instanceOf(CardsDto.class)).join();
        return ResponseEntity.status(org.springframework.http.HttpStatus.OK).body(card);

    }
}
