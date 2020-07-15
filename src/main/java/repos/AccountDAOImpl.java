package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Account;
import util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO 
{
	
	// Singleton Form
	private static AccountDAOImpl repo = new AccountDAOImpl();
	private AccountDAOImpl() {}
	public static AccountDAOImpl GetInstance()
	{
		return repo;
	}

	
	
	@Override
	public boolean CreateAccount(Account account) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			String sql = "INSERT INTO accounts(account_id, account_balance, "
					+ "account_status_fk, account_type_fk) VALUES(?,?,?,?);";
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setInt(1, account.getAccountId());
			statement.setFloat(2, account.getBalance());
			statement.setString(3, account.getStatus());
			statement.setString(4, account.getType());
			
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
	public boolean UpdateAccount(Account account) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		
		return false;
	}

	
	
	@Override
	public Account GetAccountByID(int accountId) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM account WHERE account_id = ?;";
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setInt(1, accountId);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next())
			{
				return new Account(result.getInt("account_id"),
								   result.getFloat("account_balance"), 
								   result.getAccountStatus("account_status"),
								   result.getInt("account_type"));
			}
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}


	
	
	@Override
	public void DeleteAccount(int accountId) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			String sql = "DELETE account WHERE account_id = ?;";
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setInt(1, accountId);
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}

}
