package com.eazybytes.cards.query.handler;


import com.eazybytes.cards.dto.*;
import com.eazybytes.cards.query.*;
import com.eazybytes.cards.service.*;
import lombok.*;
import org.axonframework.queryhandling.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class CardsQueryHandler {

    private final ICardsService iCardsService;

    @QueryHandler
    public CardsDto findCard(FindCardQuery query) {
        CardsDto card = iCardsService.fetchCard(query.getMobileNumber());
        return card;
    }
}
