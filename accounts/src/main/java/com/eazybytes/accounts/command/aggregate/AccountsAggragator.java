package com.eazybytes.accounts.command.aggregate;

import com.eazybytes.accounts.command.*;
import com.eazybytes.accounts.command.events.*;
import org.axonframework.commandhandling.*;
import org.axonframework.eventsourcing.*;
import org.axonframework.modelling.command.*;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.*;

@Aggregate
public class AccountsAggragator {

    @AggregateIdentifier
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private String mobileNumber;
    private boolean activeSw;

    public AccountsAggragator() {

    }

    @CommandHandler
    public AccountsAggragator(CreateAccountCommand createAccountCommand) {
        AccountCreatedEvent accountCreatedEvent = new AccountCreatedEvent();
        BeanUtils.copyProperties(createAccountCommand, accountCreatedEvent);
        AggregateLifecycle.apply(accountCreatedEvent);
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        this.accountNumber = accountCreatedEvent.getAccountNumber();
        this.accountType = accountCreatedEvent.getAccountType();
        this.branchAddress = accountCreatedEvent.getBranchAddress();
        this.mobileNumber = accountCreatedEvent.getMobileNumber();
        this.activeSw = accountCreatedEvent.isActiveSw();
    }

    @CommandHandler
    public void handle(UpdateAccountCommand updateCommand) {
        AccountUpdatedEvent accountUpdatedEvent = new AccountUpdatedEvent();
        BeanUtils.copyProperties(updateCommand, accountUpdatedEvent);
        AggregateLifecycle.apply(accountUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(AccountUpdatedEvent accountUpdatedEvent) {
        this.accountType = accountUpdatedEvent.getAccountType();
        this.branchAddress = accountUpdatedEvent.getBranchAddress();
    }

    @CommandHandler
    public void handle(DeleteAccountCommand deleteCommand) {
        AccountDeletedEvent accountDeletedEvent = new AccountDeletedEvent();
        BeanUtils.copyProperties(deleteCommand, accountDeletedEvent);
        AggregateLifecycle.apply(accountDeletedEvent);
    }

    @EventSourcingHandler
    public void on(AccountDeletedEvent accountDeletedEvent) {
        this.activeSw = accountDeletedEvent.isActiveSw();
    }
}
