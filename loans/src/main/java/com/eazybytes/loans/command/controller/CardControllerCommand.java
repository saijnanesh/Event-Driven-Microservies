package com.eazybytes.loans.command.controller;

import com.eazybytes.loans.command.*;
import com.eazybytes.loans.constants.*;
import com.eazybytes.loans.dto.*;
import com.eazybytes.loans.entity.*;
import com.eazybytes.loans.mapper.*;
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
public class CardControllerCommand {

    private final CommandGateway commandGateway;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam("mobileNumber")
                                                  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {
        Loans newLoan = LoansMapper.createNewLoan(mobileNumber);

        CreateLoanCommand createCardCommand = CreateLoanCommand.builder()
                .loanType(newLoan.getLoanType())
                .loanNumber(newLoan.getLoanNumber())
                .totalLoan(newLoan.getTotalLoan())
                .amountPaid(newLoan.getAmountPaid())
                .outstandingAmount(newLoan.getOutstandingAmount())
                .mobileNumber(mobileNumber)
                .activeSw(newLoan.isActiveSw()).build();

        commandGateway.sendAndWait(createCardCommand);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoansDto loansDto) {

        UpdateLoanCommand updateLoanCommand = UpdateLoanCommand.builder()
                .loanType(loansDto.getLoanType())
                .loanNumber(loansDto.getLoanNumber())
                .totalLoan(loansDto.getTotalLoan())
                .amountPaid(loansDto.getAmountPaid())
                .outstandingAmount(loansDto.getOutstandingAmount())
                .mobileNumber(loansDto.getMobileNumber())
                .activeSw(loansDto.isActiveSw()).build();

        commandGateway.sendAndWait(updateLoanCommand);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
    }

    @PatchMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam("loanNumber")
                                                         Long loanNumber) {

        DeleteLoanCommand deleteLoanCommand = DeleteLoanCommand.builder()
                .loanNumber(loanNumber).activeSw(LoansConstants.IN_ACTIVE_SW).build();

        commandGateway.sendAndWait(deleteLoanCommand);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
    }
}
