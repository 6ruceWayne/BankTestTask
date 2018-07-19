package com.java.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.java.entities.Account;
import com.java.entities.Client;
import com.java.forms.AccountForm;
import com.java.forms.TransactionForm;
import com.java.services.IAccountService;
import com.java.services.IClientService;
import com.java.services.ITransactionService;

@Controller
public class AccountController {

	private IAccountService accSer;
	private IClientService cltSer;
	private ITransactionService trnSer;

	@Autowired
	public AccountController(IAccountService accSer, IClientService cltSer, ITransactionService trnSer) {
		this.accSer = accSer;
		this.cltSer = cltSer;
		this.trnSer = trnSer;
	}

	@RequestMapping(value = "/client/{id}")
	public String clientPage(@PathVariable("id") long id, Model model) {
		Client client = cltSer.findById(id);
		model.addAttribute("client", client);
		model.addAttribute("accounts", accSer.findAllByClient(client));
		model.addAttribute("freshAccount", new AccountForm());
		return "clientPage";
	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String createAccount(@Valid @ModelAttribute("freshAccount") AccountForm form, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			Client client = cltSer.findById(form.getClientId());
			model.addAttribute("client", client);
			model.addAttribute("accounts", accSer.findAllByClient(client));
			return "clientPage";
		}
		accSer.addAccount(form);
		return "redirect:/client/" + form.getClientId();
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public String accountPage(@PathVariable("id") long id, Model model) {
		Account account = accSer.findById(id);
		model.addAttribute("client", account.getClient());
		model.addAttribute("account", account);
		model.addAttribute("transactions", trnSer.findAllByAccount(account));
		model.addAttribute("freshTransaction", new TransactionForm());
		return "accountPage";
	}

	@RequestMapping(value = "/addTransaction", method = RequestMethod.POST)
	public String makeTransaction(@Valid @ModelAttribute("freshTransaction") TransactionForm form, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			Account account = accSer.findById(form.getSender());
			model.addAttribute("client", account.getClient());
			model.addAttribute("account", account);
			model.addAttribute("transactions", trnSer.findAllByAccount(account));
			return "accountPage";
		}
		if (form.getAmount() > accSer.findById(form.getSender()).getMoney()) {
			result.rejectValue("amount", "errors.signup.email", "You don't have enough money to this transaction");
			Account account = accSer.findById(form.getSender());
			model.addAttribute("client", account.getClient());
			model.addAttribute("account", account);
			model.addAttribute("transactions", trnSer.findAllByAccount(account));
			return "accountPage";
		}
		trnSer.makeTransaction(form);
		return "redirect:/account/" + form.getSender();
	}
}
