package com.kagangunturk.finalproject.tables;
import com.kagangunturk.finalproject.UserPanel.TablesFrame;
import com.kagangunturk.finalproject.service.PolicyService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Map;

public class CustomerPolicyTable extends JFrame implements ActionListener {


    private JPanel contentPane;
    private JTable table;
    DefaultTableModel CustomerPolicyTable = new DefaultTableModel();
    JButton backButton = new JButton();

    public void showCustomerPolicyTable() throws SQLException {

        AbstractApplicationContext ctx = null;

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PolicyService policyService = ctx.getBean(PolicyService.class);

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
        backButton.addActionListener((ActionListener) this);


        Object[] colums = {"Policy Names", "Policy User Number"};
        Object[] rows = new Object[2];

        CustomerPolicyTable.setColumnCount(0);
        CustomerPolicyTable.setRowCount(0);
        CustomerPolicyTable.setColumnIdentifiers(colums);

        Map<String, BigInteger> data = policyService.getPolicyNameUsersCount();
        for (String str : data.keySet()) {
            rows[0] = str;
            rows[1] = data.get(str);
            CustomerPolicyTable.addRow(rows);
        }
        table.setModel(CustomerPolicyTable);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            TablesFrame tablesFrame = new TablesFrame();
            dispose();
        }
    }


}


