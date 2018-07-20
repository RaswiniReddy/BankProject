package com.capgemini.bank.bean;

import java.time.LocalDate;

public class Account {
	long accountNum;
	String firstName;
	String lastName;
	float balance;
	LocalDate createdDate;

	public Account(String firstName, String lastName, float balance) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}

	public Account() {
		super();
	}

	public long getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(long accountNum) {
		this.accountNum = accountNum;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "accountNum=" + accountNum + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", balance=" + balance + ", createdDate=" + createdDate + "\n";
	}
}
