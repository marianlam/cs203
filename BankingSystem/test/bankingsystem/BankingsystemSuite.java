/*
 * This is the JUnit tester suite for the Banking System project.
 */
package bankingsystem;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Marian Lam
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({bankingsystem.ListOfAccountsTest.class,
    bankingsystem.CheckingAccountTest.class,
    bankingsystem.SavingsAccountTest.class})
public class BankingsystemSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
