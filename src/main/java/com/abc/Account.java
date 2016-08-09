package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.TransactionRequiredException;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount,Date dt) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount,dt));
        }
    }

public void withdraw(double amount,Date dt) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount,dt));
    }
}

    public double interestEarned() {
        double amount = sumTransactions();
        double rt = new Double(0);
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
            	rt =rateForAccntType(MAXI_SAVINGS);
//                if (amount <= 1000)
//                    return amount * 0.02;
//                if (amount <= 2000)
//                    return 20 + (amount-1000) * 0.05;
//                return 70 + (amount-2000) * 0.1;
            	   return amount*rt;
            	
            	
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    private Double rateForAccntType(int acntType) {
    	Double amount = new Double(0);
        DateProvider today = new DateProvider();
        Date dt = today.now();
        Date trnsDt = new Date();
        if(acntType==MAXI_SAVINGS)
        {
        	 for (Transaction t: transactions)
        	 {
                // amount += t.amount;
                 trnsDt=t.getTransactionDate();
                 long diff = dt.getTime() - trnsDt.getTime();
                 long diffDays = diff / (24 * 60 * 60 * 1000);
                 if(diffDays>10)
                 {
                	// System.out.println("No trns in last 10 days");
                	 amount=0.05;
                 }else
                 {
                	 amount=0.001;
                 }
        	 }
        }
        return amount;
    }
    
    public int getAccountType() {
        return accountType;
    }

}
