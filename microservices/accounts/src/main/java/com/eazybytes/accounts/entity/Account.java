package com.eazybytes.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseEntity {

    @Id
    private Long accountNumber;

    private Long customerId;

    private String accountType;

    private String branchAddress;

    private Boolean communicationSw;

}
