package com.eazybytes.accounts.command.controller;


import com.eazybytes.accounts.command.*;
import com.eazybytes.accounts.constants.*;
import com.eazybytes.accounts.dto.*;
import com.eazybytes.accounts.entity.*;
import com.eazybytes.accounts.util.*;
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
public class AccountCommandController {

    private final CommandGateway commandGateway;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestParam("mobileNumber")
                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {

        Accounts newAccount = AccountMapperUtill.createNewAccount(mobileNumber);
        CreateAccountCommand createAccountCommand = CreateAccountCommand.builder()
                .accountType(newAccount.getAccountType())
                .accountNumber(newAccount.getAccountNumber())
                .mobileNumber(mobileNumber)
                .branchAddress(newAccount.getBranchAddress())
                .activeSw(newAccount.isActiveSw()).build();

        commandGateway.sendAndWait(createAccountCommand);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody AccountsDto accountsDto) {

        UpdateAccountCommand updateCommand = UpdateAccountCommand.builder()
                .accountNumber(accountsDto.getAccountNumber()).mobileNumber(accountsDto.getMobileNumber())
                .accountType(accountsDto.getAccountType()).branchAddress(accountsDto.getBranchAddress())
                .activeSw(AccountsConstants.ACTIVE_SW).build();

        commandGateway.sendAndWait(updateCommand);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

    @PatchMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam("accountNumber")
                                                            Long accountNumber) {

        DeleteAccountCommand deleteAccountCommand = DeleteAccountCommand.builder()
                .accountNumber(accountNumber).activeSw(AccountsConstants.IN_ACTIVE_SW).build();

        commandGateway.sendAndWait(deleteAccountCommand);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

}
