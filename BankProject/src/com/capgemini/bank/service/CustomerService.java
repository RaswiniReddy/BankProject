package com.capgemini.bank.service;

import java.util.ArrayList;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.exception.CustomerException;

public interface CustomerService {
	public abstract long addAccount(Account a) throws CustomerException;
	public abstract float getBalance(long accountNum) throws CustomerException;
	public abstract String withdraw(long accNum,int amount) throws CustomerException;
	public abstract String deposit(long accNum,int amount) throws CustomerException;
	public abstract String fundTrasfer(long accNum, int amount, long accNum2);
	public abstract ArrayList<Transaction> showTransactions(long accNum);
}
