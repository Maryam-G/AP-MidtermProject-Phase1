package com.company.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {

    private JMenu application;
    private JMenu view;
    private JMenu help;

    //application menu:
    private JMenuItem options;
    private JMenuItem exit;

    private JFrame optionsFrame;
    private JCheckBox enable;
    private JCheckBox disable;
    private JCheckBox exitFromApp;
    private JCheckBox hideOnSystemTray;
    private JCheckBox lightTheme;
    private JCheckBox darkTheme;

    //view menu:
    private JMenuItem toggleFullScreen;
    private JMenuItem toggleSidebar;

    private boolean visibilityOfPanel1;
    private boolean isFullScreen;

    //help menu:
    private JMenuItem helpItem;
    private JMenuItem about;

    private JTextArea helpItemText;
    private JFrame helpItemFrame;
    private JTextArea aboutText;
    private JFrame aboutFrame;

    public Menu(){
        initApplicationMenu();
        initViewMenu();
        initHelpMenu();

        this.add(application);
        this.add(view);
        this.add(help);
    }

    public void initApplicationMenu(){
        application = new JMenu("Application");
        application.setFont(new Font("Calibri", 14, 15));
        application.setMnemonic(KeyEvent.VK_A);

        options = new JMenuItem("Options");
        options.setFont(new Font("Calibri", 14, 15));
        options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
//        options.addActionListener(new MenuHandler());
        setOptionsFrame();

        exit = new JMenuItem("Exit");
        exit.setFont(new Font("Calibri", 14, 15));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
//        exit.addActionListener(new MenuHandler());

        application.add(options);
        application.add(exit);
    }

    public void setOptionsFrame(){
        optionsFrame = new JFrame("Options");
        optionsFrame.setSize(new Dimension(300, 300));
        optionsFrame.setLayout(new GridLayout(3, 1, 1, 1));
        optionsFrame.setLocation(800, 400);
        optionsFrame.setResizable(false);

        enable = new JCheckBox("Enable");
//        enable.addItemListener(new CheckBoxHandler());
        disable = new JCheckBox("Disable");
//        disable.addItemListener(new CheckBoxHandler());
        lightTheme = new JCheckBox("Light Theme");
//        lightTheme.addItemListener(new CheckBoxHandler());
        darkTheme = new JCheckBox("Dark Theme", true);
//        darkTheme.addItemListener(new CheckBoxHandler());
        exitFromApp = new JCheckBox("Exit From App", true);
//        exitFromApp.addItemListener(new CheckBoxHandler());
        hideOnSystemTray = new JCheckBox("Hide On System Tray");
//        hideOnSystemTray.addItemListener(new CheckBoxHandler());

        JPanel followRedirectPanel = new JPanel();
        followRedirectPanel.setLayout(new GridLayout(2, 1, 1, 1));
        followRedirectPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY
                , 1, true), " [ Follow Redirect ]", TitledBorder.LEFT, TitledBorder.TOP));
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(enable);
        buttonGroup1.add(disable);

        followRedirectPanel.add(enable);
        followRedirectPanel.add(disable);

        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridLayout(2, 1, 1, 1));
        exitPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY
                , 1, true), " [ Exit ]", TitledBorder.LEFT, TitledBorder.TOP));
        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(exitFromApp);
        buttonGroup2.add(hideOnSystemTray);

        exitPanel.add(exitFromApp);
        exitPanel.add(hideOnSystemTray);

        JPanel themePanel = new JPanel();
        themePanel.setLayout(new GridLayout(2, 1, 1, 1));
        themePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY
                , 1, true), " [ Theme ]", TitledBorder.LEFT, TitledBorder.TOP));
        ButtonGroup buttonGroup3 = new ButtonGroup();
        buttonGroup3.add(darkTheme);
        buttonGroup3.add(lightTheme);

        themePanel.add(lightTheme);
        themePanel.add(darkTheme);

        optionsFrame.add(followRedirectPanel);
        optionsFrame.add(exitPanel);
        optionsFrame.add(themePanel);

    }

    public void initViewMenu(){
        view = new JMenu("View");
        view.setFont(new Font("Calibri", 14, 15));
        view.setMnemonic(KeyEvent.VK_V);

        toggleFullScreen = new JMenuItem("Toggle Full Screen");
        toggleFullScreen.setFont(new Font("Calibri", 14, 15));
        toggleFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
//        toggleFullScreen.addActionListener(new MenuHandler());

        toggleSidebar = new JMenuItem("Toggle Sidebar");
        toggleSidebar.setFont(new Font("Calibri", 14, 15));
        toggleSidebar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
//        toggleSidebar.addActionListener(new MenuHandler());

        visibilityOfPanel1 = true;
        isFullScreen = false;

        view.add(toggleFullScreen);
        view.add(toggleSidebar);
    }

    public void initHelpMenu(){
        help = new JMenu("Help");
        help.setFont(new Font("Calibri", 14, 15));
        help.setMnemonic(KeyEvent.VK_H);

        helpItem = new JMenuItem("Help");
        helpItem.setFont(new Font("Calibri", 14, 15));
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
//        helpItem.addActionListener(new MenuHandler());

        about = new JMenuItem("About");
        about.setFont(new Font("Calibri", 14, 15));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
//        about.addActionListener(new MenuHandler());

        setHelpFrame();

        help.add(helpItem);
        help.add(about);
    }

    public void setHelpFrame(){
        aboutText = new JTextArea();
        aboutText.setFont(new Font("Calibri", 14, 14));
        aboutText.setText("Hi, I'm Maryam Goli!\n\nMy student number : 9831054\nMy email : goli.mary.m@gmail.com\n\nI hope you enjoy the app :)\n\n~Maryam ");
        aboutText.setEditable(false);
        aboutFrame = new JFrame("About");
        aboutFrame.setSize(300, 300);
        aboutFrame.setLocation(800, 400);
        aboutFrame.add(aboutText);

        helpItemText = new JTextArea();
        helpItemText.setFont(new Font("Calibri", 14, 14));
        helpItemText.setText(" ... :)");
        helpItemText.setEditable(false);
        helpItemFrame = new JFrame("Help");
        helpItemFrame.setSize(300, 300);
        helpItemFrame.setLocation(800, 400);
        helpItemFrame.add(helpItemText);
    }
}

