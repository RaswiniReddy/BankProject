package com.capgemini.bank.service;

import java.util.*;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.dao.CustomerDao;
import com.capgemini.bank.dao.CustomerDaoImpl;
import com.capgemini.bank.exception.CustomerException;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao serviceDao;

	// private ArrayList<Account> list;

	public CustomerServiceImpl() {
		serviceDao = new CustomerDaoImpl();
	}

	Scanner sc = new Scanner(System.in);

	private boolean validateFirstName(String str) {
		String pattern = "[A-Z][a-z]{3,50}";

		if (str.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validateLastName(String str) {
		String pattern = "[A-Z][a-z]+";

		if (str.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validateAmount(String amount) {
		String pattern = "[1-9][0-9]+";

		if (amount.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validateAccountNumber(String accountNumber) {
		String pattern = "[1-9][0-9]{9}";

		if (accountNumber.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public long addAccount(Account a) throws CustomerException {
		while (true) {
			String str = a.getFirstName();
			if (validateFirstName(str)) {
				break;
			} else {
				System.err.println("Wrong first name please enter again");
				a.setFirstName(sc.next());
			}
		}
		while (true) {
			String str = a.getLastName();
			if (validateLastName(str)) {
				break;
			} else {
				System.err.println("Wrong last name please enter again");
				a.setLastName(sc.next());
			}
		}
		long accNum = serviceDao.addAccount(a);
		return accNum;
	}

	@Override
	public float getBalance(long accountNum) throws CustomerException {
		while (true) {
			if (validateAccountNumber(String.valueOf(accountNum))) {
				break;
			} else {
				System.err.println("Incorrect account number please enter again");
				accountNum = sc.nextInt();
			}
		}
		float balance = serviceDao.getBalance(accountNum);
		return balance;
	}

	@Override
	public String withdraw(long accNum, int amount) throws CustomerException {
		while (true) {
			if (validateAccountNumber(String.valueOf(accNum))) {
				break;
			} else {
				System.err.println("Incorrect account number please enter again");
				amount = sc.nextInt();
			}
		}
		while (true) {
			if (validateAmount(String.valueOf(amount))) {
				break;
			} else {
				System.err.println("Incorrect amount please enter again");
				amount = sc.nextInt();
			}
		}
		String result = serviceDao.withdraw(accNum, amount);
		return result;
	}

	@Override
	public String deposit(long accNum, int amount) throws CustomerException {
		while (true) {
			if (validateAccountNumber(String.valueOf(accNum))) {
				break;
			} else {
				System.err.println("Incorrect account number please enter again");
				amount = sc.nextInt();
			}
		}
		while (true) {
			if (validateAmount(String.valueOf(amount))) {
				break;
			} else {
				System.err.println("Incorrect amount please enter again");
				amount = sc.nextInt();
			}
		}
		String result = serviceDao.deposit(accNum, amount);
		return result;
	}

	@Override
	public String fundTrasfer(long accNum, int amount, long accNum2) {
		while (true) {
			if (validateAccountNumber(String.valueOf(accNum))) {
				break;
			} else {
				System.err.println("Incorrect sender account number please enter again");
				amount = sc.nextInt();
			}
		}
		while (true) {
			if (validateAccountNumber(String.valueOf(accNum2))) {
				break;
			} else {
				System.err.println("Incorrect trasfer account number please enter again");
				amount = sc.nextInt();
			}
		}
		while (true) {
			if (validateAmount(String.valueOf(amount))) {
				break;
			} else {
				System.err.println("Incorrect amount please enter again");
				amount = sc.nextInt();
			}
		}
		if (accNum == accNum2) {
			return "Account numbers can not be same";
		} else {
			return serviceDao.fundTrasfer(accNum, amount, accNum2);
		}
	}

	@Override
	public ArrayList<Transaction> showTransactions(long accNum) {
		return serviceDao.showTransactions(accNum);
	}

}
