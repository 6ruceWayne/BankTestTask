package com.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entities.Account;
import com.java.entities.Client;
import com.java.forms.AccountForm;
import com.java.repositories.IAccountRepository;
import com.java.repositories.IClientReposiroty;

@Service
public class AccountService implements IAccountService {

	private IAccountRepository accRep;
	private IClientReposiroty cltRep;

	@Autowired
	public AccountService(IAccountRepository accRep, IClientReposiroty cltRep) {
		this.accRep = accRep;
		this.cltRep = cltRep;
	}

	@Override
	public void save(Account account) {
		// TODO Auto-generated method stub
		accRep.save(account);
	}

	@Override
	public Account findById(long id) {
		// TODO Auto-generated method stub
		return accRep.getOne(id);
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		accRep.delete(account);
	}

	@Override
	public List<Account> findAllByClient(Client client) {
		// TODO Auto-generated method stub
		return accRep.findByClient(client);
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return accRep.findAll();
	}

	@Override
	public void addAccount(AccountForm form) {
		// TODO Auto-generated method stub
		Client client = cltRep.getOne(form.getClientId());
		Account substitution = new Account(client, form.getMoney());
		List<Account> accounts = client.getAccounts();
		accounts.add(substitution);
		client.setAccounts(accounts);
		accRep.save(substitution);
		cltRep.save(client);
	}

}
