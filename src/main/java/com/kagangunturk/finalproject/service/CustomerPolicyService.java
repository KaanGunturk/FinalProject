package com.kagangunturk.finalproject.service;


import com.kagangunturk.finalproject.model.CustomerPolicy;
import com.kagangunturk.finalproject.repository.CustomerPolicyRepository;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CustomerPolicyService {

    private final String COMMA_DELIMITER = ",";

    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;



    public List<CustomerPolicy> readCustomerPolicyCSV(String filename) throws Exception {

        List<CustomerPolicy> customerPolicies = new ArrayList<CustomerPolicy>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);

        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String firstRow = dataInputStream.readLine();
        while (dataInputStream.available() != 0) {
            String row = dataInputStream.readLine();
            String[] splitedRow = row.split(COMMA_DELIMITER);
            CustomerPolicy customerPolicy = new CustomerPolicy();
            customerPolicy.setCustomerpolicy_id(Integer.parseInt(splitedRow[0]));
            customerPolicy.setCustomer(Integer.parseInt(splitedRow[1]));
            customerPolicy.setPolicy(Integer.parseInt(splitedRow[2]));
            customerPolicies.add(customerPolicy);

        }

        return customerPolicies;
    }

    public List<CustomerPolicy> getAll() {
        return this.customerPolicyRepository.getAllCustomerPolicy();
    }

    public CustomerPolicy get(int id) throws Exception {
        return this.customerPolicyRepository.getCustomerPolicy(id);
    }

    public void initCustomerPolicy() throws Exception {
        List<CustomerPolicy> customerPolicies = this.readCustomerPolicyCSV("customer_policy.csv");
        this.customerPolicyRepository.createCustomerPolicy(customerPolicies);
    }

}
