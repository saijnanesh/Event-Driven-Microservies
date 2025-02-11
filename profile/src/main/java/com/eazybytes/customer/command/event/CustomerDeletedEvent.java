package com.eazybytes.customer.command.event;


import lombok.Data;

/*
 *  Noun + Verb(Past tense) + Event
 * */
@Data
public class CustomerDeletedEvent {
    private String customerId;
    private boolean activeSw;

}
