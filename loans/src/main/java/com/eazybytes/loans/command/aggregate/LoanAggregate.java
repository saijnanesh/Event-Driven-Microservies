package com.eazybytes.loans.command.aggregate;

import com.eazybytes.loans.command.*;
import com.eazybytes.loans.command.events.*;
import org.axonframework.commandhandling.*;
import org.axonframework.eventsourcing.*;
import org.axonframework.modelling.command.*;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.*;

@Aggregate
public class LoanAggregate {

    @AggregateIdentifier
    private Long loanNumber;
    private String mobileNumber;
    private String loanType;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
    private boolean activeSw;
    private String loanStatus;

    public LoanAggregate() {

    }

    @CommandHandler
    public LoanAggregate(CreateLoanCommand createCommand) {
        LoanCreatedEvent loanCreatedEvent = new LoanCreatedEvent();
        BeanUtils.copyProperties(createCommand, loanCreatedEvent);
        AggregateLifecycle.apply(loanCreatedEvent);
    }

    @EventSourcingHandler
    public void on(LoanCreatedEvent loanCreatedEvent) {
        this.loanNumber = loanCreatedEvent.getLoanNumber();
        this.mobileNumber = loanCreatedEvent.getMobileNumber();
        this.loanType = loanCreatedEvent.getLoanType();
        this.loanStatus = loanCreatedEvent.getLoanStatus();
        this.totalLoan = loanCreatedEvent.getTotalLoan();
        this.amountPaid = loanCreatedEvent.getAmountPaid();
        this.outstandingAmount = loanCreatedEvent.getOutstandingAmount();
        this.activeSw = loanCreatedEvent.isActiveSw();
    }

    @CommandHandler
    public void handle(UpdateLoanCommand updateCommand) {
        LoanUpdatedEvent loanUpdatedEvent = new LoanUpdatedEvent();
        BeanUtils.copyProperties(updateCommand, loanUpdatedEvent);
        AggregateLifecycle.apply(loanUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(LoanUpdatedEvent loanUpdatedEvent) {
        this.loanType = loanUpdatedEvent.getLoanType();
        this.totalLoan = loanUpdatedEvent.getTotalLoan();
        this.amountPaid = loanUpdatedEvent.getAmountPaid();
        this.outstandingAmount = loanUpdatedEvent.getOutstandingAmount();
    }

    @CommandHandler
    public void handle(DeleteLoanCommand deleteCommand) {
        LoanDeletedEvent loanDeletedEvent = new LoanDeletedEvent();
        BeanUtils.copyProperties(deleteCommand, loanDeletedEvent);
        AggregateLifecycle.apply(loanDeletedEvent);
    }

    @EventSourcingHandler
    public void on(LoanDeletedEvent loanDeletedEvent) {
        this.activeSw = loanDeletedEvent.isActiveSw();
    }
}
