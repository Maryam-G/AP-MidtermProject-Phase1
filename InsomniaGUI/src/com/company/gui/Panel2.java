package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class Panel2 extends JPanel {

    // URL panel :
    private JButton sendButton;
    private JComboBox comboBoxForMethod;
    private JTextField urlAddress;

    // body panel :
    private JPanel requestBodyPanel;

    private JPanel noBodyPanel;
    private JRadioButton radioButtonNoBody;

    private JPanel formDataPanel;
    private JRadioButton radioButtonFormData;
    private ArrayList<KeyValueItemPanel> bodyList;

    // headers panel :
    private JPanel requestHeaderPanel;
    private ArrayList<KeyValueItemPanel> headersList;

    public Panel2(){
        this.setLayout(new BorderLayout());

        // URL :
        addUrlPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Calibri", 45, 18));

        // body :
        addBodyPanel();
        //header:
        addHeadersPanel();

        // add panels to tabs of tabbedPane
        tabbedPane.add("Body", new JScrollPane(requestBodyPanel));
        tabbedPane.add("Headers", new JScrollPane(requestHeaderPanel));

        this.add(tabbedPane, BorderLayout.CENTER);

    }

    public void addUrlPanel(){
        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new BorderLayout());
        urlPanel.setPreferredSize(new Dimension(urlPanel.getWidth(), 50));

        comboBoxForMethod = new JComboBox();
        comboBoxForMethod.setFont(new Font("Calibri", 45, 15));
        comboBoxForMethod.addItem("GET");
        comboBoxForMethod.addItem("POST");
        comboBoxForMethod.addItem("PUT");
        comboBoxForMethod.addItem("DELETE");

        urlAddress = new JTextField();
        urlAddress.setText("Enter URL ...");
        urlAddress.setFont(new Font("Calibri", 45, 15));

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Calibri", 45, 15));
        sendButton.setHorizontalAlignment(JButton.CENTER);

        urlPanel.add(comboBoxForMethod, BorderLayout.WEST);
        urlPanel.add(urlAddress, BorderLayout.CENTER);
        urlPanel.add(sendButton, BorderLayout.EAST);
        urlPanel.setVisible(true);

        this.add(urlPanel, BorderLayout.PAGE_START);
    }

    public void addHeadersPanel(){
        requestHeaderPanel = new JPanel();

        KeyValueItemPanel firstHeader = new KeyValueItemPanel();

        BoxLayout boxLayout = new BoxLayout(requestHeaderPanel, BoxLayout.Y_AXIS);
        requestHeaderPanel.setLayout(boxLayout);
        requestHeaderPanel.add(firstHeader);

        headersList = new ArrayList<>();
        headersList.add(firstHeader);

        firstHeader.getKeyField().addFocusListener(new FocusHandler());
        firstHeader.getValueField().addFocusListener(new FocusHandler());
    }

    public void addBodyPanel(){
        requestBodyPanel = new JPanel();

        // No-Body :
        noBodyPanel = new JPanel();
        radioButtonNoBody = new JRadioButton("No-Body");
        // Form-Data :
        setFormDataPanel();

        radioButtonNoBody.setSelected(true);

        radioButtonNoBody.addActionListener(new RadioButtonHandler());
        radioButtonFormData.addActionListener(new RadioButtonHandler());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonNoBody);
        buttonGroup.add(radioButtonFormData);

        JPanel buttonGroupPanel = new JPanel();
        buttonGroupPanel.setLayout(new FlowLayout());
        buttonGroupPanel.add(radioButtonNoBody);
        buttonGroupPanel.add(radioButtonFormData);

        requestBodyPanel.setLayout(new BorderLayout());
        requestBodyPanel.add(new JScrollPane(buttonGroupPanel), BorderLayout.NORTH);
    }

    public void setFormDataPanel() {

        KeyValueItemPanel firstFormDataItem = new KeyValueItemPanel();

        formDataPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(formDataPanel, BoxLayout.Y_AXIS);
        formDataPanel.setLayout(boxLayout);
        formDataPanel.add(firstFormDataItem);

        bodyList = new ArrayList<>();
        bodyList.add(firstFormDataItem);

        firstFormDataItem.getKeyField().addFocusListener(new FocusHandler());
        firstFormDataItem.getValueField().addFocusListener(new FocusHandler());

        radioButtonFormData = new JRadioButton("Form-Data");
    }

    private class FocusHandler implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if(e.getSource().equals(requestHeaderPanel)){
                System.out.println("l");
            }

            KeyValueItemPanel lastHeaderItem = new KeyValueItemPanel();
            lastHeaderItem = headersList.get(headersList.size()-1);
            KeyValueItemPanel lastFormDataItemPanel = new KeyValueItemPanel();
            lastFormDataItemPanel = bodyList.get(bodyList.size()-1);

            // focus on request headers panel -> Headers
            if (e.getSource().equals(lastHeaderItem.getKeyField()) || e.getSource().equals(lastHeaderItem.getValueField())) {
                KeyValueItemPanel newHeaderItem = new KeyValueItemPanel();
                newHeaderItem.getKeyField().addFocusListener(this);
                newHeaderItem.getValueField().addFocusListener(this);
                newHeaderItem.setVisible(true);

                requestHeaderPanel.add(newHeaderItem);
                headersList.add(newHeaderItem);

                Panel2.this.updateUI();
            }

            //focus on request body panel -> Form-Data
            else if (e.getSource().equals(lastFormDataItemPanel.getKeyField()) || e.getSource().equals(lastFormDataItemPanel.getValueField())) {
                KeyValueItemPanel newFormDataItem = new KeyValueItemPanel();
                newFormDataItem.getKeyField().addFocusListener(this);
                newFormDataItem.getValueField().addFocusListener(this);
                newFormDataItem.setVisible(true);

                formDataPanel.add(newFormDataItem);
                bodyList.add(newFormDataItem);

                Panel2.this.updateUI();
            }

        }

        @Override
        public void focusLost(FocusEvent e) {
        }
    }

    private class RadioButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (radioButtonNoBody.isSelected()) {
                noBodyPanel.setVisible(true);
                requestBodyPanel.add(noBodyPanel, BorderLayout.CENTER);
                formDataPanel.setVisible(false);
                Panel2.this.updateUI();
            } else if (radioButtonFormData.isSelected()) {
                formDataPanel.setVisible(true);
                requestBodyPanel.add(formDataPanel, BorderLayout.CENTER);
                noBodyPanel.setVisible(false);
                Panel2.this.updateUI();
            }
        }
    }

}
