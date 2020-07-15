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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() 
	{
		return role;
	}

	public void setRole(String role) 
	{
		this.role = role;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + roleId;
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Role other = (Role) obj;
		
		if (roleId != other.roleId)
			return false;
		
		return true;
	}
}
