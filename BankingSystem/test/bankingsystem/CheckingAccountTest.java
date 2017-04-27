/*
 * This is the JUnit Test class for the CheckingAccount class.
 */
package bankingsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marian Lam
 */
public class CheckingAccountTest {
    
    public CheckingAccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of withdraw method, of class CheckingAccount.
     */
    @Test
    public void testWithdraw() {
        System.out.println("* CheckingAccount: testWithdraw()");
        BigDecimal amount = new BigDecimal(50.55);
        CheckingAccount account = new CheckingAccount("Checking", "John Doe", new BigDecimal(100.00));
        account.withdraw(amount);
        assertEquals(new BigDecimal(49.35).setScale(2, RoundingMode.HALF_EVEN), account.getBalance().setScale(2, RoundingMode.HALF_EVEN));
    }

    /**
     * Test of deposit method, of class CheckingAccount.
     */
    @Test
    public void testDeposit() {
        System.out.println("* CheckingAccount: testDeposit()");
        BigDecimal amount = new BigDecimal(30.50);
        CheckingAccount account = new CheckingAccount("Checking", "John Doe", new BigDecimal(100.00));
        account.deposit(amount);
        assertEquals(new BigDecimal(130.50).setScale(2, RoundingMode.HALF_EVEN), account.getBalance().setScale(2, RoundingMode.HALF_EVEN));
    }

    /**
     * Test of accountToString method, of class CheckingAccount.
     */
    @Test
    public void testAccountToString() {
        System.out.println("* CheckingAccount: testAccountToString()");
        CheckingAccount account = new CheckingAccount("Checking", "John Doe", new BigDecimal(100.00));
        String result = account.accountToString();
        assertTrue(result != null);
    }
    
}
