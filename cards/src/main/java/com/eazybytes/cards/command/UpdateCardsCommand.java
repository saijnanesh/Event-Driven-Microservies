package com.eazybytes.cards.command;

import lombok.*;
import org.axonframework.modelling.command.*;

@Data
@Builder
public class UpdateCardsCommand {

    @TargetAggregateIdentifier
    private final Long cardNumber;
    private final String mobileNumber;
    private final String cardType;
    private final int totalLimit;
    private final int amountUsed;
    private final int availableAmount;
    private final boolean activeSw;
}
