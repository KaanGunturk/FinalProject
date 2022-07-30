package com.kagangunturk.finalproject.UserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinancePage implements ActionListener {

    JFrame frame = new JFrame();

    JButton tablesButton = new JButton();
    JButton chartButton = new JButton();

    JButton exitButton = new JButton();


    public FinancePage() {

        frame.setTitle("Finance Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(tablesButton);
        tablesButton.setSize(400, 400);
        tablesButton.setText("Tables");
        tablesButton.setVisible(true);
        tablesButton.setLayout(null);
        tablesButton.setBounds(50, 200, 300, 30);
        tablesButton.addActionListener(this);

        frame.add(chartButton);
        chartButton.setSize(400, 400);
        chartButton.setText("Charts");
        chartButton.setVisible(true);
        chartButton.setLayout(null);
        chartButton.setBounds(50, 300, 300, 30);
        chartButton.addActionListener(this);

        frame.add(exitButton);
        exitButton.setText("Exit");
        exitButton.setVisible(true);
        exitButton.setLayout(null);
        exitButton.setBounds(75, 500, 100, 50);
        exitButton.addActionListener(this);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tablesButton) {
            TablesFrame tablesFrame = new TablesFrame();
            frame.dispose();
        } else if (e.getSource() == chartButton) {
            ChartsFrame chartsFrame = new ChartsFrame();
            frame.dispose();
        }else  if (e.getSource() == exitButton){
            frame.dispose();
        }
    }
}
