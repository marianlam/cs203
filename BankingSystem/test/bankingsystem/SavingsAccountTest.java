/*
 * This is the JUnit Test class for the SavingsAccount class.
 */
package bankingsystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marian Lam
 */
public class SavingsAccountTest {
    
    public SavingsAccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getBalance method, of class SavingsAccount.
     */
    @Test
    public void testGetBalance() {
        System.out.println("* SavingsAccount: testGetBalance()");
        SavingsAccount account = new SavingsAccount("Savings", "Jane Doe", new BigDecimal(100.00), 12, 1, 2015);
        BigDecimal result = account.getBalance();
        assertEquals(new BigDecimal(100.00).add(account.calculateInterest()), result);
    }

    /**
     * Test of withdraw method, of class SavingsAccount.
     */
    @Test
    public void testWithdraw() {
        System.out.println("* SavingsAccount: testWithdraw()");
        BigDecimal amount = new BigDecimal(50.00);
        SavingsAccount account = new SavingsAccount("Savings", "Jane Doe", new BigDecimal(100.00), 12, 1, 2015);
        account.withdraw(amount);
        assertEquals(amount.add(account.calculateInterest()), account.getBalance());
    }

    /**
     * Test of deposit method, of class SavingsAccount.
     */
    @Test
    public void testDeposit() {
        System.out.println("* SavingsAccount: testDeposit()");
        BigDecimal amount = new BigDecimal(50.00);
        SavingsAccount account = new SavingsAccount("Savings", "Jane Doe", new BigDecimal(100.00), 12, 1, 2015);
        account.deposit(amount);
        assertEquals(new BigDecimal(150.00).add(account.calculateInterest()), account.getBalance());
    }

    /**
     * Test of calculateInterest method, of class SavingsAccount.
     */
    @Test
    public void testCalculateInterest() {
        System.out.println("* SavingsAccount: testCalculateInterest()");
        SavingsAccount account = new SavingsAccount("Savings", "Jane Doe", new BigDecimal(100.00), 12, 1, 2015);
        BigDecimal actualInterest = account.calculateInterest();
        
        /* calculate expected interest */
        LocalDate date_created = LocalDate.of(2015, 12, 1);
        LocalDate today = LocalDate.now();
        long daysInBetween = ChronoUnit.DAYS.between(date_created, today);
        BigDecimal expectedInterest = new BigDecimal(daysInBetween).multiply(account.getInterestRate());
  
        assertEquals(expectedInterest, actualInterest);
    }
    
    /**
     * Test of getInterestRate method, of class SavingsAccount.
     */
    @Test
    public void testGetInterestRate() {
        System.out.println("* SavingsAccount: testGetInterestRate()");
        SavingsAccount account = new SavingsAccount("Savings", "Jane Doe", new BigDecimal(100.00), 12, 1, 2015);
        assertTrue(account.getInterestRate() != null);
    }

    /**
     * Test of accountToString method, of class SavingsAccount.
     */
    @Test
    public void testAccountToString() {
        System.out.println("* SavingsAccount: testAccountToString()");
        SavingsAccount account = new SavingsAccount("Savings", "Jane Doe", new BigDecimal(100.00), 12, 1, 2015);
        String result = account.accountToString();
        assertTrue(result != null);
    }
    
}
