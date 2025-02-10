package com.eazybytes.loans.query.handler;

import com.eazybytes.loans.dto.*;
import com.eazybytes.loans.query.*;
import com.eazybytes.loans.service.*;
import lombok.*;
import org.axonframework.queryhandling.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class LoanQueryHandler {

    private final ILoansService iLoansService;

    @QueryHandler
    public LoansDto findLoan(FindLoanQuery query) {
        LoansDto loan = iLoansService.fetchLoan(query.getMobileNumber());
        return loan;
    }
}
