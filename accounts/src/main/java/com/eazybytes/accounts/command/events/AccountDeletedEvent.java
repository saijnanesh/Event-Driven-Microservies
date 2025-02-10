package com.eazybytes.accounts.command.events;

import lombok.*;

@Data
public class AccountDeletedEvent {

    private Long accountNumber;
    private boolean activeSw;
}
