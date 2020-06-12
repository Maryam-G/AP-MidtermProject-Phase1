package com.company.gui;

import javax.swing.*;
import java.awt.*;

public class InsomniaFrame extends JFrame{

    private Panel1 panel1;
    private Panel2 panel2;
    private Panel3 panel3;

    public InsomniaFrame(){

        this.setTitle("Insomnia");
        this.setSize(new Dimension(1200, 700));
        this.setLocation(400, 150);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(1, 1));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));

        addMenu();
        addPanels();
    }

    public void addMenu(){
        Menu menu = new Menu();
        this.setJMenuBar(menu);
    }

    public void addPanels(){
        panel1 = new Panel1();
        panel2 = new Panel2();
        panel3 = new Panel3();

        JSplitPane splitPaneForPanel2And3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel2, panel3);
        splitPaneForPanel2And3.setResizeWeight(0.5);
        splitPaneForPanel2And3.setDividerSize(2);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(panel1);
        splitPane.setRightComponent(splitPaneForPanel2And3);
        splitPane.setDividerLocation(300);
        splitPane.setDividerSize(2);

//        setTheme(Color.DARK_GRAY);

        this.add(splitPane, BorderLayout.CENTER);
    }
}
