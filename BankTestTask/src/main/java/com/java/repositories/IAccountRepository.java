package com.java.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.entities.Account;
import com.java.entities.Client;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
	List<Account> findByClient(Client client);
}
