package com.microservices.account.mappers;

import com.microservices.account.domain.Account;
import com.microservices.account.payloads.AccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountRequestMapper {
    AccountRequestMapper INSTANCE = Mappers.getMapper(AccountRequestMapper.class);

    Account toDomain(AccountRequest AccountRequest);

    AccountRequest toRequest(Account Account);
    
}
