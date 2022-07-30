package com.kagangunturk.finalproject.service;


import com.kagangunturk.finalproject.model.Customer;
import com.kagangunturk.finalproject.repository.CustomerRepository;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CustomerService {

    private final String COMMA_DELIMITER = ",";

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> readCustomerCSV(String filename) throws IOException {

        List<Customer> customers = new ArrayList<Customer>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);

        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String firstRow = dataInputStream.readLine();
        while (dataInputStream.available() != 0) {
            String row = dataInputStream.readLine();
            String[] splitedRow = row.split(COMMA_DELIMITER);
            Customer customer = new Customer();
            customer.setCustomer_id(Integer.parseInt(splitedRow[0]));
            customer.setCustomer_firstname(splitedRow[1]);
            customer.setCustomer_lastname(splitedRow[2]);
            customer.setCustomer_email(splitedRow[3]);
            customer.setCustomer_gender(splitedRow[4]);
            customer.setCustomer_address(splitedRow[5]);
            customer.setCustomer_mobileno(splitedRow[6]);
            customer.setCustomer_birthdate(splitedRow[7]);
            customers.add(customer);

        }

        return customers;
    }

    public List<Customer> getAll() {
        return this.customerRepository.getAllCustomer();
    }


    public Customer get(int id) throws Exception {
        return this.customerRepository.getCustomer(id);
    }

    public void initCustomer() throws IOException {
        List<Customer> customers = this.readCustomerCSV("customer.csv");
        this.customerRepository.createCustomer(customers);
    }

}
