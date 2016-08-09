package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

public class TransactionTest {
    @Test
    public void transaction() {
    	Date dt = new Date();
    	DateProvider ft = new DateProvider();
    	dt=ft.now();
        Transaction t = new Transaction(5,dt);
        assertTrue(t instanceof Transaction);
    }
}
