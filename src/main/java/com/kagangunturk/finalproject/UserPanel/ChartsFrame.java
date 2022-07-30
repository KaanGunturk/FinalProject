package com.kagangunturk.finalproject.UserPanel;



import com.kagangunturk.finalproject.charts.PaymentsBarChart;
import com.kagangunturk.finalproject.charts.PaymentsByYearLineChart;
import com.kagangunturk.finalproject.charts.PieCharts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChartsFrame implements ActionListener {

    JButton policyPieChartButton = new JButton();
    JButton paymentsBarChartButton = new JButton();

    JButton paymentsLineChartButton = new JButton();
    JFrame frame = new JFrame();

    JButton backButton = new JButton();

    public ChartsFrame() {


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(policyPieChartButton);
        policyPieChartButton.setText("Policy Pie Chart By Users ");
        policyPieChartButton.setVisible(true);
        policyPieChartButton.setLayout(null);
        policyPieChartButton.setBounds(50, 100, 300, 30);
        policyPieChartButton.addActionListener(this);

        frame.add(paymentsBarChartButton);
        paymentsBarChartButton.setText("Policy Name By Total Amount ");
        paymentsBarChartButton.setVisible(true);
        paymentsBarChartButton.setLayout(null);
        paymentsBarChartButton.setBounds(50, 200, 300, 30);
        paymentsBarChartButton.addActionListener(this);

        frame.add(paymentsLineChartButton);
        paymentsLineChartButton.setText("Payments Line Chart By Year ");
        paymentsLineChartButton.setVisible(true);
        paymentsLineChartButton.setLayout(null);
        paymentsLineChartButton.setBounds(50, 300, 300, 30);
        paymentsLineChartButton.addActionListener(this);


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
        } else if (e.getSource() == paymentsLineChartButton) {
            try {
                PaymentsByYearLineChart paymentsByYearLineChart = new PaymentsByYearLineChart();
                paymentsByYearLineChart.drawPaymentsBarChart();
                PaymentLineChartFrame paymentLineChartFrame = new PaymentLineChartFrame();
                frame.dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == paymentsBarChartButton) {
            try {
                PaymentsBarChart paymentsBarChart = new PaymentsBarChart();
                paymentsBarChart.drawPaymentsBarChart();
                PaymentBarChartFrame paymentBarChartFrame = new PaymentBarChartFrame();
                frame.dispose();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == policyPieChartButton) {
            try {
                PieCharts pieCharts = new PieCharts();
                pieCharts.drawPieChart();
                PolicyPieChartFrame policyPieChartFrame = new PolicyPieChartFrame();
                frame.dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

