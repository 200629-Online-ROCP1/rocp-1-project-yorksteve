package services;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.LoginDTO;
import repos.UsersDAO;
import repos.UsersDAOImpl;

public class LoginService 
{
	private static final UsersDAO udao = new UsersDAOImpl();
	
	public boolean login(LoginDTO l, HttpServletResponse res)
	{
		if (l.username.equals(udao.findByUserName(username)) && l.password.equals(udao.findByPassword(password)))
		{
			HttpSession ses = req.getSession();
			ses.setAttribute("user", udao);
			ses.setAttribute("loggedin", true);
			return true;
		}
		
		
		return false;
	}

}
