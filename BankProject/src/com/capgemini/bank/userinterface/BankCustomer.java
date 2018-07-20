package com.capgemini.bank.userinterface;

import java.util.*;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.exception.CustomerException;
import com.capgemini.bank.service.CustomerService;
import com.capgemini.bank.service.CustomerServiceImpl;

public class BankCustomer {

	public static void main(String[] args) throws CustomerException {

		List myList;
		CustomerService service;

		{
			service = new CustomerServiceImpl();
		}
		Scanner sc = new Scanner(System.in);
		final int minBalance = 500;
		int choice;
		long accNum = 0;
		String ans;
		do {
			System.out.println("1.Add new bank account");
			System.out.println("2.Check balance in the bank account");
			System.out.println("3.Deposit money into the bank account");
			System.out.println("4.Withdraw money from bank account");
			System.out.println("5.Fund Trasfer to another bank");
			System.out.println("6.Show transactions");
			System.out.println("5.Exit");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Minimum balance to create an account is " + minBalance);
				System.out.println("Enter your firstname");
				String firstName = sc.next();
				System.out.println("Enter your lastname");
				String lastName = sc.next();
				Account a = new Account(firstName, lastName, minBalance);
				accNum = service.addAccount(a);
				System.out.println("Account Created and your Account Number is " + accNum + ". Thank You.");
				break;
			case 2:
				System.out.println("Current account balance: " + service.getBalance(accNum));
				break;
			case 3:
				System.out.println("Enter amount to deposit");
				int depositAmount = sc.nextInt();
				System.out.println(service.deposit(accNum, depositAmount));
				break;
			case 4:
				System.out.println("Enter amount to withdraw");
				int withdrawAmount = sc.nextInt();
				System.out.println(service.withdraw(accNum, withdrawAmount));
				break;
			case 5:
				System.out.println("Enter the amount you want to transfer");
				int amount = sc.nextInt();
				System.out.println("Enter the Acocunt number you want to trasfer");
				int accNum2 = sc.nextInt();
				System.out.println(service.fundTrasfer(accNum, amount, accNum2));
				break;
			case 6:
				ArrayList<Transaction> transactionArrayList = service.showTransactions(accNum);
				System.out.println("Transaction ID\tDate\t\tType\t\tAmount\tTransferred Account Number");
				System.out
						.println("----------------------------------------------------------------------------------");
				for (Transaction trasaction : transactionArrayList) {
					System.out.print(trasaction.getTransId() + "\t" + trasaction.getDate() + "\t" + trasaction.getType()
							+ "\t" + trasaction.getAmount() + "\t");
					if (trasaction.getType().equals("Fund Transfer")) {
						System.out.println(trasaction.getAccNum2());
					}
					System.out.println("");
				}
				break;
			case 7:
				System.exit(0);
			default:
				System.out.println("Invalid Choice");
			}
			System.out.println("Do you wish to continue? yes/no");
			ans = sc.next();
		} while ((ans.equals("yes")) || (ans.equals("YES")) || (ans.equals("y")) || (ans.equals("Y")));
	}

}
