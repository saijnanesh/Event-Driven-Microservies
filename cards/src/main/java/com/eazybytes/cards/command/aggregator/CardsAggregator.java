package com.eazybytes.cards.command.aggregator;

import com.eazybytes.cards.command.*;
import com.eazybytes.cards.command.events.*;
import org.axonframework.commandhandling.*;
import org.axonframework.eventsourcing.*;
import org.axonframework.modelling.command.*;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.*;

@Aggregate
public class CardsAggregator {

    @AggregateIdentifier
    private Long cardNumber;
    private String mobileNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
    private boolean activeSw;


    public CardsAggregator() {

    }

    @CommandHandler
    public CardsAggregator(CreateCardsCommand createCardsCommand) {
        CardsCreatedEvent cardsCreatedEvent = new CardsCreatedEvent();
        BeanUtils.copyProperties(createCardsCommand, cardsCreatedEvent);
        AggregateLifecycle.apply(cardsCreatedEvent);
    }

    @EventSourcingHandler
    public void on(CardsCreatedEvent cardsCreatedEvent) {
        this.cardNumber = cardsCreatedEvent.getCardNumber();
        this.mobileNumber = cardsCreatedEvent.getMobileNumber();
        this.cardType = cardsCreatedEvent.getCardType();
        this.totalLimit = cardsCreatedEvent.getTotalLimit();
        this.amountUsed = cardsCreatedEvent.getAmountUsed();
        this.availableAmount = cardsCreatedEvent.getAvailableAmount();
        this.activeSw = cardsCreatedEvent.isActiveSw();
    }

    @CommandHandler
    public void handle(UpdateCardsCommand updateCommand) {
        CardsUpdatedEvent cardUpdatedEvent = new CardsUpdatedEvent();
        BeanUtils.copyProperties(updateCommand, cardUpdatedEvent);
        AggregateLifecycle.apply(cardUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(CardsUpdatedEvent cardUpdatedEvent) {
        this.cardType = cardUpdatedEvent.getCardType();
        this.totalLimit = cardUpdatedEvent.getTotalLimit();
        this.amountUsed = cardUpdatedEvent.getAmountUsed();
        this.availableAmount = cardUpdatedEvent.getAvailableAmount();
    }

    @CommandHandler
    public void handle(DeleteCardsCommand deleteCommand) {
        CardsDeletedEvent cardDeletedEvent = new CardsDeletedEvent();
        BeanUtils.copyProperties(deleteCommand, cardDeletedEvent);
        AggregateLifecycle.apply(cardDeletedEvent);
    }

    @EventSourcingHandler
    public void on(CardsDeletedEvent cardDeletedEvent) {
        this.activeSw = cardDeletedEvent.isActiveSw();
    }
}
