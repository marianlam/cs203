/*
 * This is the JUnit Test class for the ListOfAccount class.
 */
package bankingsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marian Lam
 */
public class ListOfAccountsTest {

    public ListOfAccountsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of createCheckingAccount method, of class ListOfAccounts.
     */
    @Test
    public void testCreateCheckingAccount() {
        System.out.println("* ListOfAccounts: testCreateCheckingAccount()");
        ListOfAccounts list = new ListOfAccounts();
        list.addAccount(list.createCheckingAccount("John Doe", new BigDecimal(30.00)));
        assertTrue(list.getSize() == 1);
    }

    /**
     * Test of createSavingsAccount method, of class ListOfAccounts.
     */
    @Test
    public void testCreateSavingsAccount() {
        System.out.println("* ListOfAccounts: testCreateSavingsAccount()");
        ListOfAccounts list = new ListOfAccounts();
        list.addAccount(list.createSavingsAccount("John Doe", new BigDecimal(30.00), 12, 1, 2015));
        assertTrue(list.getSize() == 1);
    }

    /**
     * Test of addAccount method, of class ListOfAccounts.
     */
    @Test
    public void testAddAccount() {
        System.out.println("* ListOfAccounts: testAddAccount()");
        ListOfAccounts list = new ListOfAccounts();

        list.addAccount(list.createSavingsAccount("John Doe", new BigDecimal(30.00), 12, 1, 2015));
        assertTrue(list.getSize() == 1);

        list.addAccount(list.createSavingsAccount("Jane Doe", new BigDecimal(30.00), 12, 1, 2015));
        assertTrue(list.getSize() == 2);

        list.addAccount(list.createSavingsAccount("James Doe", new BigDecimal(30.00), 12, 1, 2015));
        assertTrue(list.getSize() == 3);
    }

    /**
     * Test of findAccount method, of class ListOfAccounts.
     */
    @Test
    public void testFindAccount() {
        System.out.println("* ListOfAccounts: testFindAccount()");
        ListOfAccounts list = new ListOfAccounts();
        list.addAccount(list.createSavingsAccount("John Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("Jane Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("James Doe", new BigDecimal(30.00), 12, 1, 2015));

        assertTrue(list.findAccount("Jacob Doe") == null);
        assertTrue(list.findAccount("Jane Doe") != null);
        assertTrue(list.findAccount("Phylis Doe") == null);
        assertTrue(list.findAccount("James Doe") != null);
    }

    /**
     * Test of makeAccountListCopy method, of class ListOfAccounts.
     */
    @Test
    public void testMakeAccountListCopy() {
        System.out.println("* ListOfAccounts: testMakeAccountListCopy()");
        ListOfAccounts list = new ListOfAccounts();
        list.addAccount(list.createSavingsAccount("John Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("Jane Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("James Doe", new BigDecimal(30.00), 12, 1, 2015));
        ArrayList<Account> copy = list.makeAccountListCopy();

        for (int i = 0; i < list.getSize(); i++) {
            assertTrue(copy.get(i).equals(list.getElementAt(i)));
        }
    }

    /**
     * Test of sortByType method, of class ListOfAccounts.
     */
    @Test
    public void testSortByType() {
        System.out.println("* ListOfAccounts: testSortByType()");
        ListOfAccounts list = new ListOfAccounts();
        list.addAccount(list.createSavingsAccount("John Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("Jane Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("James Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createCheckingAccount("Jacob Doe", new BigDecimal(30.00)));
        list.addAccount(list.createCheckingAccount("Phylis Doe", new BigDecimal(30.00)));

        ArrayList<Account> listOfCheckingAccounts = list.sortByType("Checking");
        ArrayList<Account> listOfSavingsAccounts = list.sortByType("Savings");

        for (Account a : listOfCheckingAccounts) {
            assertTrue(CheckingAccount.class.isInstance(a));
        }

        for (Account a : listOfSavingsAccounts) {
            assertTrue(SavingsAccount.class.isInstance(a));
        }
    }

    /**
     * Test of withdraw method, of class ListOfAccounts.
     */
    @Test
    public void testWithdraw() {
        ListOfAccounts list = new ListOfAccounts();
        BigDecimal amount = new BigDecimal(30.00);
        Account account = list.createCheckingAccount("John Doe", amount);
        list.addAccount(account);
        list.withdraw("John Doe", 15);
        assertEquals(new BigDecimal(14.90).setScale(2, RoundingMode.HALF_EVEN),
                account.getBalance().setScale(2, RoundingMode.HALF_EVEN));
    }

    /**
     * Test of deposit method, of class ListOfAccounts.
     */
    @Test
    public void testDeposit() {
        System.out.println("* ListOfAccounts: testDeposit()");
        ListOfAccounts list = new ListOfAccounts();
        BigDecimal amount = new BigDecimal(30.00);
        Account account = list.createCheckingAccount("John Doe", amount);
        list.addAccount(account);
        list.deposit("John Doe", 30);
        assertEquals(new BigDecimal(60.00).setScale(2, RoundingMode.HALF_EVEN),
                account.getBalance().setScale(2, RoundingMode.HALF_EVEN));
    }
    
    /**
     * Test of getSize() method, of class ListOfAccounts.
     */
    @Test
    public void testGetSize() {
        System.out.println("* ListOfAccounts: testGetSize()");
        ListOfAccounts list = new ListOfAccounts();
        list.addAccount(list.createSavingsAccount("John Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("Jane Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("James Doe", new BigDecimal(30.00), 12, 1, 2015));
        assertEquals(3, list.getSize());
    }
    
    /**
     * Test getElementAt() method, of class ListOfAccounts.
     */
    @Test
    public void testGetElementAt() {
        System.out.println("* ListOfAccount: testGetElementAt()");
        ListOfAccounts list = new ListOfAccounts();
        list.addAccount(list.createSavingsAccount("John Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("Jane Doe", new BigDecimal(30.00), 12, 1, 2015));
        list.addAccount(list.createSavingsAccount("James Doe", new BigDecimal(30.00), 12, 1, 2015));
        
        assertTrue(list.getElementAt(0) != null);
        assertTrue(list.getElementAt(1) != null);
        assertTrue(list.getElementAt(2) != null);
    }
}
