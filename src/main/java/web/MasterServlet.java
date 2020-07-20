package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.AccountController;
import controllers.LoginController;
import controllers.UsersController;
import models.Account;

public class MasterServlet extends HttpServlet 
{
	private static final ObjectMapper om = new ObjectMapper();
	private static final UsersController uc = new UsersController();
	private static final AccountController ac = new AccountController();
	private static final LoginController lc = new LoginController();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("in doGet");
		caseClass(req, res);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("in doPost");
		caseClass(req, res);
	}
	
	protected void caseClass(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("application/json");
		// This will set the default response to not found; we will change it later if
		// the request was successful
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/bankingapi/", "");

		String[] portions = URI.split("/");

		HttpSession ses = req.getSession(false);
		
		try
		{
			switch (portions[0])
			{
			case "accounts":
				if (ses != null && (Boolean)ses.getAttribute("loggedin"))
				{
					
					
					if (req.getMethod().equals("GET"))
					{
						if (portions.length == 2)
						{
							if (roleId == 1 || roleId == 2)
							{
								// They should have access to every account
							}
							
							else if (roleId == 3);
							{
								// Only access to their account
							}
						}
						
					}
					
					else if (req.getMethod().equals("POST"))
					{
						if (roleId == 1 || roleId == 3)
						{
							// Change the account
						}
					}
					
					
				}
				
				else 
				{
					res.setStatus(401);
					res.getWriter().println("You must be logged in.");
				}
				
				break;
				
			case "users":
				if (ses != null && (Boolean)ses.getAttribute("loggedin"))
				{
					if (req.getMethod().equals("GET"))
					{
						if (roleId == 1 || roleId == 2)
						{
							// See all Users
						}
						
						else
						{
							// See only yourself
						}
					}
					
					else if (req.getMethod().equals("POST"))
					{
						if (roleId == 1)
						{
							// Change all Users
						}
						
						else 
						{
							// Change only yourself
						}
						
					}
				}
				
				else 
				{
					res.setStatus(401);
					res.getWriter().println("You must be logged in.");
				}
				
				break;
				
			case "login":
				lc.login(req, res);
				break;
				
			case "logout":
				lc.logout(req, res);
				break;
			}
		}
		
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
	}	

}
