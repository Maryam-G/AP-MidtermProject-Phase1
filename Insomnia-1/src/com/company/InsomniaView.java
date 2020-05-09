package com.company;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;


public class InsomniaView {

    private JFrame insomniaFrame;

    private JMenuBar menuBar;
    private JMenu application;
    private JMenu view;
    private JMenu help;
    // --------{ Application }--------
    private JMenuItem options;
    private JMenuItem exit;
    //-> Options:
    private JFrame optionsFrame;
    private JCheckBox enable;
    private JCheckBox disable;
    private JCheckBox exitFromApp;
    private JCheckBox hideOnSystemTray ;
    private JCheckBox lightTheme;
    private JCheckBox darkTheme;
    // --------{ View }-------:
    private JMenuItem toggleFullScreen;
    private JMenuItem toggleSidebar;
    private boolean visibilityOfPanel1;
    private boolean isFullScreen;
    //--------{ Help }-------
    private JMenuItem helpItem;
    private JMenuItem about;
    private JTextArea helpItemText;
    private JFrame helpItemFrame;
    private JTextArea aboutText;
    private JFrame aboutFrame;




    private JSplitPane splitPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    public InsomniaView(){
        insomniaFrame = new JFrame("Insomnia");

        menuBar = new JMenuBar();
        application = new JMenu("Application");
        view = new JMenu("View");
        help = new JMenu("Help");
        // --------{ Application }--------
        options = new JMenuItem("Options");
        exit = new JMenuItem("Exit");
        // -> Options :
        optionsFrame = new JFrame("Options");

        enable = new JCheckBox("Enable");
        disable = new JCheckBox("Disable");

        exitFromApp = new JCheckBox("Exit From App", true);
        hideOnSystemTray = new JCheckBox("Hide On System Tray");

        lightTheme = new JCheckBox("Light Theme");
        darkTheme = new JCheckBox("Dark Theme", true);

        // --------{ View }-------:
        toggleFullScreen = new JMenuItem("Toggle Full Screen");
        toggleSidebar = new JMenuItem("Toggle Sidebar");
        visibilityOfPanel1 = true;
        isFullScreen = false;

        //--------{ Help }-------
        helpItem = new JMenuItem("Help");
        about = new JMenuItem("About");

        helpItemText = new JTextArea();
        helpItemFrame = new JFrame("Help");
        aboutText = new JTextArea();
        aboutFrame = new JFrame("About");


        splitPane = new JSplitPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        setInsomniaFrame();
    }

    public void setInsomniaFrame(){
        insomniaFrame.setSize(new Dimension(1200, 700));
        insomniaFrame.setLocation(400, 150);
        insomniaFrame.setResizable(true);
        insomniaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insomniaFrame.setLayout(new BorderLayout(1, 1));

        setMenuBar();
        insomniaFrame.add(menuBar, BorderLayout.NORTH);

        setSplitPane();
        insomniaFrame.add(splitPane, BorderLayout.CENTER);
    }

    public void setMenuBar(){
        // --------{ Application }--------
        options.addActionListener(new MenuHandler());
        exit.addActionListener(new MenuHandler());
        application.add(options);
        application.add(exit);

        optionsFrame.setSize(new Dimension(300, 300));
        optionsFrame.setLayout(new GridLayout(3, 1, 1, 1));
        optionsFrame.setLocation(800, 400);
        optionsFrame.setResizable(false);

        enable.addItemListener(new CheckBoxHandler());
        disable.addItemListener(new CheckBoxHandler());
        lightTheme.addItemListener(new CheckBoxHandler());
        darkTheme.addItemListener(new CheckBoxHandler());
        exitFromApp.addItemListener(new CheckBoxHandler());
        hideOnSystemTray.addItemListener(new CheckBoxHandler());

        JPanel followRedirectPanel = new JPanel();
        followRedirectPanel.setLayout(new GridLayout(2 , 1, 1, 1));
        followRedirectPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY
                , 1, true), " [ Follow Redirect ]", TitledBorder.LEFT, TitledBorder.TOP));
        followRedirectPanel.add(enable);
        followRedirectPanel.add(disable);

        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridLayout(2, 1, 1, 1));
        exitPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY
                , 1, true), " [ Exit ]", TitledBorder.LEFT, TitledBorder.TOP));
        exitPanel.add(exitFromApp);
        exitPanel.add(hideOnSystemTray);

        JPanel themePanel = new JPanel();
        themePanel.setLayout(new GridLayout(2, 1, 1, 1));
        themePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY
                , 1, true), " [ Theme ]", TitledBorder.LEFT, TitledBorder.TOP));
        themePanel.add(lightTheme);
        themePanel.add(darkTheme);

        optionsFrame.add(followRedirectPanel);
        optionsFrame.add(exitPanel);
        optionsFrame.add(themePanel);

        // --------{ View }-------:
        toggleFullScreen.addActionListener(new MenuHandler());
        view.add(toggleFullScreen);
        toggleSidebar.addActionListener(new MenuHandler());
        view.add(toggleSidebar);

        //--------{ Help }-------
        aboutText.setFont(new Font("Arial", 14, 14));
        aboutText.setText("Hi, I'm Maryam Goli!\n\nMy student number : 9831054\nMy email : goli.mary.m@gmail.com\n\nI hope you enjoy the app :)\n\n~Maryam ");
        aboutText.setEditable(false);
        aboutFrame.setSize(300, 300);
        aboutFrame.setLocation(800 , 400);
        aboutFrame.add(aboutText);

        helpItemText.setFont(new Font("Arial", 14, 14));
        helpItemText.setText(" ... :)");
        helpItemText.setEditable(false);
        helpItemFrame.setSize(300, 300);
        helpItemFrame.setLocation(800, 400);
        helpItemFrame.add(helpItemText);

        about.addActionListener(new MenuHandler());
        help.add(about);
        helpItem.addActionListener(new MenuHandler());
        help.add(helpItem);

        menuBar.add(application);
        menuBar.add(view);
        menuBar.add(help);
    }

    public void setSplitPane(){
        setPanel1();
        setPanel2();
        setPanel3();

        JSplitPane splitPaneForPanel2And3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel2, panel3);
        splitPaneForPanel2And3.setResizeWeight(0.5);
        splitPaneForPanel2And3.setDividerSize(2);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(panel1);
        splitPane.setRightComponent(splitPaneForPanel2And3);
        splitPane.setDividerLocation(300);
        splitPane.setDividerSize(2);

    }

    public void setPanel1(){
        if(darkTheme.isSelected()){
            panel1.setBackground(Color.DARK_GRAY);
        }else if(lightTheme.isSelected()){
            panel1.setBackground(Color.LIGHT_GRAY);
        }
        panel1.setMinimumSize(new Dimension(300, 600));
    }

    public void setPanel2(){
        if(darkTheme.isSelected()){
            panel2.setBackground(Color.DARK_GRAY);
        }else if(lightTheme.isSelected()){
            panel2.setBackground(Color.LIGHT_GRAY);
        }
    }

    public void setPanel3(){
        if(darkTheme.isSelected()){
            panel3.setBackground(Color.DARK_GRAY);
        }else if(lightTheme.isSelected()){
            panel3.setBackground(Color.LIGHT_GRAY);
        }
    }

    public void showGUI(){
        insomniaFrame.setVisible(true);
    }







    private class MenuHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(options)){
                optionsFrame.setVisible(true);
            }else if(e.getSource().equals(toggleFullScreen)){
                if(isFullScreen == false){
                    insomniaFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }else{
                    insomniaFrame.setExtendedState(JFrame.NORMAL);
                }
                isFullScreen = !isFullScreen;
            }else if(e.getSource().equals(toggleSidebar)){
                visibilityOfPanel1 = !visibilityOfPanel1;
                panel1.setVisible(visibilityOfPanel1);
                splitPane.setLeftComponent(panel1);
            }else if(e.getSource().equals(about)){
                aboutFrame.setVisible(true);
            }else if(e.getSource().equals(helpItem)){
                helpItemFrame.setVisible(true);
            }else if(e.getSource().equals(exit)){
                insomniaFrame.dispose();
            }
        }
    }

    private class CheckBoxHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource().equals(lightTheme)){
                panel1.setBackground(Color.LIGHT_GRAY);
                panel2.setBackground(Color.LIGHT_GRAY);
                panel3.setBackground(Color.LIGHT_GRAY);
            }else if(e.getSource().equals(darkTheme)){
                panel1.setBackground(Color.DARK_GRAY);
                panel2.setBackground(Color.DARK_GRAY);
                panel3.setBackground(Color.DARK_GRAY);
            }else if(e.getSource().equals(exitFromApp)){
                insomniaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }else if(e.getSource().equals(hideOnSystemTray)){
                insomniaFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }else if(e.getSource().equals(enable)){
                // In the next phase of the project
            }else if(e.getSource().equals(disable)){
                // In the next phase of the project
            }
        }
    }

}
