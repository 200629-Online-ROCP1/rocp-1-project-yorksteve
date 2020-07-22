package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import models.AccountStatus;
import util.ConnectionUtil;

public class AccountStatusDAOImpl implements AccountStatusDAO 
{
	// Singleton Form
	private static AccountStatusDAOImpl repo = new AccountStatusDAOImpl();
	
	public AccountStatusDAOImpl() {}
	
	public static AccountStatusDAOImpl getInstance()
	{
		return repo;
	}

	@Override
	public boolean updateStatus(AccountStatus accountStatus) 
	{
		try (Connection sconn = ConnectionUtil.GetConnection())
		{
			String sql = "UPDATE accountstatus(account_status) WHERE status_id = ?;";
			
			PreparedStatement statement = sconn.prepareStatement(sql);
			statement.setInt(1, accountStatus.getStatusId());
			statement.setString(2, accountStatus.getStatus());
			
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
	public AccountStatus findById(int id) 
	{
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM accountstatus WHERE status_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) 
			{
				return new AccountStatus(result.getInt("status_id"), result.getString("account_status"));
			}
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}

}
