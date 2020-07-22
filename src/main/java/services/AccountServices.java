package services;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.AccountStatus;
import models.AccountType;
import models.Transaction;
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
	
	public boolean withdraw(Transaction tAmount)
	{
		return aDao.withdraw(aDao.getAccountByID(Transaction.accountSourceId), tAmount.getAmount());
	}
	
	public boolean deposit(Transaction tAmount)
	{
		return aDao.deposit(aDao.getAccountByID(Transaction.accountSourceId), tAmount.getAmount());
	}
	
	public boolean transfer(Transaction tAmount)
	{
		if (aDao.withdraw(aDao.getAccountByID(Transaction.accountSourceId), tAmount.getAmount()))
		{
			if (aDao.deposit(aDao.getAccountByID(Transaction.accountTargetId), tAmount.getAmount()))
			{
				System.out.println("$" + tAmount + " has been transferred from Account #" + Transaction.accountSourceId
									+ " to Account #" + Transaction.accountTargetId);
				return true;
			}
			
			else
			{
				aDao.deposit(aDao.getAccountByID(Transaction.accountSourceId), tAmount.getAmount());
				return false;
			}
		}
		
		else
		{
			return false;
		}
		
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
	
	public Set<Account> findAll()
	{
		return aDao.findAll();
	}

	public boolean deleteAccount(Account account)
	{
		return aDao.deleteAccount(account);
	}

}
