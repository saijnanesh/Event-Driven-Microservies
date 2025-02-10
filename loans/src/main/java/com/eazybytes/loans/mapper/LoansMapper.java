package com.eazybytes.loans.mapper;

import com.eazybytes.loans.constants.*;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;

import java.util.*;

public class LoansMapper {

    public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        loansDto.setActiveSw(loans.isActiveSw());
        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setLoanType(loansDto.getLoanType());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    public static Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 1000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(randomLoanNumber);
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setActiveSw(LoansConstants.ACTIVE_SW);
        return newLoan;
    }

}
