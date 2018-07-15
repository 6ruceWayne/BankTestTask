package com.java.start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.java.entities.Account;
import com.java.entities.Client;
import com.java.entities.Transaction;
import com.java.services.IAccountService;
import com.java.services.IClientService;
import com.java.services.ITransactionService;

@SpringBootApplication
@ComponentScan(basePackages = { "com.java.*" })
@EntityScan("com.java.entities")
@EnableJpaRepositories("com.java.repositories")
public class BankTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankTestTaskApplication.class, args);
	}

	// Method to create random users
	@Autowired
	private void init(IClientService cltSer, IAccountService accSer, ITransactionService trnSer) {
		int count = 50 - (int) cltSer.count();
		if (count > 0) {
			Random rnd = new Random();
			List<String> names = Arrays.asList("Alex", "Petr", "Nikolay", "Oleg", "Vasya", "Roman", "Evgeniy", "Igor");
			List<String> streets = Arrays.asList("Pushkina ", "Ekaterininskaya ", "Shevchenko ", "Zhukovskogo ");

			for (int i = 0; i < count; i++) {
				Client client = new Client(names.get(rnd.nextInt(names.size())),
						streets.get(rnd.nextInt(streets.size())) + (1 + rnd.nextInt(15)), 18 + rnd.nextInt(15));
				cltSer.save(client);
			}
			List<Client> clients = cltSer.getAll();
			clients.forEach((client) -> {
				int amountAcc = 1 + rnd.nextInt(5);
				for (int i = 0; i < amountAcc; i++) {
					Account account = new Account(client, 500 + rnd.nextInt(10000));
					List<Account> accounts = accSer.findAllByClient(client).size() == 0 ? new ArrayList<Account>()
							: accSer.findAllByClient(client);
					accounts.add(account);
					client.setAccounts(accounts);
					accSer.save(account);
					cltSer.save(client);
				}
			});
			List<Account> accounts = accSer.getAll();
			accounts.forEach((account) -> {
				int amountTransaction = rnd.nextInt(15);
				while (amountTransaction > 0 && account.getMoney() > 0) {
					Account sender = null;
					boolean find = false;
					while (!find) {
						sender = accounts.get(rnd.nextInt(accounts.size()));
						find = !sender.equals(account);
					}
					boolean profit = rnd.nextBoolean();
					if (profit) {
						int amountMoney = rnd.nextInt((int) sender.getMoney());
						Transaction transaction = new Transaction(amountMoney, sender, account);
						sender.setMoney(sender.getMoney() - amountMoney);
						trnSer.create(transaction);
					} else {
						int amountMoney = rnd.nextInt((int) account.getMoney());
						Transaction transaction = new Transaction(amountMoney, account, sender);
						account.setMoney(account.getMoney() - amountMoney);
						trnSer.create(transaction);
					}
					accSer.save(sender);
					accSer.save(account);
					amountTransaction--;
				}
			});
		}
	}
}
