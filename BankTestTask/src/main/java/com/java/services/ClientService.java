package com.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.entities.Client;
import com.java.repositories.IClientReposiroty;

@Service
public class ClientService implements IClientService {
	@Autowired
	private IClientReposiroty cltRep;

	@Override
	public void save(Client client) {
		// TODO Auto-generated method stub
		cltRep.save(client);
	}

	@Override
	public Client findById(long id) {
		// TODO Auto-generated method stub
		return cltRep.getOne(id);
	}

	@Override
	public void delete(Client client) {
		// TODO Auto-generated method stub
		cltRep.delete(client);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return cltRep.count();
	}

	@Override
	@Transactional 
	public List<Client> getAll() {
		// TODO Auto-generated method stub
		return cltRep.findAll();
	}

}
