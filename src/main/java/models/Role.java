package models;

public class Role 
{
	private int roleId;
	private String role;
	
	public Role() 
	{
		super();
	}
	
	public Role(String role) 
	{
		super();
		this.role = role;
	}
	
	public Role(int roleId, String role) 
	{
		super();
		this.roleId = roleId;
		this.role = role;
	}
}
