package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import controllers.ConnectionUtil;
import models.Users;

public class UsersDAOImpl implements UsersDAO 
{
	
	private static UsersDAOImpl repo = new UsersDAOImpl();
	
	private UsersDAOImpl() {}
	
	public static UsersDAOImpl GetInstance()
	{
		return repo;
	}

	@Override
	public boolean Insert(Users user) 
	{
		System.out.println("In Users users");
		
		try (Connection conn = ConnectionUtil.GetConnection())
		{
			int index = 0;
			String sql = "INSERT INTO users(user_name, pass_word, first_name, last_name, email, role_fk"
						+ "VALUES(?,?,?,?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++index, users.getFirstName());
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return false;
	}

	@Override
	public boolean InsertStatement(Users user) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Users FindByFirstName(String firstName) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Users> SelectAll() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
