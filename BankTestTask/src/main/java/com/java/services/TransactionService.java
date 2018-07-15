package com.java.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entities.Account;
import com.java.entities.Transaction;
import com.java.forms.TransactionForm;
import com.java.repositories.IAccountRepository;
import com.java.repositories.ITransactionRepository;

@Service
public class TransactionService implements ITransactionService {

	private ITransactionRepository traRep;
	private IAccountRepository accRep;

	@Autowired
	public TransactionService(ITransactionRepository traRep, IAccountRepository accRep) {
		this.traRep = traRep;
		this.accRep = accRep;
	}

	@Override
	public void create(Transaction transaction) {
		// TODO Auto-generated method stub
		traRep.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) {
		// TODO Auto-generated method stub
		traRep.delete(transaction);
	}

	@Override
	public Transaction findById(long id) {
		// TODO Auto-generated method stub
		return traRep.getOne(id);
	}

	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return traRep.findAll();
	}

	@Override
	public List<Transaction> findAllByAccount(Account account) {
		// TODO Auto-generated method stub
		List<Transaction> allTransactions = traRep.findByReceiver(account);
		allTransactions.addAll(traRep.findByReceiver(account));
		allTransactions = allTransactions.stream().distinct().sorted((e1, e2) -> e2.getData().compareTo(e1.getData()))
				.collect(Collectors.toList());
		return allTransactions;
	}

	@Override
	public void makeTransaction(TransactionForm form) {
		// TODO Auto-generated method stub
		Account sender = accRep.getOne(form.getSender());
		Account reciever = accRep.getOne(form.getReceiver());
		Transaction transaction = new Transaction(form.getAmount(), sender, reciever);

		List<Transaction> send = sender.getSend();
		send.add(transaction);
		sender.setSend(send);

		List<Transaction> received = reciever.getReceived();
		received.add(transaction);
		reciever.setReceived(received);

		traRep.save(transaction);
		accRep.save(sender);
		accRep.save(reciever);
	}

}
