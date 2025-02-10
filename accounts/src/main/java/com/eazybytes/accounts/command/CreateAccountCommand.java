package com.eazybytes.accounts.command;

import lombok.*;
import org.axonframework.modelling.command.*;

@Builder
@Data
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private final Long accountNumber;
    private final String accountType;
    private final String branchAddress;
    private final String mobileNumber;
    private final boolean activeSw;
}
