package com.learning.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    private String firstName;
    private String lastName;
    private String[] middleNames;
    private Client client;
}
