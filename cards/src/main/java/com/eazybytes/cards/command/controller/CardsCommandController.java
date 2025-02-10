package com.eazybytes.cards.command.controller;


import com.eazybytes.cards.command.*;
import com.eazybytes.cards.constants.*;
import com.eazybytes.cards.dto.*;
import com.eazybytes.cards.entity.*;
import com.eazybytes.cards.mapper.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.axonframework.commandhandling.gateway.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class CardsCommandController {


    private final CommandGateway commandGateway;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam("mobileNumber")
                                                  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {

        Cards newCard = CardsMapper.createNewCard(mobileNumber);

        CreateCardsCommand createCardsCommand = CreateCardsCommand.builder()
                .cardType(newCard.getCardType())
                .cardNumber(newCard.getCardNumber())
                .mobileNumber(mobileNumber)
                .amountUsed(newCard.getAmountUsed())
                .availableAmount(newCard.getAvailableAmount())
                .totalLimit(newCard.getTotalLimit())
                .activeSw(newCard.isActiveSw()).build();

        commandGateway.sendAndWait(createCardsCommand);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {

        UpdateCardsCommand updateCardsCommand = UpdateCardsCommand.builder()
                .cardType(cardsDto.getCardType())
                .cardNumber(cardsDto.getCardNumber())
                .mobileNumber(cardsDto.getMobileNumber())
                .amountUsed(cardsDto.getAmountUsed())
                .availableAmount(cardsDto.getAvailableAmount())
                .totalLimit(cardsDto.getTotalLimit())
                .activeSw(cardsDto.isActiveSw()).build();

        commandGateway.sendAndWait(updateCardsCommand);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }

    @PatchMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam("cardNumber")
                                                         Long cardNumber) {
        DeleteCardsCommand deleteCardsCommand = DeleteCardsCommand.builder()
                .cardNumber(cardNumber).activeSw(CardsConstants.IN_ACTIVE_SW).build();

        commandGateway.sendAndWait(deleteCardsCommand);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }
}
