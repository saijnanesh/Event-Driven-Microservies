package com.eazybytes.cards.query.projection;

import com.eazybytes.cards.command.events.*;
import com.eazybytes.cards.dto.*;
import com.eazybytes.cards.entity.*;
import com.eazybytes.cards.service.*;
import lombok.*;
import org.axonframework.eventhandling.*;
import org.springframework.beans.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class CardProjection {

    private final ICardsService iCardsService;

    @EventHandler
    private void handle(CardsCreatedEvent cardsCreatedEvent) {
        Cards cards = new Cards();
        BeanUtils.copyProperties(cardsCreatedEvent, cards);
        iCardsService.createCard(cards);
    }

    @EventHandler
    private void handle(CardsUpdatedEvent cardsUpdatedEvent) {
        CardsDto cards = new CardsDto();
        BeanUtils.copyProperties(cardsUpdatedEvent, cards);
        iCardsService.updateCard(cards);
    }

    @EventHandler
    private void handle(CardsDeletedEvent cardsDeletedEvent) {
        iCardsService.deleteCard(cardsDeletedEvent.getCardNumber());
    }
}
