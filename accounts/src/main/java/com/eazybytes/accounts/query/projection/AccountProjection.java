package com.eazybytes.accounts.query.projection;

import com.eazybytes.accounts.command.events.*;
import com.eazybytes.accounts.dto.*;
import com.eazybytes.accounts.entity.*;
import com.eazybytes.accounts.service.*;
import lombok.*;
import org.axonframework.config.*;
import org.axonframework.eventhandling.*;
import org.springframework.beans.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("account-group")
public class AccountProjection {

    private final IAccountsService iAccountsService;

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        Accounts accounts = new Accounts();
        BeanUtils.copyProperties(accountCreatedEvent, accounts);
        iAccountsService.createAccount(accounts);
    }

    @EventHandler
    public void on(AccountUpdatedEvent accountUpdatedEvent) {
        AccountsDto accountsDto = new AccountsDto();
        BeanUtils.copyProperties(accountUpdatedEvent, accountsDto);
        iAccountsService.updateAccount(accountsDto);
    }

    @EventHandler
    public void on(AccountDeletedEvent accountDeletedEvent) {
        iAccountsService.deleteAccount(accountDeletedEvent.getAccountNumber());
    }

}
