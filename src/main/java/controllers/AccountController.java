package controllers;

import models.Account;
import models.AccountStatus;
import models.AccountType;
import services.AccountServices;

public class AccountController 
{
	public static final AccountServices as = new AccountServices();
	
	public float withdraw(float amount)
	{
		return as.withdraw(amount);
	}
	
	public float deposit(float amount)
	{
		return as.deposit(amount);
	}
	
	public float transfer(float amount)
	{
		return as.transfer(amount);
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

}
