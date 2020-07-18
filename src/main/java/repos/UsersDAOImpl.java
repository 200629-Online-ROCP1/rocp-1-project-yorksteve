package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import models.Users;
import models.Role;
import util.ConnectionUtil;

public class UsersDAOImpl implements UsersDAO 
{
	//Singleton
	private static UsersDAOImpl repo = new UsersDAOImpl();
	
	public UsersDAOImpl() {}
	
	public static UsersDAOImpl GetInstance()
	{
		return repo;
	}

	
	@Override
	public boolean insert(Users user) //This is registering a User
	{
		System.out.println("In Users users");
		
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			int index = 0;
			String sql = "INSERT INTO users(user_name, pass_word, first_name, last_name, email, role_fk"
						+ "VALUES(?,?,?,?,?,?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setObject(++index, user.getRole());
			statement.setString(++index, user.getEmail());
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return false;
	}
	
	
	
	@Override
	public Users findByUserId(int userId) 
	{
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM users WHERE user_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next())
			{
				return new Users(result.getInt("user_id"), result.getString("user_name"), 
								 result.getString("pass_word"), result.getString("first_name"),
								 result.getString("last_name"), result.getString("email"),
								 result.getRole("role_fk"));
				
				
			}
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}

	
	
	@Override
	public boolean updateUser(Users user) 
	{
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			int index = 0;
			String sql = "UPDATE * FROM users WHERE user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			
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
	public Set<Users> selectAll() 
	{
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM users;";
			PreparedStatement statement = conn.prepareStatement(sql);
			Set<Users> set = new HashSet<>();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) 
			{
				set.add(new Users(result.getInt("user_id"),
								  result.getString("user_name"), 
								  result.getString("pass_word"),
								  result.getString("first_name"), 
								  result.getString("last_name"),
								  result.getString("email"),
								  result.getRole("role_fk")));				
			}
			
			return set;
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}



}
