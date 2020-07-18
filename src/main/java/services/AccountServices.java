package services;

import models.Account;
import models.AccountStatus;
import models.AccountType;
import models.Users;
import repos.AccountDAO;
import repos.AccountDAOImpl;

public class AccountServices 
{
	private static final AccountDAO aDao = new AccountDAOImpl();
	
	public Account getAccountById(int id)
	{
		return aDao.getAccountByID(id);
	}
	
	public float withdraw(float amount)
	{
		return aDao.withdraw(amount);
	}
	
	public float deposit(float amount)
	{
		return aDao.deposit(amount);
	}
	
	public float transfer(float amount)
	{
		return aDao.transfer(amount);
	}
	
	public AccountStatus getAccountStatusById(int id)
	{
		return aDao.getAccountStatusById(id);
	}
	
	public AccountType getAccountTypeById(int id)
	{
		return aDao.getAccountTypeById(id);
	}

	public boolean createAccount(Account account)
	{
		return aDao.createAccount(account);
	}
	
	public boolean updateAccountById(Account account)
	{
		return aDao.updateAccountById(account);
	}

	

}
