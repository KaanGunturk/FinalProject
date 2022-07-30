package com.kagangunturk.finalproject.service;

import com.kagangunturk.finalproject.model.Policy;
import com.kagangunturk.finalproject.repository.PolicyRepository;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Data
public class PolicyService {

    private final String COMMA_DELIMITER = ",";
    @Autowired
    private PolicyRepository policyRepository;
    public List<Policy> readPolicyCSV(String filename) throws IOException {

        List<Policy> policies = new ArrayList<Policy>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);

        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String firstRow = dataInputStream.readLine();
        while (dataInputStream.available() != 0) {
            String row = dataInputStream.readLine();
            String[] splitedRow = row.split(COMMA_DELIMITER);
            Policy policy = new Policy();
            policy.setPolicy_id(Integer.parseInt(splitedRow[0]));
            policy.setPolicy_name(splitedRow[1]);
            policy.setPolicy_description(splitedRow[2]);
            policies.add(policy);

        }

        return policies;
    }

    public List<Policy> getAll(){
        return this.policyRepository.getAllCPolicy();
    }


    public Policy get(int id) throws Exception {
        return this.policyRepository.getPolicy(id);
    }

    public void initPolicy() throws IOException {
        List<Policy> policies = this.readPolicyCSV("policy.csv");
        this.policyRepository.createPolicy(policies);
    }
    public Map<String, Double> getPolicysDetail(){
        return this.policyRepository.getPolicyNameWithTotalAmount();
    }
    public Map<String, BigInteger> getPolicyNameUsersCount(){
        return this.policyRepository.getPolicyNameByTotalUser();
    }
}

