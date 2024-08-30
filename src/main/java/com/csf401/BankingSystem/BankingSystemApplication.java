package com.csf401.BankingSystem;

import com.csf401.BankingSystem.model.Customer;
import com.csf401.BankingSystem.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BankingSystemApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BankingSystemApplication.class, args);
		Customer customer = ctx.getBean(Customer.class);
		customer.setId(100);
		customer.setName("John Doe");
		customer.setBalance(0);

		CustomerService customerService = ctx.getBean(CustomerService.class);
		customerService.storeInDB(customer);

		customer.setName("Mr. Blue");
		customerService.deposit(100, 1000);
		customerService.updateCustomer(customerService.fetchCustomer(100));
		customerService.deleteCustomer(100);
	}

}
