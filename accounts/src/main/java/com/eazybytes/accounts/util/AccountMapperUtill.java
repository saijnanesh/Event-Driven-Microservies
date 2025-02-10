package com.eazybytes.accounts.util;

import com.eazybytes.accounts.constants.*;
import com.eazybytes.accounts.entity.*;

import java.util.*;

public class AccountMapperUtill {

    /**
     * @param mobileNumber - String
     * @return the new account details
     */
    public static Accounts createNewAccount(String mobileNumber) {
        Accounts newAccount = new Accounts();
        newAccount.setMobileNumber(mobileNumber);
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setActiveSw(AccountsConstants.ACTIVE_SW);
        return newAccount;
    }
}
