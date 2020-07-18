package controllers;

import java.util.Set;

import models.Users;
import services.UsersServices;

public class UsersController 
{
	private final UsersServices usS = new UsersServices();
	
	public Set<Users> selectAll()
	{
		return usS.selectAll();
	}
	
	public Users findById(int id)
	{
		return usS.findById(id);
	}
	
	public boolean addUser(Users u)
	{
		return usS.addUser(u);
	}

}
