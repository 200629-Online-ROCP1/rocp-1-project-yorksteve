package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Account;
import models.AccountStatus;
import models.AccountType;
import models.Role;
import util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO 
{
	
	// Singleton Form
	private static AccountDAOImpl repo = new AccountDAOImpl();
	public AccountDAOImpl() {}
	public static AccountDAOImpl GetInstance()
	{
		return repo;
	}
	
	
	Account account = new Account();
	
	private static final AccountStatusDAO asdao = new AccountStatusDAOImpl();
	private static final AccountTypeDAO atdao = new AccountTypeDAOImpl();
	
	
	@Override
	public boolean createAccount(Account account) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			String sql = "INSERT INTO accounts(account_id, account_balance, "
					+ "account_status_fk, account_type_fk) VALUES(?,?,?,?);";
			
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setInt(1, account.getAccountId());
			statement.setFloat(2, account.getBalance());
			statement.setInt(3, account.getStatus().getStatusId());
			statement.setInt(4, account.getType().getTypeId());
			
			if (statement.execute()) 
			{
				return true;
			}
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return false;
	}

	
	
	@Override
	public boolean updateAccountById(Account account) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			int index = 0;
			
			String sql = "UPDATE accounts WHERE account_id = ?;";
			
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setFloat(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			
			if (statement.execute())
			{
				return true;
			}
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		
		return false;
	}

	
	
	@Override
	public Account getAccountByID(int accountId) 
	{
		
		
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM accounts WHERE account_id = ?;";
			
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setInt(1, accountId);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Account a = new Account();
				a.setAccountId(result.getInt("account_id"));
				a.setBalance(result.getFloat("account_balance"));
				
				if (result.getInt("account_status_fk") != 0)
				{
					AccountStatus as = asdao.findById(result.getInt("account_status_fk"));
					a.setStatus(as);
				}
				
				if (result.getInt("account_type_fk") != 0)
				{
					AccountType at = atdao.findById(result.getInt("account_type_fk"));
					a.setType(at);
				}
								
			}
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}


	
	
	@Override
	public void deleteAccount(int accountId) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			String sql = "DELETE accounts WHERE account_id = ?;";
			
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setInt(1, accountId);
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public float withdraw(float amount) 
	{
		float balance = account.balance;
		account.balance -= amount;
		if (account.balance < 0)
		{
			return balance;
		}
		
		else
		{
			return account.balance;
		}
	}

	@Override
	public float deposit(float amount) 
	{
		account.balance += amount;
		return account.balance;
	}
	
	@Override
	public float transfer(float amount) 
	{
		return account.balance;
	}
	
	@Override
	public AccountStatus getAccountStatusById(int id) 
	{
		AccountStatus accountStatus = asdao.findById(id);
		
		return accountStatus;
	}
	
	@Override
	public AccountType getAccountTypeById(int id) 
	{
		AccountType accountType = atdao.findById(id);
		
		return accountType;
	}

}
