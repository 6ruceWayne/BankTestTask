package com.java.services;

import java.util.List;

import com.java.entities.Account;
import com.java.entities.Client;
import com.java.forms.AccountForm;

public interface IAccountService {
	public List<Account> getAll();

	public void save(Account account);

	public Account findById(long id);

	public void delete(Account account);

	public List<Account> findAllByClient(Client client);

	public void addAccount(AccountForm form);
}
