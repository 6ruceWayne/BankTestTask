package com.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.entities.Client;
import com.java.services.IClientService;

@Controller
public class ClientController {
	@Autowired
	private IClientService cltSer;
	
	@RequestMapping(value = "/")
	public String startPage(Model model) {
		model.addAttribute("clients", cltSer.getAll());
		model.addAttribute("recruit", new Client());
		return "index";
	}
	
	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public String createClient(@ModelAttribute("client")Client client, Model model) {
		cltSer.save(client);
		return "redirect:/";
	}
	
	
}
