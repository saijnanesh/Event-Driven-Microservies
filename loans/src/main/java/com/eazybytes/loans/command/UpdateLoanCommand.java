package com.eazybytes.loans.command;

import lombok.*;
import org.axonframework.modelling.command.*;

@Data
@Builder
public class UpdateLoanCommand {

    @TargetAggregateIdentifier
    private final Long loanNumber;
    private final String mobileNumber;
    private final String loanType;
    private final int totalLoan;
    private final int amountPaid;
    private final int outstandingAmount;
    private final boolean activeSw;
}
