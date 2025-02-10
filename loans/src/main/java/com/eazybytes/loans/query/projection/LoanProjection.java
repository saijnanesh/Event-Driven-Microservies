package com.eazybytes.loans.query.projection;

import com.eazybytes.loans.command.events.*;
import com.eazybytes.loans.dto.*;
import com.eazybytes.loans.entity.*;
import com.eazybytes.loans.service.*;
import lombok.*;
import org.axonframework.eventhandling.*;
import org.springframework.beans.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class LoanProjection {

    private final ILoansService iLoansService;

    @EventHandler
    private void on(LoanCreatedEvent loanCreatedEvent) {
        Loans loans = new Loans();
        BeanUtils.copyProperties(loanCreatedEvent, loans);
        iLoansService.createLoan(loans);
    }

    @EventHandler
    private void on(LoanUpdatedEvent loanUpdatedEvent) {
        LoansDto loans = new LoansDto();
        BeanUtils.copyProperties(loanUpdatedEvent, loans);
        iLoansService.updateLoan(loans);
    }

    @EventHandler
    private void on(LoanDeletedEvent loanDeletedEvent) {
        iLoansService.deleteLoan(loanDeletedEvent.getLoanNumber());
    }
}
