package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Role;
import util.ConnectionUtil;

public class RoleDAOImpl implements RoleDAO 
{
	
	//Singleton
	private static RoleDAOImpl self = new RoleDAOImpl();
	
	public RoleDAOImpl() {}
	
	public static RoleDAOImpl getInstance()
	{
		return self;
	}
	

	
	@Override
	public Role getRoleById(int roleId) 
	{
		try (Connection rconn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM roles WHERE role_id = ?;";
			PreparedStatement statement = rconn.prepareStatement(sql);
			statement.setInt(1, roleId);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next())
			{
				return new Role(result.getInt("role_id"), result.getString("role_"));
			}
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}

}
