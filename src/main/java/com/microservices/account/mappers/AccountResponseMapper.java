package com.microservices.account.mappers;

import com.microservices.account.domain.Account;
import com.microservices.account.payloads.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountResponseMapper {
    AccountResponseMapper INSTANCE = Mappers.getMapper(AccountResponseMapper.class);

    Account toDomain(AccountResponse AccountResponse);

    AccountResponse toResponse(Account user);
}
