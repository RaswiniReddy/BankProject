package com.capgemini.bank.dao;

import java.time.LocalDate;
import java.util.*;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.exception.CustomerException;

public class CustomerDaoImpl implements CustomerDao {

	static long accNum = 1000000000;
	static long transId = 1111111111;
	final int minBalance = 500;
	final int overDraftLimit = 5000000;
	private ArrayList<Account> list;

	{
		list = new ArrayList<Account>();
	}

	private ArrayList<Transaction> transaction;

	{
		transaction = new ArrayList<Transaction>();
	}
	Iterator<Account> iterator = list.iterator();

	@Override
	public long addAccount(Account a) throws CustomerException {
		try {
			accNum++;
			a.setAccountNum(accNum);
			a.setCreatedDate(LocalDate.now());
			list.add(a);

			return accNum;
		} catch (Exception e) {
			throw new CustomerException("Something went wrong", e);
		}
	}

	@Override
	public float getBalance(long accountNum) throws CustomerException {
		int flag = 0;
		try {
			Iterator<Account> i = list.iterator();

			// System.out.println(list.contains());

			while (i.hasNext()) {
				Account a = i.next();
				if (a.getAccountNum() == accountNum) {
					flag++;
					Transaction t = new Transaction(++transId, LocalDate.now(), "Balance Check", (int) a.getBalance(),
							accountNum, 0);
					transaction.add(t);
					return a.getBalance();
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		if (flag == 0) {
			throw new CustomerException("Invalid account number");
		}
		return 0;
	}

	@Override
	public String withdraw(long accNum, int amount) throws CustomerException {
		int flag = 0;
		try {
			Iterator<Account> i = list.iterator();
			while (i.hasNext()) {
				Account a = i.next();
				if (a.getAccountNum() == accNum) {
					flag++;
					// float tempBalance=a.getBalance();
					if (a.getBalance() - amount < minBalance) {
						throw new CustomerException("Denied. Low minimum Balance");
					} else {
						a.setBalance(a.getBalance() - amount);
						Transaction t = new Transaction(++transId, LocalDate.now(), "Credit/Withdraw", amount, accNum,
								0);
						transaction.add(t);
						return "Amount successfully withdrawn.";
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		if (flag == 0) {
			return "Invalid account number";
			// throw new CustomerException("Invalid account number");
		}
		return null;
	}

	@Override
	public String deposit(long accNum, int amount) throws CustomerException {
		int flag = 0;
		try {
			Iterator<Account> i = list.iterator();
			while (i.hasNext()) {
				Account a = i.next();
				if (a.getAccountNum() == accNum) {
					flag++;
					if (a.getBalance() + amount > overDraftLimit) {
						throw new CustomerException("Denied. Overdraft limit reached");
					} else {
						a.setBalance(a.getBalance() + amount);
						Transaction t = new Transaction(++transId, LocalDate.now(), "Debit/Deposit", amount, accNum, 0);
						transaction.add(t);
						return "Amount successfully deposited.";
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		if (flag == 0) {
			return "Invalid account number";
			// throw new CustomerException("Invalid account number");
		}
		return null;
	}

	@Override
	public String fundTrasfer(long accNum, int amount, long accNum2) {
		int flag1 = 0, flag2 = 0;
		try {
			Account a1 = new Account();
			Account a2 = new Account();
			Iterator<Account> i = list.iterator();
			while (i.hasNext()) {
				Account a = i.next();
				if (a.getAccountNum() == accNum) {
					flag1 = 1;
					a1 = a;
				} else if (a.getAccountNum() == accNum2) {
					flag2 = 1;
					a2 = a;
				}
			}
			if (flag1 == 1 && flag2 == 1) {
				if (a1.getBalance() - amount < minBalance) {
					throw new CustomerException("Denied. Low minimum Balance");
				} else {
					a1.setBalance(a1.getBalance() - amount);
					a2.setBalance(a2.getBalance() + amount);
					Transaction t = new Transaction(++transId, LocalDate.now(), "Fund Transfer", amount, accNum,
							accNum2);
					transaction.add(t);
					Transaction t2 = new Transaction(++transId, LocalDate.now(), "Fund Transfer", amount, accNum2,
							accNum);
					transaction.add(t2);
					return "Amount successfully transferred to " + accNum2 + ".";
				}
			} else if (flag1 == 0) {
				return "Invalid sender account number";
				// throw new CustomerException("Invalid account number");
			} else if (flag2 == 0) {
				return "invalid transfer accout number";
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		return null;
	}

	@Override
	public ArrayList<Transaction> showTransactions(long accNum) {
		ArrayList<Transaction> tList = new ArrayList<Transaction>();
		Iterator<Transaction> i = transaction.iterator();
		while (i.hasNext()) {
			Transaction t = i.next();
			if (t.getAccNum() == accNum) {
				tList.add(t);
			}
		}
		return tList;
	}
}
