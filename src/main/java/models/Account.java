package models;

public class Account 
{
	private int accountId;
	private float balance;
	private AccountStatus status;
	private AccountType type;
	
	public Account() 
	{
		super();
	}

	public Account(int accountId, float balance, AccountStatus status, AccountType type) 
	{
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}

	public Account(float balance, AccountStatus status, AccountType type) 
	{
		super();
		this.balance = balance;
		this.status = status;
		this.type = type;
	}
	

	public int getAccountId() 
	{
		return accountId;
	}

	public void setAccountId(int accountId) 
	{
		this.accountId = accountId;
	}

	public float getBalance() 
	{
		return balance;
	}

	public void setBalance(float balance) 
	{
		this.balance = balance;
	}

	public AccountStatus getStatus() 
	{
		return status;
	}

	public void setStatus(AccountStatus status) 
	{
		this.status = status;
	}

	public AccountType getType() 
	{
		return type;
	}

	public void setType(AccountType type) 
	{
		this.type = type;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
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
		
		Account other = (Account) obj;
		
		if (accountId != other.accountId)
			return false;
		
		return true;
	}


	
	
	
}
