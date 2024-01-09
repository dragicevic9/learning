package com.learning.graphql.controller;

import com.learning.graphql.model.BankAccount;
import com.learning.graphql.model.Client;
import com.learning.graphql.model.Currency;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BankAccountController {
    
    @QueryMapping
    public BankAccount bankAccount(@Argument String id) {
        Client client = Client.builder()
                .firstName("First")
                .lastName("Client")
                .build();

        Client secClient = Client.builder()
                .firstName("Second")
                .lastName("Client")
                .client(client)
                .build();

        client.setClient(secClient);

        return BankAccount.builder()
                .id("bank-1")
                .client(client)
                .currency(Currency.USD)
                .build();
    }
}
