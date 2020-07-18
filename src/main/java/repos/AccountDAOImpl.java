package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Account;
import models.AccountStatus;
import models.AccountType;
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

	
	AccountTypeDAOImpl typeDAO = AccountTypeDAOImpl.getInstance();
	AccountStatusDAOImpl statusDAO = AccountStatusDAOImpl.getInstance();
	Account account = new Account();
	
	
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
			
			if (result.next())
			{
				Account a = new Account();
				a.setAccountId(result.getInt("account_id"));
				a.setBalance(result.getFloat("account_balance")); 
				a.setStatus(result.getAccountStatus("account_status_fk"));
				a.setType(result.getInt("account_type_fk"));
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
		account.balance -= amount;
		return account.balance;
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
		AccountStatus accountStatus = statusDAO.findById(id);
		
		return accountStatus;
	}
	
	@Override
	public AccountType getAccountTypeById(int id) 
	{
		AccountType accountType = typeDAO.findById(id);
		
		return accountType;
	}

}
