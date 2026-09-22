package exampletest;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.plugins.MockMaker;

import example.Account;
import example.Logger;


public class BankAccountTest {
	private Account account;
	private Logger mockLogger;
	
    @Before
    public void setUp() {
    	mockLogger = Mockito.mock(Logger.class);
    	account = new Account("123456789", mockLogger);
    	//account = new Account("123456789");
    }
    
    @After
    public void tearDown() {
    	//Tear down not needed in this case as we don't have any resources to clean up
    }
    
    @Test
    public void testDeposit() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance(), 0.001);
        account.printAccountInfo();
        Mockito.verify(mockLogger).log("Deposit of $100.0 to account 123456789");
    }

    @Test
    public void testWithdrawSufficientFunds() {
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance(), 0.001);
        account.printAccountInfo();
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        account.deposit(100.0);
        assertThrows(IllegalArgumentException.class, () -> {
        account.withdraw(150.0);
        });
        account.printAccountInfo();
    }

    @Test
    public void testDepositNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
        account.deposit(-100.0);
        });
        account.printAccountInfo();
    }

    @Test
    public void testWithdrawNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
        account.withdraw(-100.0);
        });
        account.printAccountInfo();
    }

    // edge cases
    @Test
    public void testWithdrawZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
        account.withdraw(0.0);
        });
        account.printAccountInfo();
    }
}

