package com.eazybytes.cards.command.events;

import lombok.*;

@Data
public class CardsDeletedEvent {

    private Long cardNumber;
    private boolean activeSw;
}
