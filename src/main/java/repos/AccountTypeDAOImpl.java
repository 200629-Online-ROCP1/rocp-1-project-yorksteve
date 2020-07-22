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
	public AccountType findById(int id) 
	{
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM accountstatus WHERE type_id = ?";
			
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
