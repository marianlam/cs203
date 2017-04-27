/*
 * This class is an aggregation of Account objects; allows the management of
 * bank accounts via a list (enables searching, sorting, adding items, and
 * altering elements of that list).
 */
package bankingsystem;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Marian Lam
 */
public class ListOfAccounts {

    /* class constructor */
    public ListOfAccounts() {
        accountList = new ArrayList<>();
    }

    /**
     * Creates a new CheckingAccount object
     * @param name of account holder
     * @param balance initial account balance
     * @return new CheckingAccount
     */
    public Account createCheckingAccount(String name, BigDecimal balance) {
        return new CheckingAccount("Checking", name, balance);
    }

    /**
     * Creates a new SavingsAccount object
     * @param name of the account holder
     * @param balance initial account balance
     * @param month account was created
     * @param day account was created
     * @param year account was created
     * @return new SavingsAccount object
     */
    public Account createSavingsAccount(String name, BigDecimal balance, int month, int day, int year) {
        return new SavingsAccount("Savings", name, balance, month, day, year);
    }

    /**
     * Appends an Account object onto list of bank accounts
     * @param a Account object
     */
    public void addAccount(Account a) {
        accountList.add(a);
    }

    /**
     * Searches for a matching name in the list of bank accounts
     * @param name of the account holder
     * @return an Account object
     * @precondition accountList.size != 0
     */
    public Account findAccount(String name) {

        for (Account a : accountList) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Iterates through accountList and copies elements into a new list
     * @return a copy of accountList
     */
    public ArrayList<Account> makeAccountListCopy() {
        ArrayList<Account> newList = new ArrayList<>();
        for (Account a : accountList) {
            newList.add(a);
        }
        return newList;
    }

    /**
     * Creates a new list of solely either checking or savings accounts
     * @param type either "checking" or "savings" account
     * @return a list of either checking or savings accounts
     * @precondition accountList.size != 0
     */
    public ArrayList<Account> sortByType(String type) {
        ArrayList<Account> newList = new ArrayList<>();
        for (Account a : accountList) {
            if (a.getType().equals(type)) {
                newList.add(a);
            }
        }
        return newList;
    }

    /**
     * Withdraw specified amount from specified account in the list
     * @param name of account holder
     * @param amount to withdraw
     * @precondtion a.getBalance() >= amount
     */
    public void withdraw(String name, double amount) {
        Account a = findAccount(name);
        a.withdraw(new BigDecimal(amount));
    }

    /**
     * Deposit specified amount from specified account in the list
     * @param name of account holder
     * @param amount to deposit
     */
    public void deposit(String name, double amount) {
        Account a = findAccount(name);
        a.deposit(new BigDecimal(amount));
    }
    
    /**
     * @return size of accountList
     */
    public int getSize() {
        return accountList.size();
    }
    
    /**
     * Retrieves account from list at index i
     * @param index of element (starts at 0)
     * @return an account
     */
    public Account getElementAt(int index) {
        return accountList.get(index);
    }

    /* private field */
    private final ArrayList<Account> accountList;
}
