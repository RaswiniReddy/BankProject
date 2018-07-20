package com.capgemini.bank.bean;

import java.time.LocalDate;

public class Transaction {
	
	long transId;
	LocalDate date;
	String type;
	int amount;
	long accNum;
	long accNum2;
	public Transaction(long transId, LocalDate date, String type, int amount, long accNum, long accNum2) {
		super();
		this.transId = transId;
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.accNum = accNum;
		this.accNum2 = accNum2;
	}
	public long getTransId() {
		return transId;
	}
	public void setTransId(long transId) {
		this.transId = transId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getAccNum() {
		return accNum;
	}
	public void setAccNum(long accNum) {
		this.accNum = accNum;
	}
	public long getAccNum2() {
		return accNum2;
	}
	public void setAccNum2(long accNum2) {
		this.accNum2 = accNum2;
	}
	@Override
	public String toString() {
		return "transId=" + transId + ", date=" + date + ", type=" + type + ", amount=" + amount
				+ ", accNum=" + accNum + ", accNum2=" + accNum2 + "\n";
	}
	
}
