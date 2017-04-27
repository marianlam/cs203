/*
 * This class represents the abstraction of an bank account which includes
 * fields such as type, name, and balance; checking and savings account
 * subclasses extend this class, thereby inheriting its public interface.
 * As an abstract class, Account cannot be instantiated.
 */
package bankingsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Marian Lam
 */
public abstract class Account {

    /* class constructor */
    public Account(String type, String name, BigDecimal balance) {
        this.TYPE = type;
        this.NAME = name;
        this.balance = balance;
    }

    /**
     * @return account type (either "checking" or "savings")
     */
    public String getType() {
        return TYPE;
    }

    /**
     * @return name of the account holder
     */
    public String getName() {
        return NAME;
    }

    /**
     * @return account balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param new_balance updated account balance
     */
    public void setBalance(BigDecimal new_balance) {
        balance = new_balance;
    }

    /**
     * @return String representation of account information
     */
    public String accountToString() {
        return "\n" + " Account Type: " + TYPE + "\n"
                + " Name: " + NAME + "\n"
                + " Balance: $" + getBalance().setScale(2, RoundingMode.HALF_EVEN) + "\n";
    }

    /* abstract methods */
    public abstract void withdraw(BigDecimal amount);
    public abstract void deposit(BigDecimal amount);

    /* private fields */
    private final String TYPE;
    private final String NAME;
    private BigDecimal balance;
}
