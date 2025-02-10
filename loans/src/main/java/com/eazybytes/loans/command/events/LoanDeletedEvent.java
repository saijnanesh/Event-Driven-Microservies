package com.eazybytes.loans.command.events;

import lombok.*;

@Data
public class LoanDeletedEvent {

    private Long loanNumber;
    private boolean activeSw;
}
