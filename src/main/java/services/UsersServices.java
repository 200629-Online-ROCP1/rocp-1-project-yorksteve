package services;

import java.util.Set;

import models.Users;
import repos.UsersDAO;
import repos.UsersDAOImpl;

public class UsersServices 
{
	private final UsersDAO dao = new UsersDAOImpl();
	
	public Set<Users> selectAll()
	{
		return dao.selectAll();
	}
	
	public Users findById(int id)
	{
		return dao.findByUserId(id);
	}
	
	public boolean addUser(Users u)
	{
		Set<Users> set = selectAll();
		
		for (Users us: set)
		{
			if (us.getFirstName().equals(u.getFirstName()) && us.getLastName().equals(u.getLastName()))
			{
				return false;
			}
		}
		
		return dao.insert(u);
		
	}

	public boolean updateUser(Users u) 
	{
		return dao.updateUser(u);
	}

}
