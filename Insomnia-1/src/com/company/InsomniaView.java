package com.company;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import jdk.nashorn.internal.objects.Global;
import sun.invoke.empty.Empty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;


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
    private JCheckBox hideOnSystemTray;
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

    // ======= > panel 1
    private JButton newFolder;
    private JButton newRequest;
    private String nameOfNewRequest;
    private String nameOfNewFolder;

    private DefaultMutableTreeNode allRequests;
    private JTree treeOfRequests;

    // ======= > panel 2
    // URL panel :
    private JButton sendButton;
    private JComboBox typeComboBox2;
    private JTextField urlField;

    //body panel :
    private JPanel requestBodyPanel;

    private JPanel noBodyPanel;
    private JRadioButton radioButtonNoBody;

    private JPanel formDataPanel;
    private JRadioButton radioButtonFormData;
    private NameValueItemPanel lastFormDataItemPanel;

    private JPanel jsonPanel;
    private JRadioButton radioButtonJSON;
    private JSONItemPanel lastLine;
    private int lineNumber;

    private JPanel binaryFilePanel;
    private JRadioButton radioButtonBinaryFile;

    private JPanel queryPanel;
    private JRadioButton radioButtonQuery;
    private NameValueItemPanel lastQueryItemPanel;

    private JPanel authPanel;
    private JRadioButton radioButtonAuth;

    //header panel :
    private JPanel requestHeaderPanel;
    private HeaderItemPanel lastHeaderItem;

    // ======= > panel 3
    // info panel :
    private JPanel responseInfoPanel;
    private JTextField statusField;
    private JTextField timeField;
    private JTextField sizeField;

    // body panel :
    private JPanel responseBodyPanel;

    private JRadioButton radioButtonRaw;
    private JPanel rawPanel;

    private JRadioButton radioButtonPreview;
    private JPanel previewPanel;

    //header panel :
    private JPanel responseHeaderPanel;
    private JButton copyButton;

    public InsomniaView() {
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

        // ======= > panel 1
        newFolder = new JButton("New Folder");
        newRequest = new JButton("New Request");

        allRequests = new DefaultMutableTreeNode("All Request", true);
        treeOfRequests = new JTree(allRequests);

        // ====== > panel 2
        //URL panel :
        sendButton = new JButton("Send");
        typeComboBox2 = new JComboBox();
        urlField = new JTextField();
        //body panel:
        requestBodyPanel = new JPanel();

        noBodyPanel = new JPanel();
        radioButtonNoBody = new JRadioButton("No-Body");

        formDataPanel = new JPanel();
        radioButtonFormData = new JRadioButton("Form-Data");
        lastFormDataItemPanel = new NameValueItemPanel();

        jsonPanel = new JPanel();
        radioButtonJSON = new JRadioButton("JSON");
        lineNumber = 1;
        lastLine = new JSONItemPanel(lineNumber);

        binaryFilePanel = new JPanel();
        radioButtonBinaryFile = new JRadioButton("Binary-File");

        queryPanel = new JPanel();
        radioButtonQuery = new JRadioButton("Query");
        lastQueryItemPanel = new NameValueItemPanel();

        authPanel = new JPanel();
        radioButtonAuth = new JRadioButton("Auth-Bearer");

        //header panel:
        requestHeaderPanel = new JPanel();
        lastHeaderItem = new HeaderItemPanel();

        // ====== > panel 3
        //info panel :
        responseInfoPanel = new JPanel();
        statusField = new JTextField(" [ Status ] ");
        timeField = new JTextField(" [ Time ] ");
        sizeField = new JTextField(" [ Size ] ");
        //body panel :
        responseBodyPanel = new JPanel();

        radioButtonRaw = new JRadioButton("Raw");
        rawPanel = new JPanel();

        radioButtonPreview = new JRadioButton("Preview");
        previewPanel = new JPanel();

        //header panel :
        responseHeaderPanel = new JPanel();
        copyButton = new JButton("Copy to Clipboard");

        setInsomniaFrame();
    }

    public void setInsomniaFrame() {
        insomniaFrame.setSize(new Dimension(1200, 700));
        insomniaFrame.setLocation(400, 150);
        insomniaFrame.setResizable(true);
        insomniaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insomniaFrame.setLayout(new BorderLayout(1, 1));
        insomniaFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));

        setMenuBar();
        insomniaFrame.add(menuBar, BorderLayout.NORTH);

        setSplitPane();
        insomniaFrame.add(splitPane, BorderLayout.CENTER);
    }

    public void setMenuBar() {

        //set Mnemonic
        application.setMnemonic(KeyEvent.VK_A);
        view.setMnemonic(KeyEvent.VK_V);
        help.setMnemonic(KeyEvent.VK_H);

        //set Accelerator for JMenuItems:
        options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        toggleSidebar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        toggleFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));

        // set font :
        application.setFont(new Font("Calibri", 14, 15));
        view.setFont(new Font("Calibri", 14, 15));
        help.setFont(new Font("Calibri", 14, 15));

        options.setFont(new Font("Calibri", 14, 15));
        exit.setFont(new Font("Calibri", 14, 15));
        toggleSidebar.setFont(new Font("Calibri", 14, 15));
        toggleFullScreen.setFont(new Font("Calibri", 14, 15));
        helpItem.setFont(new Font("Calibri", 14, 15));
        about.setFont(new Font("Calibri", 14, 15));

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

        // --------{ View }-------:
        toggleFullScreen.addActionListener(new MenuHandler());
        view.add(toggleFullScreen);
        toggleSidebar.addActionListener(new MenuHandler());
        view.add(toggleSidebar);

        //--------{ Help }-------
        aboutText.setFont(new Font("Calibri", 14, 14));
        aboutText.setText("Hi, I'm Maryam Goli!\n\nMy student number : 9831054\nMy email : goli.mary.m@gmail.com\n\nI hope you enjoy the app :)\n\n~Maryam ");
        aboutText.setEditable(false);
        aboutFrame.setSize(300, 300);
        aboutFrame.setLocation(800, 400);
        aboutFrame.add(aboutText);

        helpItemText.setFont(new Font("Calibri", 14, 14));
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

    public void setSplitPane() {
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

        setTheme(Color.DARK_GRAY);

    }

    //==============================================================> panel 1

    public void setPanel1() {

        panel1.setMinimumSize(new Dimension(300, 600));

        // for label "Insomnia" on top of panel 1
        JPanel insomniaPanel = new JPanel();
        insomniaPanel.setLayout(new BorderLayout());
        insomniaPanel.setBackground(new Color(148, 40, 255));
        insomniaPanel.setPreferredSize(new Dimension(insomniaPanel.getWidth(), 50));

        JLabel insomniaLabel = new JLabel();
        insomniaLabel.setText("  Insomnia");
        insomniaLabel.setFont(new Font("Calibri", 45, 22));
        insomniaLabel.setForeground(Color.WHITE);
        insomniaLabel.setHorizontalAlignment(JLabel.LEFT);
        insomniaLabel.setVerticalAlignment(JLabel.CENTER);

        insomniaPanel.add(insomniaLabel);

        panel1.setLayout(new BorderLayout());
        panel1.add(insomniaPanel, BorderLayout.PAGE_START);

        // for showing list of requests
        JPanel requestPanel = new JPanel();
        requestPanel.setLayout(new BorderLayout());
        requestPanel.setBorder(new EmptyBorder(3, 3, 3, 0));

        // buttons for create new folder or new request
        newFolder.setFont(new Font("Calibri", 45, 15));
        newRequest.setFont(new Font("Calibri", 45, 15));
        JPanel newRequestButtons = new JPanel();
        newRequestButtons.setBorder(new EmptyBorder(1, 0, 2, 0));
        newRequestButtons.setLayout(new GridLayout(1, 2, 2, 2));

        newRequest.addActionListener(new RequestButtonHandler());
        newFolder.addActionListener(new RequestButtonHandler());

        newRequestButtons.add(newRequest);
        newRequestButtons.add(newFolder);

        allRequests = new DefaultMutableTreeNode("All Request", true);
        DefaultMutableTreeNode firstRequest = new DefaultMutableTreeNode("FirstRequest");
        allRequests.add(firstRequest);

        treeOfRequests = new JTree(allRequests);
        treeOfRequests.scrollPathToVisible(new TreePath(allRequests.getPath()));

        requestPanel.add(newRequestButtons, BorderLayout.NORTH);
        requestPanel.add(treeOfRequests, BorderLayout.CENTER);

        panel1.add(requestPanel, BorderLayout.CENTER);

        final DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) (treeOfRequests.getCellRenderer());
        renderer.setFont(new Font("Calibri", 45, 19));
        treeOfRequests.setRowHeight(30);

        allRequests.breadthFirstEnumeration();
    }

    //==============================================================> panel 2

    public void setPanel2() {

        // create URL panel :
        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new BorderLayout());
        urlPanel.setPreferredSize(new Dimension(urlPanel.getWidth(), 50));

        typeComboBox2.setFont(new Font("Calibri", 45, 15));
        typeComboBox2.addItem("GET");
        typeComboBox2.addItem("POST");
        typeComboBox2.addItem("PUT");
        typeComboBox2.addItem("PATCH");
        typeComboBox2.addItem("DELETE");
        typeComboBox2.addItem("OPTIONS");
        typeComboBox2.addItem("HEAD");

        urlField.setText("Enter URL ...");
        urlField.setFont(new Font("Calibri", 45, 15));

        sendButton.setFont(new Font("Calibri", 45, 15));
        sendButton.setHorizontalAlignment(JButton.CENTER);

        urlPanel.add(typeComboBox2, BorderLayout.WEST);
        urlPanel.add(urlField, BorderLayout.CENTER);
        urlPanel.add(sendButton, BorderLayout.EAST);

        urlPanel.setVisible(true);

        panel2.setLayout(new BorderLayout());
        panel2.add(urlPanel, BorderLayout.PAGE_START);

        // create tabs for body and header

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Calibri", 45, 18));

        // body :
        setRequestBodyPanel();
        //header:
        setRequestHeaderPanel();

        // add panels to tabs of tabbedPane
        tabbedPane.add("Body", new JScrollPane(requestBodyPanel));
        tabbedPane.add("Header", new JScrollPane(requestHeaderPanel));

        panel2.add(tabbedPane, BorderLayout.CENTER);

    }

    public void setRequestHeaderPanel(){

        HeaderItemPanel firstHeader = new HeaderItemPanel();
        lastHeaderItem = firstHeader;

        BoxLayout boxLayout = new BoxLayout(requestHeaderPanel, BoxLayout.Y_AXIS);
        requestHeaderPanel.setLayout(boxLayout);

        requestHeaderPanel.add(lastHeaderItem);
        lastHeaderItem.getKeyField().addFocusListener(new FocusHandler());
        lastHeaderItem.getValueField().addFocusListener(new FocusHandler());
    }

    public void setRequestBodyPanel(){

        radioButtonNoBody.setSelected(true);

        radioButtonNoBody.addActionListener(new RadioButtonHandler());
        radioButtonFormData.addActionListener(new RadioButtonHandler());
        radioButtonJSON.addActionListener(new RadioButtonHandler());
        radioButtonBinaryFile.addActionListener(new RadioButtonHandler());
        radioButtonQuery.addActionListener(new RadioButtonHandler());
        radioButtonAuth.addActionListener(new RadioButtonHandler());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonNoBody);
        buttonGroup.add(radioButtonFormData);
        buttonGroup.add(radioButtonJSON);
        buttonGroup.add(radioButtonBinaryFile);
        buttonGroup.add(radioButtonQuery);
        buttonGroup.add(radioButtonAuth);

        JPanel buttonGroupPanel = new JPanel();
        buttonGroupPanel.setLayout(new FlowLayout());
        buttonGroupPanel.add(radioButtonNoBody);
        buttonGroupPanel.add(radioButtonFormData);
        buttonGroupPanel.add(radioButtonJSON);
        buttonGroupPanel.add(radioButtonBinaryFile);
        buttonGroupPanel.add(radioButtonQuery);
        buttonGroupPanel.add(radioButtonAuth);

        requestBodyPanel.setLayout(new BorderLayout());
        requestBodyPanel.add(new JScrollPane(buttonGroupPanel), BorderLayout.NORTH);

        setJsonPanel();
        setFormDataPanel();
        setBinaryFilePanel();
        setQueryPanel();
        setAuthPanel();
    }

    public void setFormDataPanel(){

        NameValueItemPanel firstFormDataItem = new NameValueItemPanel();
        lastFormDataItemPanel = firstFormDataItem;

        BoxLayout boxLayout = new BoxLayout(formDataPanel, BoxLayout.Y_AXIS);
        formDataPanel.setLayout(boxLayout);

        formDataPanel.add(lastFormDataItemPanel);
        lastFormDataItemPanel.getNameField().addFocusListener(new FocusHandler());
        lastFormDataItemPanel.getValueField().addFocusListener(new FocusHandler());
    }

    public void setJsonPanel(){

        JSONItemPanel firstLine = new JSONItemPanel(lineNumber);

        lastLine = firstLine;
        lastLine.getTextField().addFocusListener(new FocusHandler());

        BoxLayout boxLayout = new BoxLayout(jsonPanel, BoxLayout.Y_AXIS);
        jsonPanel.setLayout(boxLayout);

        jsonPanel.add(lastLine);

    }

    public void setBinaryFilePanel(){

        binaryFilePanel.setBorder(new EmptyBorder(10, 5, 5, 5));

        JTextField selectedFilePath = new JTextField("No file selected!");
        selectedFilePath.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        selectedFilePath.setFont(new Font("Calibri", 45, 15));
        selectedFilePath.setEditable(false);

        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.setFont(new Font("Calibri", 45, 15));

        JFileChooser fileChooser = new JFileChooser();

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(chooseFileButton)){
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(binaryFilePanel);
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedFilePath.setText(selectedFile.getAbsolutePath());
                    panel1.updateUI();
                }
            }
        });
        BoxLayout boxLayout = new BoxLayout(binaryFilePanel, BoxLayout.Y_AXIS);
        binaryFilePanel.setLayout(boxLayout);

        binaryFilePanel.add(selectedFilePath);
        binaryFilePanel.add(chooseFileButton);
    }

    public void setQueryPanel(){

        queryPanel.setBorder(new EmptyBorder(10, 5, 5, 5));

        BoxLayout boxLayout = new BoxLayout(queryPanel, BoxLayout.Y_AXIS);
        queryPanel.setLayout(boxLayout);

        JTextField urlPreview = new JTextField(" URL Preview ...");
        urlPreview.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        queryPanel.add(urlPreview);

        NameValueItemPanel firstQueryItem = new NameValueItemPanel();
        lastQueryItemPanel = firstQueryItem;

        queryPanel.add(lastQueryItemPanel);
        lastQueryItemPanel.getNameField().addFocusListener(new FocusHandler());
        lastQueryItemPanel.getValueField().addFocusListener(new FocusHandler());

    }

    public void setAuthPanel(){

        authPanel.setBorder(new EmptyBorder(10, 5, 5, 5));

        BoxLayout boxLayout = new BoxLayout(authPanel, BoxLayout.Y_AXIS);
        authPanel.setLayout(boxLayout);

        JTextField tokenField = new JTextField("  ...");
        JLabel tokenLabel = new JLabel("  Token          ");

        JTextField prefixField = new JTextField("  ...");
        JLabel prefixLabel = new JLabel("  Prefix           ");

        JLabel enabledLabel = new JLabel("  Enabled       ");
        JCheckBox enabled = new JCheckBox();

        JPanel tokenPanel = new JPanel();
        tokenPanel.setLayout(new BorderLayout());
        tokenPanel.add(tokenLabel, BorderLayout.WEST);
        tokenPanel.add(tokenField, BorderLayout.CENTER);
        tokenPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JPanel prefixPanel = new JPanel();
        prefixPanel.setLayout(new BorderLayout());
        prefixPanel.add(prefixLabel, BorderLayout.WEST);
        prefixPanel.add(prefixField, BorderLayout.CENTER);
        prefixPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JPanel enabledPanel = new JPanel();
        enabledPanel.setLayout(new BorderLayout());
        enabledPanel.add(enabledLabel, BorderLayout.WEST);
        enabledPanel.add(enabled, BorderLayout.CENTER);
        enabledPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        tokenLabel.setFont(new Font("Calibri", 45, 16));
        prefixLabel.setFont(new Font("Calibri", 45, 16));
        enabledLabel.setFont(new Font("Calibri", 45, 16));

        authPanel.add(tokenPanel);
        authPanel.add(prefixPanel);
        authPanel.add(enabledPanel);

    }


    //==============================================================> panel 3

    public void setPanel3() {

        panel3.setLayout(new BorderLayout());
        setResponseInfoPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Calibri", 45, 18));

        // body :
        setResponseBodyPanel();
        //header:
        setResponseHeaderPanel();

        // add panels to tabs of tabbedPane
        tabbedPane.add("Body", new JScrollPane(responseBodyPanel));
        tabbedPane.add("Header", new JScrollPane(responseHeaderPanel));

        panel3.add(tabbedPane, BorderLayout.CENTER);

    }

    public void setResponseInfoPanel(){
        // top of panel 3:
        statusField.setFont(new Font("Calibri", 45, 17));
        timeField.setFont(new Font("Calibri", 45, 17));
        sizeField.setFont(new Font("Calibri", 45, 17));

        statusField.setEditable(false);
        timeField.setEditable(false);
        sizeField.setEditable(false);

        statusField.setBackground(new Color(207, 214, 210));
        statusField.isOpaque();
        timeField.setBackground(new Color(207, 214, 210));
        timeField.isOpaque();
        sizeField.setBackground(new Color(207, 214, 210));
        sizeField.isOpaque();

        responseInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 6));
        responseInfoPanel.setBackground(Color.WHITE);

        responseInfoPanel.add(statusField);
        responseInfoPanel.add(timeField);
        responseInfoPanel.add(sizeField);

        responseInfoPanel.setPreferredSize(new Dimension(responseInfoPanel.getWidth(), 50));

        panel3.add(responseInfoPanel, BorderLayout.PAGE_START);
    }

    public void setResponseHeaderPanel(){

        BoxLayout boxLayout = new BoxLayout(responseHeaderPanel, BoxLayout.Y_AXIS);
        responseHeaderPanel.setLayout(boxLayout);

        JTextField keyField = new JTextField(" key : ----");
        keyField.setEditable(false);
        JTextField valueField = new JTextField(" value : ---- ");
        valueField.setEditable(false);

        keyField.setFont(new Font("Calibri", 45, 15));
        valueField.setFont(new Font("Calibri", 45, 15));

        JPanel keyValuePanel = new JPanel();
        keyValuePanel.setLayout(new GridLayout());
        keyValuePanel.add(keyField);
        keyValuePanel.add(valueField);
        keyValuePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        responseHeaderPanel.add(keyValuePanel);

        JTextField keyField2 = new JTextField(" key 2 : ----");
        keyField2.setEditable(false);
        JTextField valueField2 = new JTextField(" value 2 : ---- ");
        valueField2.setEditable(false);

        keyField2.setFont(new Font("Calibri", 45, 15));
        valueField2.setFont(new Font("Calibri", 45, 15));

        JPanel keyValuePanel2 = new JPanel();
        keyValuePanel2.setLayout(new GridLayout());
        keyValuePanel2.add(keyField2);
        keyValuePanel2.add(valueField2);
        keyValuePanel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        responseHeaderPanel.add(keyValuePanel2);

        //button copy to clipboard :

        copyButton.setFont(new Font("Calibri", 45, 15));
        copyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        responseHeaderPanel.add(copyButton);
    }

    public void setResponseBodyPanel(){

        radioButtonRaw.setSelected(true);

        radioButtonRaw.addActionListener(new RadioButtonHandler());
        radioButtonPreview.addActionListener(new RadioButtonHandler());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonRaw);
        buttonGroup.add(radioButtonPreview);

        JPanel buttonGroupPanel = new JPanel();
        buttonGroupPanel.setLayout(new FlowLayout());
        buttonGroupPanel.add(radioButtonRaw);
        buttonGroupPanel.add(radioButtonPreview);

        responseBodyPanel.setLayout(new BorderLayout());
        responseBodyPanel.add(buttonGroupPanel, BorderLayout.NORTH);
    }


    //----------------------------------------------------------------> Show GUI

    public void showGUI() {
        insomniaFrame.setVisible(true);
    }

    //---------------------------------------------------------------->  Event Handling

    private class MenuHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(options)) {
                optionsFrame.setVisible(true);
            } else if (e.getSource().equals(toggleFullScreen)) {
                if (isFullScreen == false) {
                    insomniaFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                } else {
                    insomniaFrame.setExtendedState(JFrame.NORMAL);
                }
                isFullScreen = !isFullScreen;
            } else if (e.getSource().equals(toggleSidebar)) {
                visibilityOfPanel1 = !visibilityOfPanel1;
                panel1.setVisible(visibilityOfPanel1);
                splitPane.setLeftComponent(panel1);
            } else if (e.getSource().equals(about)) {
                aboutFrame.setVisible(true);
            } else if (e.getSource().equals(helpItem)) {
                helpItemFrame.setVisible(true);
            } else if (e.getSource().equals(exit)) {
//                insomniaFrame.dispose();
//                new HideToSystemTray();

            }
        }
    }

    private class CheckBoxHandler implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource().equals(lightTheme)) {
                setTheme(Color.LIGHT_GRAY);
            } else if (e.getSource().equals(darkTheme)) {
                setTheme(Color.DARK_GRAY);
            } else if (e.getSource().equals(exitFromApp)) {
                insomniaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else if (e.getSource().equals(hideOnSystemTray)) {
                insomniaFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            } else if (e.getSource().equals(enable)) {
                // In the next phase of the project
            } else if (e.getSource().equals(disable)) {
                // In the next phase of the project
            }
        }
    }

    public void setTheme(Color newColor){
        insomniaFrame.setBackground(newColor);

        panel1.setBackground(newColor);
        panel2.setBackground(newColor);
        panel3.setBackground(newColor);

        treeOfRequests.setBackground(newColor);
        final DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) (treeOfRequests.getCellRenderer());
        if(newColor.equals(Color.DARK_GRAY)){
            renderer.setTextNonSelectionColor(Color.WHITE);
            renderer.setTextSelectionColor(new Color(197, 138, 255));
        }else{
            renderer.setTextNonSelectionColor(Color.BLACK);
            renderer.setTextSelectionColor(new Color(197, 138, 255));
        }

        requestBodyPanel.setBackground(newColor);
        noBodyPanel.setBackground(newColor);
        formDataPanel.setBackground(newColor);
        jsonPanel.setBackground(newColor);
        binaryFilePanel.setBackground(newColor);
        queryPanel.setBackground(newColor);
        authPanel.setBackground(newColor);

        requestHeaderPanel.setBackground(newColor);

        responseBodyPanel.setBackground(newColor);
        rawPanel.setBackground(newColor);
        previewPanel.setBackground(newColor);

        responseHeaderPanel.setBackground(newColor);
    }

    private class RequestButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            DefaultMutableTreeNode parentNode;
            TreePath parentPath = treeOfRequests.getSelectionPath();

            //button : New Folder
            if (e.getSource().equals(newFolder)) {
                if (parentPath == null) {
                    //There is no selection. Default to the root node (all request).
                    parentNode = allRequests;
                } else {
                    parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
                    if (parentNode.getChildCount() == 0) {
                        JOptionPane.showMessageDialog(null, "You can not create new folder here!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        buildFrameForNameOfFolder(parentNode);
                    }
                }
            //button : New Request
            } else if (e.getSource().equals(newRequest)) {
                if (parentPath == null) {
                    //There is no selection. Default to the root node (all request).
                    parentNode = allRequests;
                } else {
                    parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
                    if (parentNode.getChildCount() == 0) {
                        buildFrameForNameOfRequest(allRequests);
                    } else {
                        buildFrameForNameOfRequest(parentNode);
                    }
                }
            }
        }

        public void buildFrameForNameOfFolder(DefaultMutableTreeNode parentNode) {
            JFrame setNameFrame = new JFrame("New Folder");
            setNameFrame.setSize(new Dimension(500, 200));
            setNameFrame.setLocation(900, 400);
            setNameFrame.setLayout(new BorderLayout());
            setNameFrame.setResizable(false);

            JPanel namePanel = new JPanel();
            namePanel.setLayout(new BorderLayout());
            namePanel.setBorder(new EmptyBorder(30, 10, 30, 10));

            JLabel nameLabel = new JLabel("Name");
            nameLabel.setFont(new Font("Calibri", 14, 19));
            nameLabel.setBorder(new EmptyBorder(0, 5, 10, 10));

            JTextField nameField = new JTextField();
            nameField.setText("My Folder");

            JButton createButton = new JButton("Create");

            namePanel.add(nameLabel, BorderLayout.NORTH);
            namePanel.add(nameField, BorderLayout.CENTER);

            setNameFrame.add(namePanel, BorderLayout.CENTER);
            setNameFrame.add(createButton, BorderLayout.SOUTH);

            setNameFrame.setVisible(true);

            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nameOfNewFolder = nameField.getText();
                    DefaultMutableTreeNode newFolder = new DefaultMutableTreeNode(nameOfNewFolder);
                    DefaultMutableTreeNode firstRequest = new DefaultMutableTreeNode("First Request");
                    newFolder.add(firstRequest);

                    DefaultTreeModel model = (DefaultTreeModel) treeOfRequests.getModel();
                    model.insertNodeInto(newFolder, parentNode, parentNode.getChildCount());
                    treeOfRequests.scrollPathToVisible(new TreePath(newFolder.getPath()));

                    setNameFrame.setVisible(false);
                }
            });
        }

        public void buildFrameForNameOfRequest(DefaultMutableTreeNode parentNode) {
            JFrame setNameFrame = new JFrame("New Request");
            setNameFrame.setSize(new Dimension(500, 230));
            setNameFrame.setLocation(900, 400);
            setNameFrame.setLayout(new BorderLayout());
            setNameFrame.setResizable(false);

            JPanel newRequestPanel = new JPanel();
            newRequestPanel.setLayout(new BorderLayout());
            newRequestPanel.setBorder(new EmptyBorder(20, 10, 10, 10));

            // create panel and add to frame
            JPanel namePanel = new JPanel();
            namePanel.setLayout(new BorderLayout());
            namePanel.setBorder(new EmptyBorder(30, 10, 30, 10));

            JLabel nameLabel = new JLabel("Name");
            nameLabel.setFont(new Font("Calibri", 14, 19));
            nameLabel.setBorder(new EmptyBorder(0, 5, 10, 10));

            JTextField nameField = new JTextField();
            nameField.setText("My Request");

            JComboBox typeOfRequest = new JComboBox();
            typeOfRequest.addItem("GET");
            typeOfRequest.addItem("POST");
            typeOfRequest.addItem("PUT");
            typeOfRequest.addItem("PATCH");
            typeOfRequest.addItem("DELETE");
            typeOfRequest.addItem("OPTIONS");
            typeOfRequest.addItem("HEAD");

            JButton createButton = new JButton("Create");

            namePanel.add(nameLabel, BorderLayout.NORTH);
            namePanel.add(nameField, BorderLayout.CENTER);
            namePanel.add(typeOfRequest, BorderLayout.EAST);

            newRequestPanel.add(namePanel, BorderLayout.CENTER);
            newRequestPanel.add(createButton, BorderLayout.SOUTH);

            setNameFrame.add(newRequestPanel, BorderLayout.CENTER);
            setNameFrame.setVisible(true);

            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nameOfNewRequest = nameField.getText();
                    DefaultMutableTreeNode newRequest = new DefaultMutableTreeNode(nameOfNewRequest + " [" + typeOfRequest.getSelectedItem() + "]");

                    DefaultTreeModel model = (DefaultTreeModel) treeOfRequests.getModel();
                    model.insertNodeInto(newRequest, parentNode, parentNode.getChildCount());
                    treeOfRequests.scrollPathToVisible(new TreePath(newRequest.getPath()));

                    setNameFrame.setVisible(false);
                }
            });
        }

    }

    private class FocusHandler implements FocusListener{

        @Override
        public void focusGained(FocusEvent e) {
            // focus on  panel2 (request-panel) -> Header
            if(e.getSource().equals(lastHeaderItem.getKeyField()) || e.getSource().equals(lastHeaderItem.getValueField())) {
                HeaderItemPanel newHeaderItem = new HeaderItemPanel();
                lastHeaderItem = newHeaderItem;
                lastHeaderItem.getKeyField().addFocusListener(this);
                lastHeaderItem.getValueField().addFocusListener(this);

                lastHeaderItem.setVisible(true);
                requestHeaderPanel.add(lastHeaderItem);
                panel2.updateUI();
            }
            //focus on panel2 (request-panel) -> Body -> JSON
            else if(e.getSource().equals(lastLine.getTextField())){
                lineNumber++;
                JSONItemPanel newLine = new JSONItemPanel(lineNumber);
                lastLine = newLine;
                lastLine.getTextField().addFocusListener(this);

                lastLine.setVisible(true);
                jsonPanel.add(lastLine);
                panel2.updateUI();
            }
            //focus on panel2 (request-panel) -> Body -> Form-Data
            else if(e.getSource().equals(lastFormDataItemPanel.getNameField()) || e.getSource().equals(lastFormDataItemPanel.getValueField())) {
                NameValueItemPanel newFormDataItem = new NameValueItemPanel();
                lastFormDataItemPanel = newFormDataItem;
                lastFormDataItemPanel.getNameField().addFocusListener(this);
                lastFormDataItemPanel.getValueField().addFocusListener(this);

                lastFormDataItemPanel.setVisible(true);
                formDataPanel.add(lastFormDataItemPanel);
                panel2.updateUI();
            }
            //focus on panel2 (request-panel) -> Body -> Query
            else if(e.getSource().equals(lastQueryItemPanel.getNameField()) || e.getSource().equals(lastQueryItemPanel.getValueField())) {
                NameValueItemPanel newQueryItem = new NameValueItemPanel();
                lastQueryItemPanel = newQueryItem;
                lastQueryItemPanel.getNameField().addFocusListener(this);
                lastQueryItemPanel.getValueField().addFocusListener(this);

                lastQueryItemPanel.setVisible(true);
                queryPanel.add(lastQueryItemPanel);
                panel2.updateUI();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
        }
    }

    private class RadioButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //panel 2:
            if(radioButtonNoBody.isSelected()){
                noBodyPanel.setVisible(true);
                requestBodyPanel.add(noBodyPanel, BorderLayout.CENTER);
                formDataPanel.setVisible(false);
                jsonPanel.setVisible(false);
                binaryFilePanel.setVisible(false);
                queryPanel.setVisible(false);
                authPanel.setVisible(false);
                panel2.updateUI();
            }else if(radioButtonFormData.isSelected()){
                formDataPanel.setVisible(true);
                requestBodyPanel.add(formDataPanel, BorderLayout.CENTER);
                noBodyPanel.setVisible(false);
                jsonPanel.setVisible(false);
                binaryFilePanel.setVisible(false);
                queryPanel.setVisible(false);
                authPanel.setVisible(false);
                panel2.updateUI();
            }else if(radioButtonJSON.isSelected()){
                jsonPanel.setVisible(true);
                requestBodyPanel.add(jsonPanel, BorderLayout.CENTER);
                formDataPanel.setVisible(false);
                noBodyPanel.setVisible(false);
                binaryFilePanel.setVisible(false);
                queryPanel.setVisible(false);
                authPanel.setVisible(false);
                panel2.updateUI();
            }else if(radioButtonBinaryFile.isSelected()){
                binaryFilePanel.setVisible(true);
                requestBodyPanel.add(binaryFilePanel, BorderLayout.CENTER);
                noBodyPanel.setVisible(false);
                formDataPanel.setVisible(false);
                jsonPanel.setVisible(false);
                queryPanel.setVisible(false);
                authPanel.setVisible(false);
                panel2.updateUI();
            }else if(radioButtonQuery.isSelected()){
                queryPanel.setVisible(true);
                requestBodyPanel.add(queryPanel, BorderLayout.CENTER);
                noBodyPanel.setVisible(false);
                formDataPanel.setVisible(false);
                jsonPanel.setVisible(false);
                binaryFilePanel.setVisible(false);
                authPanel.setVisible(false);
                panel2.updateUI();
            }else if(radioButtonAuth.isSelected()){
                authPanel.setVisible(true);
                requestBodyPanel.add(authPanel, BorderLayout.CENTER);
                noBodyPanel.setVisible(false);
                formDataPanel.setVisible(false);
                jsonPanel.setVisible(false);
                binaryFilePanel.setVisible(false);
                queryPanel.setVisible(false);
                panel2.updateUI();
            }

            // panel 3 :
            if(radioButtonRaw.isSelected()){
                rawPanel.setVisible(true);
                responseBodyPanel.add(rawPanel, BorderLayout.CENTER);
                previewPanel.setVisible(false);
                panel3.updateUI();
            }else if(radioButtonPreview.isSelected()){
                previewPanel.setVisible(true);
                responseBodyPanel.add(previewPanel, BorderLayout.CENTER);
                rawPanel.setVisible(false);
                panel3.updateUI();
            }

        }
    }

    public class HideToSystemTray extends JFrame{
        TrayIcon trayIcon;
        SystemTray tray;
        HideToSystemTray(){
            super("SystemTray test");
            System.out.println("creating instance");
            try{
                System.out.println("setting look and feel");
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch(Exception e){
                System.out.println("Unable to set LookAndFeel");
            }
            if(SystemTray.isSupported()){
                System.out.println("system tray supported");
                tray=SystemTray.getSystemTray();

                Image image=Toolkit.getDefaultToolkit().getImage("/media/faisal/DukeImg/Duke256.png");
                ActionListener exitListener=new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Exiting....");
                        System.exit(0);
                    }
                };
                PopupMenu popup=new PopupMenu();
                MenuItem defaultItem=new MenuItem("Exit");
                defaultItem.addActionListener(exitListener);
                popup.add(defaultItem);
                defaultItem=new MenuItem("Open");
                defaultItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(true);
                        setExtendedState(JFrame.NORMAL);
                    }
                });
                popup.add(defaultItem);
                trayIcon=new TrayIcon(image, "SystemTray Demo", popup);
                trayIcon.setImageAutoSize(true);
            }else{
                System.out.println("system tray not supported");
            }
            addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if(e.getNewState()==ICONIFIED){
                        try {
                            tray.add(trayIcon);
                            setVisible(false);
                            System.out.println("added to SystemTray");
                        } catch (AWTException ex) {
                            System.out.println("unable to add to tray");
                        }
                    }
                    if(e.getNewState()==7){
                        try{
                            tray.add(trayIcon);
                            setVisible(false);
                            System.out.println("added to SystemTray");
                        }catch(AWTException ex){
                            System.out.println("unable to add to system tray");
                        }
                    }
                    if(e.getNewState()==MAXIMIZED_BOTH){
                        tray.remove(trayIcon);
                        setVisible(true);
                        System.out.println("Tray icon removed");
                    }
                    if(e.getNewState()==NORMAL){
                        tray.remove(trayIcon);
                        setVisible(true);
                        System.out.println("Tray icon removed");
                    }
                }
            });
            setIconImage(Toolkit.getDefaultToolkit().getImage("Duke256.png"));

            setVisible(true);
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}

