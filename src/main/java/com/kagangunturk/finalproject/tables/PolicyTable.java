package com.kagangunturk.finalproject.tables;



import com.kagangunturk.finalproject.UserPanel.TablesFrame;
import com.kagangunturk.finalproject.model.Policy;
import com.kagangunturk.finalproject.service.PolicyService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class PolicyTable extends JFrame implements ActionListener {


    private JPanel contentPane;
    private JTable table;
    DefaultTableModel policyTable = new DefaultTableModel();
    JButton backButton = new JButton();

    public void showPolicyTable() throws SQLException {

        AbstractApplicationContext ctx = null;

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PolicyService policyService = ctx.getBean(PolicyService.class);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        Object[] columns = {"Policy Id", "Policy Name", "Policy Description"};
        Object[] rows = new Object[3];

        policyTable.setColumnCount(0);
        policyTable.setRowCount(0);
        policyTable.setColumnIdentifiers(columns);

        List<Policy> policyList = policyService.getAll();

        for (Policy policy : policyList) {
            rows[0] = policy.getPolicy_id();
            rows[1] = policy.getPolicy_name();
            rows[2] = policy.getPolicy_description();
            policyTable.addRow(rows);
        }
        table.setModel(policyTable);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            TablesFrame tablesFrame = new TablesFrame();
            dispose();
        }
    }
}