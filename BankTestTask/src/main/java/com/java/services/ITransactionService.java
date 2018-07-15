package com.java.services;

import java.util.List;

import com.java.entities.Account;
import com.java.entities.Transaction;
import com.java.forms.TransactionForm;

public interface ITransactionService {
	public void create(Transaction transaction);
	
	public void delete(Transaction transaction);
	
	public Transaction findById(long id);
	
	public List<Transaction> findAll();
	
	public List<Transaction> findAllByAccount(Account account);
	
	public void makeTransaction(TransactionForm form);
}
