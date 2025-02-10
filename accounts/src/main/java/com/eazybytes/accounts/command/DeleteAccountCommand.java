package com.eazybytes.accounts.command;


import lombok.*;
import org.axonframework.modelling.command.*;

@Builder
@Data
public class DeleteAccountCommand {

    @TargetAggregateIdentifier
    private final Long accountNumber;
    private final boolean activeSw;
}
