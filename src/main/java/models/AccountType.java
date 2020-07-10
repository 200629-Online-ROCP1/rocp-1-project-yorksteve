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
}
