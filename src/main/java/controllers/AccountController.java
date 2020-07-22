package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Account;
import models.AccountStatus;
import models.AccountType;
import models.Transaction;
import services.AccountServices;

public class AccountController 
{
	public static final AccountServices as = new AccountServices();
	public static final ObjectMapper om = new ObjectMapper();
	
	public boolean withdraw(Transaction tAmount)
	{
		return as.withdraw(tAmount);
	}
	
	public boolean deposit(Transaction tAmount)
	{
		return as.deposit(tAmount);
	}
	
	public boolean transfer(Transaction tAmount)
	{
		return as.transfer(tAmount);
	}
	
	public AccountStatus getAccountStatusById(int id)
	{
		return as.getAccountStatusById(id);
	}
	
	public AccountType getAccountTypeById(int id)
	{
		return as.getAccountTypeById(id);
	}

	public boolean createAccount(Account account)
	{
		return as.createAccount(account);
	}
	
	public boolean updateAccountById(Account account)
	{
		return as.updateAccountById(account);
	}

	public Account getAccountById(int id) 
	{
		return as.getAccountById(id);
	}
	
	public Set<Account> findAll(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		return as.findAll();
	}
	
	public Transaction getTransAmount(HttpServletRequest req) throws IOException
	{
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null)
		{
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		Transaction amount = om.readValue(body, Transaction.class);
		
		return amount;
	}
	
	public boolean deleteAccount(Account account)
	{
		return as.deleteAccount(account);
	}

}
