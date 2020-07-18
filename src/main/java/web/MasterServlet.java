package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.AccountController;
import controllers.LoginController;
import controllers.UsersController;

public class MasterServlet extends HttpServlet 
{
	private static final ObjectMapper om = new ObjectMapper();
	private static final UsersController uc = new UsersController();
	private static final AccountController ac = new AccountController();
	private static final LoginController lc = new LoginController();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
	}

}
