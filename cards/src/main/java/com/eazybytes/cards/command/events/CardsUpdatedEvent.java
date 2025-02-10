package com.eazybytes.cards.command.events;

import lombok.*;

@Data
public class CardsUpdatedEvent {

    private Long cardNumber;
    private String mobileNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
    private boolean activeSw;
}
