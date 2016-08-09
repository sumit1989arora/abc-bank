package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
       Account MaxSvngAccount = new Account(Account.MAXI_SAVINGS);
       Date dt = new Date();
   	DateProvider ft = new DateProvider();
   	

        Customer henry = new Customer("Henry").openAccount(MaxSvngAccount);
        dt=ft.now();
        MaxSvngAccount.deposit(100.0,dt);
        MaxSvngAccount.deposit(4000.0,dt);
        MaxSvngAccount.withdraw(200.0,dt);

        assertEquals(1, henry.getNumberOfAccounts());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
