package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel3 extends JPanel {

    // info panel :
    private JPanel responseInfoPanel;
    private JTextField statusField;
    private JTextField timeField;
    private JTextField sizeField;

    // body panel :
    private JPanel responseBodyPanel;

    private JRadioButton radioButtonRaw;
    private JPanel rawPanel;
    private JTextArea rawText;

    private JRadioButton radioButtonPreview;
    private JPanel previewPanel;

    //header panel :
    private JPanel responseHeaderPanel;
    private ArrayList<HeaderItemPanel> headersList;
    private JButton copyButton;

    public Panel3(){
        this.setLayout(new BorderLayout());
        addInfoPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Calibri", 45, 18));

        // body :
        addResponseBodyPanel();
        //header:
        addResponseHeadersPanel();

        // add panels to tabs of tabbedPane
        tabbedPane.add("Body", new JScrollPane(responseBodyPanel));
        tabbedPane.add("Header", new JScrollPane(responseHeaderPanel));

        this.add(tabbedPane, BorderLayout.CENTER);
    }

    public void addInfoPanel(){
        // top of panel 3:
        statusField = new JTextField(" [ Status ] ");
        statusField.setFont(new Font("Calibri", 45, 17));
        statusField.setEditable(false);
        statusField.setBackground(new Color(207, 214, 210));
        statusField.isOpaque();

        timeField = new JTextField(" [ Time ] ");
        timeField.setFont(new Font("Calibri", 45, 17));
        timeField.setEditable(false);
        timeField.setBackground(new Color(207, 214, 210));
        timeField.isOpaque();

        sizeField = new JTextField(" [ Size ] ");
        sizeField.setFont(new Font("Calibri", 45, 17));
        sizeField.setEditable(false);
        sizeField.setBackground(new Color(207, 214, 210));
        sizeField.isOpaque();

        responseInfoPanel = new JPanel();
        responseInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 6));
        responseInfoPanel.setBackground(Color.WHITE);
        responseInfoPanel.add(statusField);
        responseInfoPanel.add(timeField);
        responseInfoPanel.add(sizeField);
        responseInfoPanel.setPreferredSize(new Dimension(responseInfoPanel.getWidth(), 50));

        this.add(responseInfoPanel, BorderLayout.PAGE_START);
    }

    public void addResponseHeadersPanel(){
        responseHeaderPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(responseHeaderPanel, BoxLayout.Y_AXIS);
        responseHeaderPanel.setLayout(boxLayout);

        headersList = new ArrayList<>();

        JTextField keyField = new JTextField(" key ");
        keyField.setEditable(false);
        JTextField valueField = new JTextField(" value ");
        valueField.setEditable(false);

        keyField.setFont(new Font("Calibri", 45, 15));
        valueField.setFont(new Font("Calibri", 45, 15));

        JPanel keyValuePanel = new JPanel();
        keyValuePanel.setLayout(new GridLayout());
        keyValuePanel.add(keyField);
        keyValuePanel.add(valueField);
        keyValuePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        responseHeaderPanel.add(keyValuePanel);

        //button copy to clipboard :

        copyButton = new JButton("Copy to Clipboard");
        copyButton.setFont(new Font("Calibri", 45, 15));
        copyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        copyButton.addActionListener(new CopyButtonHandler());

        responseHeaderPanel.add(copyButton);
    }

    public void addResponseBodyPanel(){
        setRawPanel();
        setPreviewPanel();

        radioButtonRaw.addActionListener(new RadioButtonHandler());
        radioButtonPreview.addActionListener(new RadioButtonHandler());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonRaw);
        buttonGroup.add(radioButtonPreview);
        radioButtonRaw.setSelected(true);

        JPanel buttonGroupPanel = new JPanel();
        buttonGroupPanel.setLayout(new FlowLayout());
        buttonGroupPanel.add(radioButtonRaw);
        buttonGroupPanel.add(radioButtonPreview);

        responseBodyPanel = new JPanel();
        responseBodyPanel.setLayout(new BorderLayout());
        responseBodyPanel.add(buttonGroupPanel, BorderLayout.NORTH);

        responseBodyPanel.add(rawPanel, BorderLayout.CENTER);
    }

    public void setRawPanel(){
        radioButtonRaw = new JRadioButton("Raw");

        rawPanel = new JPanel();
        rawPanel.setLayout(new BorderLayout());

        rawText = new JTextArea();
        rawText.setFont(new Font("Calibri", 45, 15));
        rawText.setText(" -[ Response Body]-");
        rawText.setEditable(false);

        rawPanel.add(rawText, BorderLayout.CENTER);
    }

    public void setPreviewPanel(){
        radioButtonPreview = new JRadioButton("Preview");
        previewPanel = new JPanel();
    }

    //todo :
    private class CopyButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(copyButton)){

            }
        }
    }

    private class RadioButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // panel 3 :
            if (radioButtonRaw.isSelected()) {
                rawPanel.setVisible(true);
                responseBodyPanel.add(rawPanel, BorderLayout.CENTER);
                previewPanel.setVisible(false);
                Panel3.this.updateUI();
            } else if (radioButtonPreview.isSelected()) {
                previewPanel.setVisible(true);
                responseBodyPanel.add(previewPanel, BorderLayout.CENTER);
                rawPanel.setVisible(false);
                Panel3.this.updateUI();
            }
        }
    }

    private class HeaderItemPanel extends JPanel{

        private JPanel keyValuePanel;
        private JTextField keyField;
        private JTextField valueField;

        public HeaderItemPanel(String key, String value){
            keyField = new JTextField(key);
            keyField.setEditable(false);
            keyField.setFont(new Font("Calibri", 45, 15));

            valueField = new JTextField(value);
            valueField.setEditable(false);
            valueField.setFont(new Font("Calibri", 45, 15));

            keyValuePanel = new JPanel();
            keyValuePanel.setLayout(new GridLayout());
            keyValuePanel.add(keyField);
            keyValuePanel.add(valueField);
            keyValuePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

            responseHeaderPanel.add(keyValuePanel);
        }

    }

    public void setThemeForPanel3(Color newColor){
        responseBodyPanel.setBackground(newColor);
        rawPanel.setBackground(newColor);
        previewPanel.setBackground(newColor);

        responseHeaderPanel.setBackground(newColor);
    }

}
