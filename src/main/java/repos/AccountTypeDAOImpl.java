package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import models.AccountStatus;
import models.AccountType;
import util.ConnectionUtil;

public class AccountTypeDAOImpl implements AccountTypeDAO 
{
	//Singleton Form
	private static AccountTypeDAOImpl self = new AccountTypeDAOImpl();
	public AccountTypeDAOImpl() {}
	public static AccountTypeDAOImpl getInstance()
	{
		return self;
	}
	

	@Override
	public boolean insert(AccountType accountType) 
	{
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			String sql = "INSERT INTO accouttype(account_type) VALUES(?);";
			PreparedStatement statement = conn.prepareStatement(sql);
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
	public boolean insertStatement(AccountType accountType) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AccountType findById(int id) 
	{
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM accountstatus WHERE status_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) 
			{
				return new AccountType(result.getInt("type_id"), result.getString("account_type"));
			}
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}

	@Override
	public Set<AccountType> selectAll() 
	{
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM accounttype;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			Set<AccountType> set = new HashSet<>();
			
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next())
			{
				set.add(new AccountType(result.getInt("type_id"), result.getString("account_type")));
			}
			
			return set;
			
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}
	
	@Override
	public boolean updateType(AccountType accountType) 
	{
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			String sql = "UPDATE accounttype(account_type) WHERE type_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, accountType.getTypeId());
			statement.setString(2, accountType.getType());
			
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

}
