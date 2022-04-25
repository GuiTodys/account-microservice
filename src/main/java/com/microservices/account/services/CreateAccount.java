package com.microservices.account.services;

import com.microservices.account.domain.Account;
import com.microservices.account.entity.AccountEntity;
import com.microservices.account.exceptions.AccountAlreadyRegisteredException;
import com.microservices.account.exceptions.InvalidUserException;
import com.microservices.account.mappers.AccountEntityMapper;
import com.microservices.account.mappers.AccountRequestMapper;
import com.microservices.account.payloads.AccountRequest;
import com.microservices.account.payloads.UserValidationResponse;
import com.microservices.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateAccount {

    private final AccountRepository accountRepository;
    private final ValidateUserInformation validateUserInformation;
    private AccountEntityMapper accountEntityMapper = AccountEntityMapper.INSTANCE;
    private AccountRequestMapper accountRequestMapper = AccountRequestMapper.INSTANCE;

    public Account execute(AccountRequest accountRequest) {

        if (isAccountRegistered(accountRequest.getAccountNumber(), accountRequest.getAgency()))
            throw AccountAlreadyRegisteredException.builder().build();

        UserValidationResponse userValidationResponse = validateUserInformation.execute(accountRequest.getUserLogIn(), accountRequest.getUserPassword());

        if (userValidationResponse.getIsValid()) {
            Account account = accountRequestMapper.toDomain(accountRequest);
            account.setUserId(userValidationResponse.getUserId());
            AccountEntity accountEntity = accountEntityMapper.toEntity(account);
            return accountEntityMapper.toDomain(accountRepository.save(accountEntity));
        } else throw InvalidUserException.builder().build();
    }

    private Boolean isAccountRegistered(Integer accountNumber, Integer agency) {
        AccountEntity accountEntity = accountRepository.findByAccountNumberAndAgency(accountNumber, agency);
        return Objects.nonNull(accountEntity);
    }
}
