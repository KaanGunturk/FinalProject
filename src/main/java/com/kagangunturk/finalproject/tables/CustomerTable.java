package com.kagangunturk.finalproject.tables;



import com.kagangunturk.finalproject.UserPanel.TablesFrame;
import com.kagangunturk.finalproject.model.Customer;
import com.kagangunturk.finalproject.service.CustomerService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class CustomerTable extends JFrame implements ActionListener {


    private JPanel contentPane;
    private JTable table;
    DefaultTableModel customerTable = new DefaultTableModel();


    JButton backButton = new JButton();


    public void showCustomerTable() throws SQLException, IOException {

        AbstractApplicationContext ctx = null;

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerService customerService = ctx.getBean(CustomerService.class);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 778, 472);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane.setLayout(null);
        setVisible(true);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(33, 26, 1024, 768);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        backButton = new JButton("Back");
        backButton.setBounds(1200, 500, 100, 50);
        backButton.setVisible(true);
        add(backButton);
        backButton.addActionListener(this);


        Object[] colums = {"Customer Id", "Customer Firstname", "Customer Lastname", "Customer Email", "Customer Gender", "Customer Address", "Customer Mobileno", "Customer Birthdate"};
        Object[] rows = new Object[8];

        customerTable.setColumnCount(0);
        customerTable.setRowCount(0);
        customerTable.setColumnIdentifiers(colums);

        List<Customer> customerList = customerService.getAll();
        for (Customer customer : customerList) {
            rows[0] = customer.getCustomer_id();
            rows[1] = customer.getCustomer_firstname();
            rows[2] = customer.getCustomer_lastname();
            rows[3] = customer.getCustomer_email();
            rows[4] = customer.getCustomer_gender();
            rows[5] = customer.getCustomer_address();
            rows[6] = customer.getCustomer_mobileno();
            rows[7] = customer.getCustomer_birthdate();
            customerTable.addRow(rows);
        }
        table.setModel(customerTable);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            TablesFrame tablesFrame = new TablesFrame();
            dispose();
        }
    }


}
