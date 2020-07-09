package models;

public class Role 
{
	private int roleId;
	private String role;

	private void StandardRole()
	{
		role = "Standard";
		//Access Users
		//Access Account
		//Access AccountType
	}
	
	private void EmployeeRole()
	{
		StandardRole();
		role = "Employee";
		//View Users for all
	}
	
	private void AdminRole()
	{
		StandardRole();
		role = "Admin";
		//Access Users for all
	}
	
}
