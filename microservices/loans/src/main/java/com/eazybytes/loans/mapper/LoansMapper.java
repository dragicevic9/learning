package com.eazybytes.loans.mapper;

import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loan;

public class LoansMapper {

    public static Loan mapToLoan(LoanDto loanDto, Loan loan) {
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setLoanType(loanDto.getLoanType());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());

        return loan;
    }

    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto) {
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());

        return loanDto;
    }
}
