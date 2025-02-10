package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.*;

public interface ILoansService {

    /**
     *
     * @param loans - Mobile Number of the Customer
     */
    void createLoan(Loans loans);

    /**
     *
     * @param mobileNumber - Input mobile Number
     *  @return Loan Details based on a given mobileNumber
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     *
     * @param loanNumber - Input Loan Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(Long loanNumber);

}
