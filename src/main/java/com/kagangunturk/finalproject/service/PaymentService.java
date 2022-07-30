package com.kagangunturk.finalproject.service;

import com.kagangunturk.finalproject.model.Payment;
import com.kagangunturk.finalproject.repository.PaymentsRepository;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Data
public class PaymentService {

    private final String COMMA_DELIMITER = ",";
    @Autowired
    private PaymentsRepository paymentsRepository;


    public List<Payment> readPaymentCSV(String filename) throws Exception {

        List<Payment> payments = new ArrayList<Payment>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);

        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String firstRow = dataInputStream.readLine();
        while (dataInputStream.available() != 0) {
            String row = dataInputStream.readLine();
            String[] splitedRow = row.split(COMMA_DELIMITER);
            Payment payment = new Payment();
            payment.setPayments_id(Integer.parseInt(splitedRow[0]));
            payment.setCustomerpolicy(Integer.parseInt(splitedRow[1]));
            payment.setPayments_amount(Double.parseDouble(splitedRow[2]));
            payment.setPayments_date((splitedRow[3]));

            payments.add(payment);

        }

        return payments;
    }

    public List<Payment> getAll() {
        return this.paymentsRepository.getAllPayment();
    }


    public Payment get(int id) throws Exception {
        return this.paymentsRepository.getPayment(id);
    }

    public void initPayment() throws Exception {
        List<Payment> payments = this.readPaymentCSV("payments.csv");
        this.paymentsRepository.createPayment(payments);
    }

    public Map<String, Double> getPaymentsDetail() {
        return this.paymentsRepository.getPaymentsWithTotalAmountByYear();
    }


}
