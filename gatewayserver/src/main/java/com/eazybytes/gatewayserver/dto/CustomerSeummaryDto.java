package com.eazybytes.gatewayserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerSeummaryDto {

    private CustomerDto customer;
    private AccountsDto account;
    private LoansDto loan;
    private CardsDto card;

}
