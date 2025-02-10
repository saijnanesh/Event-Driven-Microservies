package com.eazybytes.accounts.query.handler;

import com.eazybytes.accounts.dto.*;
import com.eazybytes.accounts.query.*;
import com.eazybytes.accounts.service.*;
import lombok.*;
import org.axonframework.queryhandling.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class AccountQueryHandler {

    private final IAccountsService iAccountsService;

    @QueryHandler
    public AccountsDto findAccount(FindAccountQuery query) {
        AccountsDto account = iAccountsService.fetchAccount(query.getMobileNumber());
        return account;
    }
}
