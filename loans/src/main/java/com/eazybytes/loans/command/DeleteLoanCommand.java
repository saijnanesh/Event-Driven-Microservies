package com.eazybytes.loans.command;

import lombok.*;
import org.axonframework.modelling.command.*;

@Data
@Builder
public class DeleteLoanCommand {

    @TargetAggregateIdentifier
    private final Long loanNumber;
    private final boolean activeSw;
}
