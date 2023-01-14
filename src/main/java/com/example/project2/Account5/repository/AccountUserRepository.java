package com.example.project2.Account5.repository;

import com.example.project2.Account5.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {
}
