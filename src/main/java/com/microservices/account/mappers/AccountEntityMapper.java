package com.microservices.account.mappers;

import com.microservices.account.domain.Account;
import com.microservices.account.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountEntityMapper {
    AccountEntityMapper INSTANCE = Mappers.getMapper(AccountEntityMapper.class);

    Account toDomain(AccountEntity AccountEntity);

    AccountEntity toEntity(Account Account);
}
