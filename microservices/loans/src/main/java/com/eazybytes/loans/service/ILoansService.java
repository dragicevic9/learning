package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoanDto;

public interface ILoansService {
    void createLoan(String mobileNumber);

    LoanDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoanDto loanDto);

    boolean deleteLoan(String mobileNumber);
}
