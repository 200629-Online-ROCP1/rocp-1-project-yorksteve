package models;

public class AccountType 
{
	private int typeId;
	private String type;
	
	public AccountType() 
	{
		super();
	}
		
	public AccountType(int typeId, String type) 
	{
		super();
		this.typeId = typeId;
		this.type = type;
	}

	public AccountType(String type) 
	{
		super();
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public int getTypeId() 
	{
		return typeId;
	}

	public void setTypeId(int typeId) 
	{
		this.typeId = typeId;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + typeId;
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
		
		AccountType other = (AccountType) obj;
		
		if (typeId != other.typeId)
			return false;
		
		return true;
	}

	
	
	
}
