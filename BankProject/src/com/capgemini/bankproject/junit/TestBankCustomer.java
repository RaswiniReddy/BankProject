package com.capgemini.bankproject.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.exception.CustomerException;
import com.capgemini.bank.service.CustomerService;
import com.capgemini.bank.service.CustomerServiceImpl;

class TestBankCustomer {

	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/
	private CustomerService service=new CustomerServiceImpl();
	
	@Test
	public void testAddAccount() throws CustomerException
	{
		Account account1=new Account("Raswini","Reddy",1000f);
		assertEquals(1000000004L, service.addAccount(account1));
	}
	
	@Test
	public void testDeposit() throws CustomerException
	{
		Account account1=new Account("Raswini","Reddy",1000f);
		account1.setAccountNum(service.addAccount(account1));
		assertEquals("Amount successfully deposited.", service.deposit(account1.getAccountNum(), 1000));
	}
	
	@Test
	public void testWithdraw() throws CustomerException
	{
		Account account1=new Account("Raswini","Reddy",1000f);
		account1.setAccountNum(service.addAccount(account1));
		service.deposit(account1.getAccountNum(), 1000);
		assertEquals("Amount successfully withdrawn.", service.withdraw(account1.getAccountNum(), 100));
	}
	
	@Test
	public void testFundTransfer() throws CustomerException
	{
		Account account1=new Account("Raswini","Reddy",1000f);
		account1.setAccountNum(service.addAccount(account1));
		Account account2=new Account("Raswini","Thummala",1000f);
		account2.setAccountNum(service.addAccount(account2));
		//System.out.println(account1.getAccountNum()+" "+account2.getAccountNum());
		assertEquals("Amount successfully transferred to 1000000002.", service.fundTrasfer(account1.getAccountNum(), 100, account2.getAccountNum()));
	}
}
