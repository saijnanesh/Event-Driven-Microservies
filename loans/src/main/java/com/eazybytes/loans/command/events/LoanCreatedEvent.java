package com.eazybytes.loans.command.events;

import lombok.*;

@Data
public class LoanCreatedEvent {

    private Long loanNumber;
    private String mobileNumber;
    private String loanType;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
    private boolean activeSw;
    private String loanStatus;
}
