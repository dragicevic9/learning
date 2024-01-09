package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.CardsDto;
import com.eazybytes.accounts.dto.LoanDto;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Log4j2
public class AccountsComponentTests {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private Account account;
    private Customer customer;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CardsFeignClient cardsProxy;
    @MockBean
    private LoansFeignClient loansProxy;


    @BeforeAll
    public void init() {
        account = Account.builder()
                .accountNumber(TestConstants.ACC_NUM)
                .customerId(TestConstants.CUSTOMER_ID)
                .accountType(TestConstants.ACC_TYPE)
                .branchAddress(TestConstants.ADDRESS)
                .communicationSw(TestConstants.COMMUNICATION_SW)
                .build();

        customer = Customer.builder()
                .customerId(TestConstants.CUSTOMER_ID)
                .name(TestConstants.CUST_NAME)
                .email(TestConstants.CUST_EMAIL)
                .mobileNumber(TestConstants.CUST_MOBILE)
                .build();

        customerRepository.save(customer);
        accountsRepository.save(account);
    }


    @Test
    void getCustomerDetailsTest() throws Exception {
        final String link = "/api/fetchCustomerDetails?mobileNumber=" + TestConstants.CUST_MOBILE;

        Mockito.when(this.cardsProxy.fetchCardDetails("123-123", TestConstants.CUST_MOBILE))
                .thenReturn(ResponseEntity.of(Optional.of(
                        new CardsDto(TestConstants.CUST_MOBILE, TestConstants.CARD_NUM, TestConstants.CARD_TYPE,
                                TestConstants.TOTAL_LIMIT, TestConstants.AMOUNT_USED, TestConstants.AVAILABLE_AMOUNT))));

        Mockito.when(this.loansProxy.fetchLoanDetails("123-123", TestConstants.CUST_MOBILE))
                .thenReturn(ResponseEntity.of(Optional.of(
                        new LoanDto(TestConstants.CUST_MOBILE, TestConstants.LOAN_NUM, TestConstants.LOAN_TYPE,
                                TestConstants.TOTAL_LOAN, TestConstants.AMOUNT_PAID, TestConstants.OUTSTANDING_AMOUNT)
                )));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get(link)
                        .header("eazybank-correlation-id", "123-123")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(TestConstants.CUST_NAME)))
                .andExpect(jsonPath("$.accountsDto.accountNumber", is((int) TestConstants.ACC_NUM)))
                .andExpect(jsonPath("$.loansDto.loanNumber", is(TestConstants.LOAN_NUM)))
                .andExpect(jsonPath("$.cardsDto.cardNumber", is(TestConstants.CARD_NUM)));
    }
}
