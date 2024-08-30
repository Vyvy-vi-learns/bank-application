package com.csf401.BankingSystem.service;

import com.csf401.BankingSystem.model.Customer;
import com.csf401.BankingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

//    public int withdraw(Customer c, long amount) {
//
//    }
//
//    public int deposit(Customer c, long amount) {
//
//    }


    public void deposit(int id, long amount) {
        customerRepository.changeBalance(id, amount);
    }
    public void withdraw(int id, long amount) {
        customerRepository.changeBalance(id, -amount);
    }


    public Customer fetchCustomer(int id) {
        return customerRepository.fetch(id);
    }

    public void updateCustomer(Customer c) {
        customerRepository.update(c);
    }

    public void deleteCustomer(int id) {
        System.out.println(customerRepository.delete(id) + " records deleted");
    }

    public void storeInDB(Customer c) {
        System.out.println(customerRepository.save(c) + " records stored");
    }
}
