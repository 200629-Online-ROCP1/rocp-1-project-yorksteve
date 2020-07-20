package services;

import models.Role;
import repos.RoleDAO;
import repos.RoleDAOImpl;

public class RoleService 
{
	private final RoleDAO rDao = new RoleDAOImpl();
	
	public Role getRoleById(int roleId)
	{
		return rDao.getRoleById(roleId);
	}

}
