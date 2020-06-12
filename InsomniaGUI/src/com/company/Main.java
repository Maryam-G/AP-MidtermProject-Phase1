package com.company;

import com.company.gui.InsomniaFrame;
import com.company.gui.KeyValueItemPanel;

import javax.crypto.spec.PSource;
import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        InsomniaFrame frame = new InsomniaFrame();
        frame.setVisible(true);

    }



}
