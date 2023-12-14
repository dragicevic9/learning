package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loan;
import com.eazybytes.loans.exceptions.LoanAlreadyExistsException;
import com.eazybytes.loans.exceptions.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private final LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loan> optionalLoan = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobile number: " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan loan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loan.setLoanNumber(Long.toString(randomLoanNumber));
        loan.setMobileNumber(mobileNumber);
        loan.setLoanType(LoansConstants.HOME_LOAN);
        loan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loan;
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        return LoansMapper.mapToLoanDto(loan, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan loan = loansRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loanNumber", loanDto.getLoanNumber())
        );

        LoansMapper.mapToLoan(loanDto, loan);
        loansRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        loansRepository.delete(loan);
        return true;
    }
}
