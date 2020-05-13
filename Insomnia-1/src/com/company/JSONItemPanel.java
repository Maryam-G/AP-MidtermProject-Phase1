package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JSONItemPanel extends JPanel{

    private JTextField textField;
    private JButton deleteButton;

    public JSONItemPanel(int lineNumber){

        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        textField = new JTextField();
        textField.setFont(new Font("Calibri", 45, 15));
        textField.setText(lineNumber + "  ...");

        this.add(textField, BorderLayout.CENTER);
    }

    public JTextField getTextField() {
        return textField;
    }

}
