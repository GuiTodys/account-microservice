package com.microservices.account.services;

import com.microservices.account.domain.Account;
import com.microservices.account.entity.AccountEntity;
import com.microservices.account.exceptions.AccountNotFoundException;
import com.microservices.account.mappers.AccountEntityMapper;
import com.microservices.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAccount {

    private final AccountRepository accountRepository;
    private AccountEntityMapper accountEntityMapper = AccountEntityMapper.INSTANCE;

    public Account byId(Long id) {
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(() -> AccountNotFoundException.builder().build());
        return accountEntityMapper.toDomain(accountEntity);
    }

    public List<Account> getAll() {
        List<AccountEntity> accountsEntities = accountRepository.findAll();
        return accountsEntities.stream().map(account -> accountEntityMapper.toDomain(account)).collect(Collectors.toList());
    }
}
