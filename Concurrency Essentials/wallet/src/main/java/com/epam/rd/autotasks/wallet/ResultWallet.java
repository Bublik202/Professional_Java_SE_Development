package com.epam.rd.autotasks.wallet;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResultWallet implements Wallet{
	private final List<Account> accounts;
	private final PaymentLog paymentLog;
	private final Lock lock;
	
	public ResultWallet(List<Account> accounts, PaymentLog paymentLog) {
	     this.accounts = accounts;
	     this.paymentLog = paymentLog;
	     this.lock = new ReentrantLock();
	}
	
	@Override
	public void pay(String recipient, long amount) throws Exception {		
		try {
			lock.lock();
			Account checkBalance = accounts.stream()
					.filter(t -> t.balance() >= amount)
					.findFirst()
					.orElseThrow(() -> new ShortageOfMoneyException(recipient, amount));
			
			checkBalance.pay(amount);
			paymentLog.add(checkBalance, recipient, amount);
			
		} finally {
			lock.unlock();
		}	
	}

}
