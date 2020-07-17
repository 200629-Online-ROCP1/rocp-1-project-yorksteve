package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Role;
import util.ConnectionUtil;

public class RoleDAOImpl implements RoleDAO 
{
	
	//Singleton
	private static RoleDAOImpl self = new RoleDAOImpl();
	
	private RoleDAOImpl() {}
	
	public static RoleDAOImpl getInstance()
	{
		return self;
	}
	

	
	@Override
	public Role getRoleById(int roleId) 
	{
		try (Connection rconn = ConnectionUtil.GetConnection())
		{
			String sql = "INSERT INTO roles(role_id, role_)" + "VALUES(?,?);";
			PreparedStatement statement = rconn.prepareStatement(sql);
			statement.setInt(1, roleId.getRoleId());
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}

}
