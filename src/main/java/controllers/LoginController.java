package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.LoginDTO;
import models.Users;
import services.LoginService;

public class LoginController 
{
	public static final LoginService ls = new LoginService();
	
	public void login(LoginDTO l2, HttpServletRequest req, HttpServletResponse res) throws IOException
	{
			
		if (ls.login(l2, req, res))
		{
			res.setStatus(200);
			res.getWriter().println("Login Successful!");
		}
			
		else
		{
			HttpSession ses = req.getSession(false);
			if (ses != null)
			{
				ses.invalidate();
			}
				
			res.setStatus(401);
			res.getWriter().println("Login Failed");
				
		}
		
		
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		HttpSession ses = req.getSession(false);
		
		if (ses != null)
		{
			Users l = (Users) ses.getAttribute("user");
			ses.invalidate();
			res.setStatus(200);
			res.getWriter().println("You logged out " + l.username);
		}
		
		else
		{
			res.setStatus(400);
			res.getWriter().println("You must be logged in to log out.");
		}
		
	}

}
