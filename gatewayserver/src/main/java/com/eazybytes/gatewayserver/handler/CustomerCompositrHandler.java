package com.eazybytes.gatewayserver.handler;


import com.eazybytes.gatewayserver.dto.*;
import com.eazybytes.gatewayserver.service.client.CustomerSummaryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerCompositrHandler {

    private final CustomerSummaryClient client;

   public Mono<ServerResponse> fetchCustomerSummery(ServerRequest request) {
        String mobileNumber = request.queryParam("mobileNumber").get();

        Mono<ResponseEntity<CustomerDto>> customerDetails = client.fetchCustomerDetails(mobileNumber);
        Mono<ResponseEntity<AccountsDto>> accountDetails = client.fetchAccountDetails(mobileNumber);
        Mono<ResponseEntity<LoansDto>> loanDetails = client.fetchLoanDetails(mobileNumber);
        Mono<ResponseEntity<CardsDto>> cardDetails = client.fetchCardDetails(mobileNumber);

        return Mono.zip(customerDetails, accountDetails, loanDetails, cardDetails)
                .flatMap(tuple -> {
                    CustomerDto customerDto = tuple.getT1().getBody();
                    AccountsDto accountsDto = tuple.getT2().getBody();
                    LoansDto loansDto = tuple.getT3().getBody();
                    CardsDto cardsDto = tuple.getT4().getBody();
                    CustomerSeummaryDto customerSummaryDto = new CustomerSeummaryDto(customerDto, accountsDto, loansDto, cardsDto);
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(customerSummaryDto));
                });

    }
}
