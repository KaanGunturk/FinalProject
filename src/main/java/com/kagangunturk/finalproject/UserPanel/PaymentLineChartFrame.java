package com.kagangunturk.finalproject.UserPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PaymentLineChartFrame implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton backButton = new JButton();


    public PaymentLineChartFrame() throws IOException {


        panel.setLayout(new FlowLayout());
        BufferedImage myPicture = ImageIO.read(new File("images/Payment Line Chart.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        panel.add(picLabel);
        frame.add(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        backButton = new JButton("Back");
        backButton.setBounds(50, 500, 100, 50);
        backButton.setVisible(true);
        panel.add(backButton);
        backButton.addActionListener(this);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            ChartsFrame chartsFrame = new ChartsFrame();
            frame.dispose();

        }
    }


}
