package com.kagangunturk.finalproject.UserPanel;

import com.kagangunturk.finalproject.tables.CustomerPolicyTable;
import com.kagangunturk.finalproject.tables.CustomerTable;
import com.kagangunturk.finalproject.tables.PaymentsByYearTable;
import com.kagangunturk.finalproject.tables.PolicyTable;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class TablesFrame implements ActionListener {


    JFrame frame = new JFrame();
    JButton backButton = new JButton();

    JButton customerTable = new JButton();
    JButton customerPolicyTable = new JButton();
    JButton paymentsTable = new JButton();
    JButton policyTable = new JButton();


    public TablesFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(customerTable);
        customerTable.setText("Customer Details Table");
        customerTable.setVisible(true);
        customerTable.setLayout(null);
        customerTable.setBounds(50, 100, 300, 30);
        customerTable.addActionListener(this);

        frame.add(customerPolicyTable);
        customerPolicyTable.setText("Policy User Numbers  Table");
        customerPolicyTable.setVisible(true);
        customerPolicyTable.setLayout(null);
        customerPolicyTable.setBounds(50, 200, 300, 30);
        customerPolicyTable.addActionListener(this);

        frame.add(paymentsTable);
        paymentsTable.setText("Payments By Year Table");
        paymentsTable.setVisible(true);
        paymentsTable.setLayout(null);
        paymentsTable.setBounds(50, 300, 300, 30);
        paymentsTable.addActionListener(this);

        frame.add(policyTable);
        policyTable.setText("Policy Type Details Table");
        policyTable.setVisible(true);
        policyTable.setLayout(null);
        policyTable.setBounds(50, 400, 300, 30);
        policyTable.addActionListener(this);


        frame.add(backButton);
        backButton.setText("Back");
        backButton.setVisible(true);
        backButton.setLayout(null);
        backButton.setBounds(75, 500, 100, 50);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            FinancePage financePage = new FinancePage();
            frame.dispose();
        } else if (e.getSource() == customerTable) {
            CustomerTable customerTable = new CustomerTable();
            frame.dispose();
            try {
                customerTable.showCustomerTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == customerPolicyTable) {
            CustomerPolicyTable customerPolicyTable = new CustomerPolicyTable();
            frame.dispose();
            try {
                customerPolicyTable.showCustomerPolicyTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == policyTable) {
            PolicyTable policyTable = new PolicyTable();
            frame.dispose();
            try {
                policyTable.showPolicyTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == paymentsTable){
            PaymentsByYearTable payments = new PaymentsByYearTable();
            frame.dispose();
            payments.showPaymentsByYearTable();
        }
    }
}
