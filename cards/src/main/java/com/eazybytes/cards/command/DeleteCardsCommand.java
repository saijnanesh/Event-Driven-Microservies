package com.eazybytes.cards.command;

import lombok.*;
import org.axonframework.modelling.command.*;

@Data
@Builder
public class DeleteCardsCommand {

    @TargetAggregateIdentifier
    private final Long cardNumber;
    private final boolean activeSw;
}
