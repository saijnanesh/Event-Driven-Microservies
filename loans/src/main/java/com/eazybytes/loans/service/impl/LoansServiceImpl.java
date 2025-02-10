package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param loans - Mobile Number of the Customer
     */
    @Override
    public void createLoan(Loans loans) {
        Optional<Loans> optionalLoan = loansRepository.findByMobileNumberAndActiveSw(loans.getMobileNumber(),
                LoansConstants.ACTIVE_SW);
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + loans.getMobileNumber());
        }
        loansRepository.save(loans);
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumberAndActiveSw(mobileNumber, LoansConstants.ACTIVE_SW)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
                );
        return LoansMapper.mapToLoansDto(loan, new LoansDto());
    }

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loan = loansRepository.findByMobileNumberAndActiveSw(loansDto.getMobileNumber(),
                LoansConstants.ACTIVE_SW).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber().toString()));
        LoansMapper.mapToLoans(loansDto, loan);
        loansRepository.save(loan);
        return true;
    }

    /**
     * @param loanNumber - Input Loan Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(Long loanNumber) {
        Loans loan = loansRepository.findById(loanNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "loanNumber", loanNumber.toString())
                );
        loan.setActiveSw(LoansConstants.IN_ACTIVE_SW);
        loansRepository.save(loan);
        return true;
    }


}
