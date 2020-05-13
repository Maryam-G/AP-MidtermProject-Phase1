package com.company;

import javax.swing.*;

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

        InsomniaView insomnia = new InsomniaView();
        insomnia.showGUI();

//        String s = "\uD83D\uDDD1";
//        System.out.println(s.);

    }
}
