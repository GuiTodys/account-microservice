package com.microservices.account.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotNull
    private Integer accountNumber;
    @NotNull
    private Integer agency;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private String userLogIn;
    @NotNull
    private String userPassword;
}
