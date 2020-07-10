package models;

public class Account 
{
	private int accountId;
	private double balance;
	private AccountStatus status;
	private AccountType type;
	
	public Account() 
	{
		super();
	}

	public Account(int accountId, double balance, AccountStatus status, AccountType type) 
	{
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}

	public Account(double balance, AccountStatus status, AccountType type) 
	{
		super();
		this.balance = balance;
		this.status = status;
		this.type = type;
	}
}
