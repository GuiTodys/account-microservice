package com.microservices.account.controllers;

import com.microservices.account.mappers.AccountRequestMapper;
import com.microservices.account.mappers.AccountResponseMapper;
import com.microservices.account.payloads.AccountRequest;
import com.microservices.account.payloads.AccountResponse;
import com.microservices.account.services.CreateAccount;
import com.microservices.account.services.GetAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final CreateAccount createAccount;
    private final GetAccount getAccount;
    private AccountResponseMapper accountResponseMapper = AccountResponseMapper.INSTANCE;
    private AccountRequestMapper accountRequestMapper = AccountRequestMapper.INSTANCE;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(
            @RequestBody AccountRequest accountRequest) {
        return accountResponseMapper.toResponse(createAccount.execute(accountRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse getAccountById(
            @PathVariable Long id
    ) {
        return accountResponseMapper.toResponse(getAccount.byId(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountResponse> getAllAccounts() {
        return getAccount.getAll().stream().map(user -> accountResponseMapper.toResponse(user)).collect(Collectors.toList());
    }
}
