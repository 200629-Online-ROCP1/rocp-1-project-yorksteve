package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.LoginDTO;
import models.Users;
import repos.UsersDAO;
import repos.UsersDAOImpl;

public class LoginService 
{
	public static final UsersDAO udao = new UsersDAOImpl();
	
	public boolean login(LoginDTO l, HttpServletRequest req, HttpServletResponse res)
	{
		Users u = udao.findByUserName(l.username);

		
		if (l.username.equals(u.getUsername()) && l.password.equals(u.getPassword()))
		{
			HttpSession ses = req.getSession();
			ses.setAttribute("user", u);
			ses.setAttribute("loggedin", true);
			return true;
		}
		
		
		return false;
	}

}
