package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Menu {

    private JMenu application;
    private JMenu view;
    private JMenu help;

    //application menu:
    private JMenuItem options;
    private JMenuItem exit;
    //view menu:
    private JMenuItem toggleFullScreen;
    private JMenuItem toggleSidebar;
    //help menu:
    private JMenuItem helpItem;
    private JMenuItem about;

    public Menu(JFrame frame){
        initApplicationMenu();
        initViewMenu();
        initHelpMenu();

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(application);
        menuBar.add(view);
        menuBar.add(help);

        frame.setJMenuBar(menuBar);
    }

    public void initApplicationMenu(){
        application = new JMenu("Application");
        application.setFont(new Font("Calibri", 14, 15));
        application.setMnemonic(KeyEvent.VK_A);

        options = new JMenuItem("Options");
        options.setFont(new Font("Calibri", 14, 15));
        options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));

        exit = new JMenuItem("Exit");
        exit.setFont(new Font("Calibri", 14, 15));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));

        application.add(options);
        application.add(exit);
    }

    public void initViewMenu(){
        view = new JMenu("View");
        view.setFont(new Font("Calibri", 14, 15));
        view.setMnemonic(KeyEvent.VK_V);

        toggleFullScreen = new JMenuItem("Toggle Full Screen");
        toggleFullScreen.setFont(new Font("Calibri", 14, 15));
        toggleFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));

        toggleSidebar = new JMenuItem("Toggle Sidebar");
        toggleSidebar.setFont(new Font("Calibri", 14, 15));
        toggleSidebar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));

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

        about = new JMenuItem("About");
        about.setFont(new Font("Calibri", 14, 15));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));

        help.add(helpItem);
        help.add(about);
    }
}

