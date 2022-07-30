package com.kagangunturk.finalproject.tables;



import com.kagangunturk.finalproject.UserPanel.TablesFrame;
import com.kagangunturk.finalproject.service.PaymentService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class PaymentsByYearTable extends JFrame implements ActionListener {


    private JPanel contentPane;
    private JTable table;
    JButton backButton = new JButton();
    DefaultTableModel paymentsByYearTable = new DefaultTableModel();


    public void showPaymentsByYearTable() {

        AbstractApplicationContext ctx = null;

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PaymentService paymentService = ctx.getBean(PaymentService.class);

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

        Object[] columns = {"Payment Year", "Payment Amount Total"};
        Object[] rows = new Object[2];

        paymentsByYearTable.setColumnCount(0);
        paymentsByYearTable.setRowCount(0);
        paymentsByYearTable.setColumnIdentifiers(columns);

        Map<String, Double> data = paymentService.getPaymentsDetail();
        for (String str : data.keySet()) {
            rows[0] = str;
            rows[1] = data.get(str);
            paymentsByYearTable.addRow(rows);

        }
        table.setModel(paymentsByYearTable);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            TablesFrame tablesFrame = new TablesFrame();
            dispose();
        }
    }
}
