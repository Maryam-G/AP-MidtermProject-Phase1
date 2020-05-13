package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameValueItemPanel extends JPanel {

    private JTextField nameField;
    private JTextField valueField;
    private JButton deleteButton;
    private JCheckBox checkBox;

    public NameValueItemPanel(){
        nameField = new JTextField("-[name]-");
        valueField = new JTextField("-[value]-");
        deleteButton = new JButton("\u2716");
        checkBox = new JCheckBox();

        setPanel();
    }

    public void setPanel(){

        nameField.setFont(new Font("Calibri", 45, 15));
        valueField.setFont(new Font("Calibri", 45, 15));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(deleteButton)){
                    NameValueItemPanel.this.setVisible(false);
                }
            }
        });

        JPanel keyValuePanel = new JPanel();
        keyValuePanel.setLayout(new GridLayout());
        keyValuePanel.add(nameField);
        keyValuePanel.add(valueField);

        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        this.add(checkBox, BorderLayout.WEST);
        this.add(keyValuePanel, BorderLayout.CENTER);
        this.add(deleteButton, BorderLayout.EAST);

        this.setVisible(true);

    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getValueField() {
        return valueField;
    }
}
