package com.java.services;

import java.util.List;

import com.java.entities.Client;

public interface IClientService {
	public List<Client> getAll();
	
	public void save(Client client);

	public Client findById(long id);

	public void delete(Client client);
	
	public long count();
}
