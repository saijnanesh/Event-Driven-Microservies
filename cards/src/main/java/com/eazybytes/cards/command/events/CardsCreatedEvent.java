package com.eazybytes.cards.command.events;

import lombok.*;

@Data
public class CardsCreatedEvent {

    private Long cardNumber;
    private String mobileNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
    private boolean activeSw;
}
