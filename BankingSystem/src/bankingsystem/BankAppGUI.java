/*
 * This is the class that contains the GUI information for the Banking Application;
 * it displays a list of checking and savings accounts, account details for a 
 * specified account, a filtering option for the aforementioned list, a create
 * new account option, and additional buttons for transactions such as withdrawals,
 * deposits, and exiting the program.
 */
package bankingsystem;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Marian Lam
 */
public class BankAppGUI {

    /* class constructor */
    public BankAppGUI() {

        /* ACCOUNT PANEL */
        accountPanel = new JPanel();
        /* create JList to display list of bank accounts */
        listModel = new DefaultListModel();
        accountList = new JList(listModel);
        accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountList.setLayoutOrientation(JList.VERTICAL);
        listScroller = new JScrollPane(accountList);
        listScroller.setPreferredSize(LIST_DIMENSIONS);
        /* add titled border */
        listOfAccountsTitle = BorderFactory.createTitledBorder("List of Accounts");
        listScroller.setBorder(listOfAccountsTitle);

        /* create JTextArea to display selected account information */
        accountInfoTextArea = new JTextArea(FIELD_ROWS, FIELD_COLUMNS);
        accountInfoTextArea.setEditable(false);

        /* add titled border */
        accountInfoTitle = BorderFactory.createTitledBorder("Account Details");
        accountInfoTextArea.setBorder(accountInfoTitle);
        accountInfoTextArea.setLineWrap(true);

        /* create JRadioButtons for list filtering options */
        checkingRadioButton = new JRadioButton("Checking");
        savingsRadioButton = new JRadioButton("Savings");
        /* set bothRadioButton to be initially selected */
        bothRadioButton = new JRadioButton("Both", true);
        /* add filtering buttons to ButtonGroup */
        buttonGroup = new ButtonGroup();
        buttonGroup.add(checkingRadioButton);
        buttonGroup.add(savingsRadioButton);
        buttonGroup.add(bothRadioButton);

        radioButtonPanel = new JPanel();

        /* add JComponents to JPanel */
        radioButtonPanel.add(checkingRadioButton);
        radioButtonPanel.add(savingsRadioButton);
        radioButtonPanel.add(bothRadioButton);
        /* add titled border */
        filterByTypeTitle = BorderFactory.createTitledBorder("Filter by type");
        radioButtonPanel.setBorder(filterByTypeTitle);

        /* instantiate Create Account button and panel */
        createAccountButton = new JButton("Create Account");
        createAccountButtonPanel = new JPanel();
        createAccountButtonPanel.add(createAccountButton);
        /* create Layout Manager for button panel */
        createAccountButtonPanel.setLayout(new GridLayout(1, 1));
        /* add titled border */
        createAccountTitle = BorderFactory.createTitledBorder("Need an account?");
        createAccountButtonPanel.setBorder(createAccountTitle);

        /* create Layout Manager for all components in ACCOUNT PANEL */
        GroupLayout layout = new GroupLayout(accountPanel);
        accountPanel.setLayout(layout);
        /* specify automatic gap insertion */
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        /* horizontally align components */
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(listScroller)
                        .addComponent(radioButtonPanel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(accountInfoTextArea)
                        .addComponent(createAccountButtonPanel))
        );

        /* vertically align components */
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(listScroller)
                        .addComponent(accountInfoTextArea))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(radioButtonPanel)
                        .addComponent(createAccountButtonPanel))
        );

        /* TRANSACTION PANEL */
        transactionPanel = new JPanel();
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        exitButton = new JButton("Exit");
        /* create Layout Manager */
        transactionPanel.setLayout(new GridLayout(1, 3));
        /* add JComponents to JPanel */
        transactionPanel.add(withdrawButton);
        transactionPanel.add(depositButton);
        transactionPanel.add(exitButton);
        /* add titled border */
        transactionTitle = BorderFactory.createTitledBorder("Transactions");
        transactionPanel.setBorder(transactionTitle);

        /* add panels to JFrame */
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(accountPanel, BorderLayout.NORTH);
        frame.add(transactionPanel, BorderLayout.SOUTH);
        /* set JFrame title */
        frame.setTitle("Banking Application");
        /* set JFrame defaults */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /* public interface */
    public void makeTransactionButtonsUneditable() {
        withdrawButton.setEnabled(false);
        depositButton.setEnabled(false);
        exitButton.setEnabled(false);
    }
    
    public void makeTransactionButtonsEditable() {
        withdrawButton.setEnabled(true);
        depositButton.setEnabled(true);
        exitButton.setEnabled(true);
    }
    
    public void addElementToListModel(Object o) {
        listModel.addElement(o);
    }

    public void clearListModel() {
        listModel.clear();
    }

    public int getListSelectedIndex() {
        return accountList.getSelectedIndex();
    }

    public String getListSelectedValue() {
        return (String)accountList.getSelectedValue();
    }

    public void clearListSelection() {
        accountList.clearSelection();
    }
    
    public void setListSelectedValue(String name, boolean scroll) {
        accountList.setSelectedValue(name, scroll);
    }
    
    public void setAccountInfoTextArea(String s) {
        accountInfoTextArea.setText(s);
    }

    public void addAccountListListener(MouseListener listener) {
        accountList.addMouseListener(listener);
    }

    public void addCheckingRadioButtonListener(ActionListener listener) {
        checkingRadioButton.addActionListener(listener);
    }

    public void addSavingsRadioButtonListener(ActionListener listener) {
        savingsRadioButton.addActionListener(listener);
    }

    public void addBothRadioButtonListener(ActionListener listener) {
        bothRadioButton.addActionListener(listener);
    }

    public void addCreateAccountButtonListener(ActionListener listener) {
        createAccountButton.addActionListener(listener);
    }

    public void addWithdrawButtonListener(ActionListener listener) {
        withdrawButton.addActionListener(listener);
    }

    public void addDepositButtonListener(ActionListener listener) {
        depositButton.addActionListener(listener);
    }

    public void addExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    /**
     * Create user date input graphics for JOptionPane dialog box
     * @return JPanel containing JComboBox and JLabel components
     */
    public JPanel createDateInputPanel() {
        month = new JComboBox();
        for (int i = 0; i < months.length; i++) {
            month.addItem(months[i]);
        }
        day = new JComboBox();
        for (int i = 0; i < days.length; i++) {
            day.addItem(days[i]);
        }
        year = new JComboBox();
        for (int i = 0; i < years.length; i++) {
            year.addItem(years[i]);
        }
        JPanel dateInputPanel = new JPanel();
        dateInputPanel.add(month);
        month.setToolTipText("month");
        dateInputPanel.add(new JLabel("/"));
        dateInputPanel.add(day);
        day.setToolTipText("day");
        dateInputPanel.add(new JLabel("/"));
        dateInputPanel.add(year);
        year.setToolTipText("year");

        return dateInputPanel;
    }

    /**
     * Retrieves the day that the user's bank account was created
     * @return day value selected from JComboBox
     */
    public int getDay() {
        return (int)day.getSelectedItem();
    }

    /**
     * Retrieves the month that the user's bank account was created
     * @return month value selected from JComboBox
     */
    public int getMonth() {
        return (int)month.getSelectedItem();
    }

    /**
     * Retrieves the year that the user's bank account was created
     * @return year value selected from JComboBox
     */
    public int getyear() {
        return (int)year.getSelectedItem();
    }

    /* private fields */
    private final JPanel accountPanel;
    private final DefaultListModel listModel;
    private final JList accountList;
    private final JScrollPane listScroller;
    private final TitledBorder listOfAccountsTitle;
    private final TitledBorder accountInfoTitle;
    private final TitledBorder filterByTypeTitle;
    private final TitledBorder createAccountTitle;
    private final TitledBorder transactionTitle;
    private final JTextArea accountInfoTextArea;
    private final JPanel createAccountButtonPanel;
    private final JRadioButton checkingRadioButton;
    private final JRadioButton savingsRadioButton;
    private final JRadioButton bothRadioButton;
    private final ButtonGroup buttonGroup;
    private final JPanel radioButtonPanel;
    private final JButton createAccountButton;

    private final JPanel transactionPanel;
    private final JButton withdrawButton;
    private final JButton depositButton;
    private final JButton exitButton;
    private final JFrame frame;

    private final int FIELD_ROWS = 15;
    private final int FIELD_COLUMNS = 15;
    private final Dimension LIST_DIMENSIONS = new Dimension(255, 150);

    private JComboBox day;
    private JComboBox month;
    private JComboBox year;

    private final int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private final int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
        17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private final int[] years = {2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008,
        2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016};
}
