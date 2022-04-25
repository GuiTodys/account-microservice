package com.microservices.account.repository;

import com.microservices.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByAccountNumberAndAgency(Integer accountNumber, Integer agency);
}
