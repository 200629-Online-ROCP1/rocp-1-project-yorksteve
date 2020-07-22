package models;

public class Account 
{
	private int accountId;
	public float balance;
	private AccountStatus status;
	private AccountType type;
	private Users userId;
	
	public Account() 
	{
		super();
	}

	public Account(int accountId, float balance, AccountStatus status, AccountType type, Users userId) 
	{
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.status = status;
		this.type = type;
		this.userId = userId;
	}

	public Account(float balance, AccountStatus status, AccountType type, Users userId) 
	{
		super();
		this.balance = balance;
		this.status = status;
		this.type = type;
		this.userId = userId;
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

	public Users getUserId() 
	{
		return userId;
	}

	public void setUserId(Users userId) 
	{
		this.userId = userId;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		
		if (status == null) 
		{
			if (other.status != null)
				return false;
		}
		
		else if (!status.equals(other.status))
			return false;
		
		if (type == null) 
		{
			if (other.type != null)
				return false;
		} 
		
		else if (!type.equals(other.type))
			return false;
		
		if (userId == null) 
		{
			if (other.userId != null)
				return false;
		}
		
		else if (!userId.equals(other.userId))
			return false;
		
		return true;
	}
}
