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
import models.LoginDTO;
import models.Transaction;
import models.Users;

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
		res.setContentType("application/json");
		// This will set the default response to not found; we will change it later if
		// the request was successful
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/bankingapi/", "");

		String[] portions = URI.split("/");
		
		HttpSession ses = req.getSession(false);
		
		int roleId = 0;
		int accountId = 0;
		
		if (ses != null)
		{
			roleId = (int) ses.getAttribute("role_fk");
			accountId = (int) ses.getAttribute("account_fk");
		}
		
		
			try
			{
				switch (portions[0])
				{
							
				case "users":
					
					if (portions.length == 1 && ses != null && (Boolean) ses.getAttribute("loggedin"))
					{
						BufferedReader reader = req.getReader();
					
						StringBuilder s = new StringBuilder();
					
						String line = reader.readLine();
					
						while (line != null)
						{
							s.append(line);
							line = reader.readLine();
						}
					
						String body = new String(s);
					
						Users u = om.readValue(body, Users.class);
					
						if (portions.length == 1)
						{
							if (roleId == 1 || roleId == 2)
							{
								// See all Users
								uc.selectAll();
							}
							
							else
							{
								res.setStatus(401);
							}
						}
						
						else
						{
							int id = Integer.parseInt(portions[1]);
							//uc.findById(u.getUserID());
							uc.findById(id);
						}
					}
				
					break;
				
				case "accounts":
					if (portions.length == 1)
					{
						if (roleId == 1 || roleId == 2)
						{
							// Admin and Employees can view all Accounts
							ac.findAll(req, res);
						}
					
						else 
						{
							res.setStatus(401);
						}
					}
				
					else if (portions.length == 2)
					{
						ac.getAccountById(accountId);
					}
				
					else
					{
						int id = Integer.parseInt(portions[2]);
					
						if (portions[1].equals("status"))
						{
							ac.getAccountStatusById(id);
						}
					
						if (portions[1].equals("owner"))
						{
							
						}
					}
					break;
				}
			}
		
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
		
		System.out.println("Bottom of doGet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("in doPost");
		res.setContentType("application/json");
		// This will set the default response to not found; we will change it later if
		// the request was successful
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/bankingapi/", "");

		String[] portions = URI.split("/");
		
		HttpSession ses = req.getSession(false);
		
		int roleId = 0;
		int accountId = 0;
		int statusId = 0;
		
	
		
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null)
		{
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		try
		{
			if (ses != null)
			{
				roleId = (int) ses.getAttribute("role_fk");
				accountId = (int) ses.getAttribute("account_fk");
				statusId = (int) ses.getAttribute("account_status_fk");
			}
			
			switch (portions[0])
			{
			case "accounts":
				
				Account a = om.readValue(body, Account.class);
				ac.createAccount(a);
				ac.getAccountStatusById(accountId).setStatusId(1);
				
				if (a.getStatus().equals(ses.getAttribute("Open")))
				{
					Transaction amount = ac.getTransAmount(req);
					
					switch (portions[2])
					{
					
					case "withdraw":
						ac.withdraw(amount);
						break;
					
					case "deposit":
						ac.deposit(amount);
						break;
					
					case "transfer":
						ac.transfer(amount);
						break;
					case "delete":
						ac.deleteAccount(a);
						break;
					}
				}
				
				else
				{
					System.out.println("Account is not approved.");
					res.setStatus(401);
				}
				
				break;
				
			case "register":
				Users u = om.readValue(body, Users.class);
				if (roleId == 1)
				{
					uc.addUser(u);
					res.setStatus(201);
				}	
				
				else
				{
					res.setStatus(401);
					System.out.println("Must be Admin");
				}
				
				break;
				
			case "login":
				LoginDTO l = om.readValue(body, LoginDTO.class);
				lc.login(l, req, res);
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
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("in doPut");
		res.setContentType("application/json");
		// This will set the default response to not found; we will change it later if
		// the request was successful
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/bankingapi/", "");

		String[] portions = URI.split("/");
		
		HttpSession ses = req.getSession(false);
		
		int roleId = 0;
		int accountId = 0;
		
		if (ses != null)
		{
			roleId = (int) ses.getAttribute("role_fk");
			accountId = (int) ses.getAttribute("account_fk");
		}
		
		Account a = new Account();
		Users u = new Users();
		Users currentUser = (Users) ses.getAttribute("user");
		
		
		if (ses != null && (Boolean)ses.getAttribute("loggedin"))
		{
			switch (portions[1])
			{
			case "users":
				if (roleId == 1 || currentUser.getUserID() == (u.getUserID()))
					uc.updateUser(u);
				break;
			case "accounts":
				if (roleId == 1)
				{
					ac.updateAccountById(a);
				}
				break;
			}
		}
	
		else
		{
			
			res.setStatus(401);
			res.getWriter().println("You must be logged in.");
		}
	}
}
