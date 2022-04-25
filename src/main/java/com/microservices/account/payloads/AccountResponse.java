package com.microservices.account.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Long id;
    private Integer accountNumber;
    private Integer agency;
    private BigDecimal balance;
    private Long userId;
}
