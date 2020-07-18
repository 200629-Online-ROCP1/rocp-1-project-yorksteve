package services;

import models.LoginDTO;

public class LoginService 
{
	public boolean login(LoginDTO l)
	{
		if (l.username.equals() && l.password.equals())
		{
			return true;
		}
		
		return false;
	}

}
