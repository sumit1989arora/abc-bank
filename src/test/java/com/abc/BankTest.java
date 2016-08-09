package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    Date dt = new Date();
	DateProvider ft = new DateProvider();

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);
       
    	dt=ft.now();
        checkingAccount.deposit(100.0,dt);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        dt=ft.now();
        checkingAccount.deposit(1500.0,dt);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        dt=ft.now();
        checkingAccount.deposit(3000.0,dt);

        assertEquals(3.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    
    @Test
    public void maxi_savings_accountOldTransaction() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        dt=ft.now();
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -550);
        
        checkingAccount.deposit(3000.0,cal.getTime());
        

        assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void maxi_savings_accountOldCurrentTransaction() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        dt=ft.now();
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -550);
        
        checkingAccount.deposit(3000.0,cal.getTime());
        checkingAccount.deposit(3000.0,dt);

        assertEquals(6.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
