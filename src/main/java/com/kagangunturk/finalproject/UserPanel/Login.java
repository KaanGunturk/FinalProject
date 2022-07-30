package com.kagangunturk.finalproject.UserPanel;

import javax.swing.*;

public class Login {
    public static void login()  {

        LoginFrame frame = new LoginFrame();
        frame.setTitle("userPanel.Login Form");
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

}
