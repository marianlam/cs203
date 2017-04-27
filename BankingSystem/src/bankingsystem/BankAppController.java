/*
 * This class acts upon both the model and view; it controls the data flow
 * into the model object and updates the view whenever data changes, thereby
 * keeping model and view separate.
 */
package bankingsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Marian Lam
 */
public class BankAppController {

    /* class constructor */
    public BankAppController() {
        model = new ListOfAccounts();
        view = new BankAppGUI();

        /* populate list with checking and savings accounts */
        model.addAccount(model.createCheckingAccount("Lily Makki", new BigDecimal(900.50)));
        model.addAccount(model.createSavingsAccount("Jane Brown", new BigDecimal(430.00), 12, 1, 2016));
        model.addAccount(model.createCheckingAccount("Herbert Castro", new BigDecimal(500.90)));
        model.addAccount(model.createSavingsAccount("Ming Chen", new BigDecimal(300.90), 5, 5, 2015));
        model.addAccount(model.createCheckingAccount("Alicia Gonzales", new BigDecimal(780.25)));
        model.addAccount(model.createCheckingAccount("John Anderson", new BigDecimal(840.75)));
        model.addAccount(model.createSavingsAccount("Michael Lopez", new BigDecimal(935.90), 8, 15, 2016));
        model.addAccount(model.createSavingsAccount("Sijun Zhang", new BigDecimal(500.90), 2, 25, 2014));

        /* initially display both account types */
        addToListModel(model.makeAccountListCopy());
        /* create and add component listeners */
        view.addCheckingRadioButtonListener(makeCheckingRadioButtonListener());
        view.addSavingsRadioButtonListener(makeSavingsRadioButtonListener());
        view.addBothRadioButtonListener(makeBothRadioButtonListener());
        view.addAccountListListener(makeAccountListListener());
        view.addCreateAccountButtonListener(makeCreateAccountButtonListener());
        view.addWithdrawButtonListener(makeWithdrawButtonListener());
        view.addDepositButtonListener(makeDepositButtonListener());
        view.addExitButtonListener(makeExitButtonListener());
        /* initially make transaction buttons uneditable */
        view.makeTransactionButtonsUneditable();
    }

    /**
     * Updates the view to display the names of each bank account stored in a list
     * @param list of bank accounts
     */
    private void addToListModel(ArrayList<Account> list) {
        view.clearListModel();
        for (Account a : list) {
            view.addElementToListModel(a.getName());
        }
    }

    /**
     * When CheckingRadioButton is clicked, the view will only show checking accounts
     * @return ActionListener object
     */
    private ActionListener makeCheckingRadioButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* clear account information display and selection */
                view.setAccountInfoTextArea("");
                view.clearListSelection();
                /* make transactions impossible when no account is selected */
                if (view.getListSelectedIndex() == -1) {
                    view.makeTransactionButtonsUneditable();
                }
                addToListModel(model.sortByType("Checking"));
            }
        };
    }

    /**
     * When SavingsRadioButton is clicked, the view will only show savings accounts
     * @return ActionListener object
     */
    private ActionListener makeSavingsRadioButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* clear account information display and selection */
                view.setAccountInfoTextArea("");
                view.clearListSelection();
                /* make transactions impossible when no account is selected */
                if (view.getListSelectedIndex() == -1) {
                    view.makeTransactionButtonsUneditable();
                }
                addToListModel(model.sortByType("Savings"));
            }
        };
    }

    /**
     * When BothRadioButton is clicked, the view will show both checking and 
     * savings accounts
     * @return ActionListener object
     */
    private ActionListener makeBothRadioButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* clear account information display and selection */
                view.setAccountInfoTextArea("");
                view.clearListSelection();
                /* make transactions impossible when no account is selected */
                if (view.getListSelectedIndex() == -1) {
                    view.makeTransactionButtonsUneditable();
                }
                ArrayList<Account> list = model.makeAccountListCopy();
                addToListModel(list);
            }
        };
    }

    /**
     * When an item in account list is selected, the view will display the
     * information unique to that account
     * @return ActionListener object
     */
    private MouseListener makeAccountListListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (view.getListSelectedIndex() != -1) {
                    /* once a list item is selected, make transactions possible */
                    view.makeTransactionButtonsEditable();
                    /* find the account and display its information */
                    String name = view.getListSelectedValue();
                    Account selectedAccount = model.findAccount(name);
                    view.setAccountInfoTextArea(selectedAccount.accountToString());
                }
            }
        };
    }

    /**
     * When CreateAccountButton is clicked, the user is prompted to provide
     * the necessary information to open a new bank account
     * @return ActionListener object
     * @invariant newAccount.getName() is unique; no two names are the same unless
     * the account types are different
     */
    private ActionListener makeCreateAccountButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(null, "Please enter your full name", "Create Account", JOptionPane.PLAIN_MESSAGE);
                /* if user does not exit or cancel out of dialog box */
                if (name != null) {
                    /* handles case where user does not input a name */
                    if (name.equals("")) {
                        JOptionPane.showConfirmDialog(null, "No name was entered", "Invalid Value", JOptionPane.OK_CANCEL_OPTION);
                        return;
                    }
                    /* otherwise continue prompting for more information */
                    String type = (String) JOptionPane.showInputDialog(null, "Select new account type", "Create Account", JOptionPane.PLAIN_MESSAGE, null, accounts, accounts[0]);
                    /* if user does not exit or cancel out of dialog box */
                    if (type != null) {
                        /* create new checking account and show receipt */
                        if (type.equals("Checking")) {
                            Account newAccount = model.createCheckingAccount(name, new BigDecimal(0));
                            model.addAccount(newAccount);
                            /* update view */
                            view.setAccountInfoTextArea(newAccount.accountToString());
                            view.addElementToListModel(newAccount.getName());
                            view.setListSelectedValue(newAccount.getName(), true);
                            JOptionPane.showConfirmDialog(null, "Checking account created", "Receipt", JOptionPane.PLAIN_MESSAGE);
                        }
                        if (type.equals("Savings")) {
                            /* create new savings account and show receipt */
                            int option = JOptionPane.showConfirmDialog(null, view.createDateInputPanel(), "Enter Date", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            /* if user exits or cancels dialog box, return */
                            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                                return;
                            }
                            int month = view.getMonth();
                            int day = view.getDay();
                            int year = view.getyear();
                            Account newAccount = model.createSavingsAccount(name, new BigDecimal(0), month, day, year);
                            model.addAccount(newAccount);
                            /* update view */
                            view.setAccountInfoTextArea(newAccount.accountToString());
                            view.addElementToListModel(newAccount.getName());
                            view.setListSelectedValue(newAccount.getName(), true);
                            JOptionPane.showConfirmDialog(null, "Savings account created", "Receipt", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
            }
        };
    }

    /**
     * When WithdrawButton is clicked, the user is allowed to withdraw a valid
     * amount from the selected bank account
     * @return ActionListener object
     */
    private ActionListener makeWithdrawButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getListSelectedValue();
                /* prompt for user input */
                String input = JOptionPane.showInputDialog(null, "Enter the amount you wish to withdraw", "Withdraw", JOptionPane.PLAIN_MESSAGE);
                /* if user does not exit or cancel out of dialog box */
                if (input != null) {
                    try {
                        Account a = model.findAccount(name);
                        /* try to convert String to double */
                        double amount = Double.parseDouble(input);
                        /* return if user inputs amount <= 0 or if amount > account balance */
                        if (amount <= 0 || a.getBalance().setScale(2, RoundingMode.HALF_EVEN).compareTo(new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN)) < 0
                            /* if it's a checking account, make sure amount > account balance + transaction fee */
                                || CheckingAccount.class.isInstance(a) && a.getBalance().setScale(2, RoundingMode.HALF_EVEN).compareTo(new BigDecimal(amount).add(TRANSACTION_FEE).setScale(2, RoundingMode.HALF_EVEN)) < 0) { 
                            JOptionPane.showConfirmDialog(null, "Invalid amount", "Withdraw", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                        model.withdraw(name, amount);
                    } catch (NumberFormatException n) {
                        JOptionPane.showConfirmDialog(null, "Invalid amount", "Withdraw", JOptionPane.OK_CANCEL_OPTION);
                        return;
                    }
                    view.setAccountInfoTextArea(model.findAccount(name).accountToString());
                    JOptionPane.showConfirmDialog(null, "Amount successfully withdrawn", "Receipt", JOptionPane.OK_CANCEL_OPTION);
                }
            }
        };
    }

    /**
    * When DepositButton is clicked, the user is allowed to deposit a valid
    * amount from the selected bank account
    * @return ActionListener object
    */
    private ActionListener makeDepositButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getListSelectedValue();
                /* prompt for user input */
                String input = JOptionPane.showInputDialog(null, "Enter the amount you wish to deposit", "Deposit", JOptionPane.PLAIN_MESSAGE);
                /* if user does not exit or cancel out of dialog box */
                if (input != null) {
                    try {
                        /* try to convert String to double */
                        double amount = Double.parseDouble(input);
                        /* return if user inputs amount <= 0 */
                        if (amount <= 0) {
                            JOptionPane.showConfirmDialog(null, "Invalid amount", "Deposit", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                        model.deposit(name, amount);
                    } catch (NumberFormatException n) {
                        JOptionPane.showConfirmDialog(null, "Invalid amount", "Deposit", JOptionPane.OK_CANCEL_OPTION);
                        return;
                    }
                    view.setAccountInfoTextArea(model.findAccount(name).accountToString());
                    JOptionPane.showConfirmDialog(null, "Amount successfully deposited", "Receipt", JOptionPane.OK_CANCEL_OPTION);
                }
            }
        };
    }

    /**
     * When ExitButton is clicked, the program is quitted
     * @return ActionListener object
     */
    private ActionListener makeExitButtonListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
    }

    /* private fields */
    private final ListOfAccounts model;
    private final BankAppGUI view;
    private final String[] accounts = {"Checking", "Savings"};
    private final BigDecimal TRANSACTION_FEE = new BigDecimal(0.10);
}