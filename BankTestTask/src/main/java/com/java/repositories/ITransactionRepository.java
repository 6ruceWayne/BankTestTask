package com.java.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.entities.Account;
import com.java.entities.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
	public List<Transaction> findTransactionBySender(Account account);

	public List<Transaction> findTransactionByReceiver(Account account);
}
