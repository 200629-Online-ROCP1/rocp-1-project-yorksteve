package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import models.AccountType;
import util.ConnectionUtil;

public class AccountTypeDAOImpl implements AccountTypeDAO 
{
	//Singleton Form
	private static AccountTypeDAOImpl self = new AccountTypeDAOImpl();
	private AccountTypeDAOImpl() {}
	public static AccountTypeDAOImpl getInstance()
	{
		return self;
	}
	

	@Override
	public boolean Insert(AccountType accountType) 
	{
		try (Connection atconn = ConnectionUtil.GetConnection())
		{
			String sql = "INSERT INTO accouttype(account_type) VALUES(?);";
			PreparedStatement statement = atconn.prepareStatement(sql);
			statement.setString(1, accountType.getType());
			
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
	public boolean InsertStatement(AccountType accountType) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AccountType FindByFirstName(String firstName) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<AccountType> SelectAll() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean UpdateType(AccountType accountType) 
	{
		try (Connection atconn = ConnectionUtil.GetConnection())
		{
			String sql = "UPDATE accounttype(account_type) WHERE type_id = ?;";
			PreparedStatement statement = atconn.prepareStatement(sql);
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return false;
	}

}
