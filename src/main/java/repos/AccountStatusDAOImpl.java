package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import models.AccountStatus;
import util.ConnectionUtil;

public class AccountStatusDAOImpl implements AccountStatusDAO 
{
	// Singleton Form
	private static AccountStatusDAOImpl repo = new AccountStatusDAOImpl();
	private AccountStatusDAOImpl() {}
	public static AccountStatusDAOImpl GetInstance()
	{
		return repo;
	}

	
	
	@Override
	public boolean Insert(AccountStatus accountStatus) 
	{
		try (Connection sconn = ConnectionUtil.GetConnection())
		{
			String sql = "INSERT INTO accountstatus(account_status) VALUES(?);";
			PreparedStatement statement = sconn.prepareStatement(sql);
			statement.setString(1, accountStatus.getStatus());
			
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
	public boolean UpdateStatus(AccountStatus accountStatus) 
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
	public AccountStatus FindByFirstName(String firstName) 
	{
		return null;
	}

	@Override
	public Set<AccountStatus> SelectAll() 
	{
		return null;
	}

}
