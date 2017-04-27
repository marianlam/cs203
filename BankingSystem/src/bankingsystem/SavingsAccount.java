/*
 * This is the class that represents a savings account; it inherits abstract 
 * and concrete methods from the abstract Account class, implementing and 
 * overriding those methods. This class also contains additional data, such as
 * the date the savings account was created, the present date, and a daily
 * interest rate.
 */
package bankingsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Marian Lam
 */
public class SavingsAccount extends Account {

    /* class constructor */
    public SavingsAccount(String type, String name, BigDecimal balance, int month, int day, int year) {
        super(type, name, balance);
        DATE_CREATED = LocalDate.of(year, month, day);
    }

    /**
     * adds calculated interest into the current account balance
     * @return savings account balance with interest included
     */
    @Override
    public BigDecimal getBalance() {
        return super.getBalance().add(calculateInterest());
    }
    
    /**
     * Subtracts withdrawal amount from savings account balance
     * @param amount to withdraw
     * @precondition amount > 0 && getBalance() > 0
     */
    @Override
    public void withdraw(BigDecimal amount) {
        setBalance(super.getBalance().subtract(amount));
    }

    /**
     * Adds deposit amount to savings account balance
     * @param amount to deposit
     * @precondition amount > 0
     * @postcondition getBalance() > 0
     */
    @Override
    public void deposit(BigDecimal amount) {
        setBalance(super.getBalance().add(amount));
    }

    /**
     * Calculates the interest earned by taking the difference of days between
     * the present and the day the account was created and multiplying that
     * number by the daily interest rate
     * @return amount of interest earned
     */
    public BigDecimal calculateInterest() {
        long daysInBetween = ChronoUnit.DAYS.between(DATE_CREATED, today);
        return new BigDecimal(daysInBetween).multiply(DAILY_INTEREST_RATE);
    }
    
    /**
     * @return BigDecimal object that represents the interest rate
     */
    public BigDecimal getInterestRate() {
        return DAILY_INTEREST_RATE;
    }

    /**
     * @return String representation of savings account information
     */
    @Override
    public String accountToString() {
        return super.accountToString() + " Date Created: " + DATE_CREATED.format(formatter) + "\n"
                + " Interest Earned: $" + calculateInterest().setScale(2, RoundingMode.HALF_EVEN);
    }

    /* private fields */
    private final LocalDate DATE_CREATED;
    private final LocalDate today = LocalDate.now();
    private final BigDecimal DAILY_INTEREST_RATE = new BigDecimal(0.01);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/u");
}
