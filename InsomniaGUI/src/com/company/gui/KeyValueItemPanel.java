package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class KeyValueItemPanel extends JPanel{

    private JTextField keyField;
    private JTextField valueField;
    private JButton deleteButton;
    private JCheckBox checkBox;

    public KeyValueItemPanel(){
        keyField = new JTextField("-[key]-");
        valueField = new JTextField("-[value]-");
        deleteButton = new JButton("\u2716");
        checkBox = new JCheckBox();

        setPanel();
    }

    public void setPanel(){

        keyField.setFont(new Font("Calibri", 45, 15));
        valueField.setFont(new Font("Calibri", 45, 15));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(deleteButton)){
                    KeyValueItemPanel.this.setVisible(false);
                }


            }
        });

        JPanel keyValuePanel = new JPanel();
        keyValuePanel.setLayout(new GridLayout());
        keyValuePanel.add(keyField);
        keyValuePanel.add(valueField);

        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        this.add(checkBox, BorderLayout.WEST);
        this.add(keyValuePanel, BorderLayout.CENTER);
        this.add(deleteButton, BorderLayout.EAST);

        this.setVisible(true);
    }

    public JTextField getKeyField() {
        return keyField;
    }

    public JTextField getValueField() {
        return valueField;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
