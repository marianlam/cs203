/*
 * This is the class that represents a checking account; it inherits abstract 
 * and concrete methods from the abstract Account class, implementing and 
 * overriding those methods. This class also contains additional data, such as
 * the transaction fee.
 */
package bankingsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Marian Lam
 */
public class CheckingAccount extends Account {
    
    /* class constructor */
    public CheckingAccount(String type, String name, BigDecimal balance) {
        super(type, name, balance);
    }
    
    /**
     * Subtracts withdrawal amount + transaction fee from checking account balance
     * @param amount to withdraw
     * @precondition amount > 0 && getBalance() > 0
     */
    @Override
    public void withdraw(BigDecimal amount) {
        setBalance(getBalance().subtract(amount.add(TRANSACTION_FEE)));
    }
    
    /**
     * Adds deposit amount to checking account balance
     * @param amount to deposit
     * @precondition amount > 0
     * @postcondition getBalance() > 0
     */
    @Override
    public void deposit(BigDecimal amount) {
        setBalance(getBalance().add(amount));
    }
    
    /**
     * @return transaction fee
     */
    public BigDecimal getTransactionFee() {
        return TRANSACTION_FEE.setScale(2, RoundingMode.HALF_EVEN);
    }
    
    /**
     * @return String representation of checking account information
     */
    @Override
    public String accountToString() {
        return super.accountToString() 
                + " Transaction Fee: $" + getTransactionFee().toString() + "\n"
                + "\n << withdrawals require \n"
                + "       a $" + getTransactionFee().toString() + " transaction \n"
                + "       fee >>";     
    }
    
    /* private field */
    private final BigDecimal TRANSACTION_FEE = new BigDecimal(0.10);
}
