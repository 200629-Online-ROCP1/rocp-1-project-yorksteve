package models;

public class Transaction 
{
	public float amount;
	public static int accountSourceId;
	public static int accountTargetId;
	
	
	public Transaction() 
	{
		super();
	}


	public Transaction(float amount, int accountSourceId, int accountTargetId) 
	{
		super();
		this.amount = amount;
		Transaction.accountSourceId = accountSourceId;
		Transaction.accountTargetId = accountTargetId;
	}
	
	

	public Transaction(float amount, int accountSourceId) 
	{
		super();
		this.amount = amount;
		Transaction.accountSourceId = accountSourceId;
	}


	public float getAmount() 
	{
		return amount;
	}


	public void setAmount(float amount) 
	{
		this.amount = amount;
	}


	public int getAccountSourceId() 
	{
		return accountSourceId;
	}


	public void setAccountSourceId(int accountSourceId) 
	{
		Transaction.accountSourceId = accountSourceId;
	}


	public int getAccountTargetId() 
	{
		return accountTargetId;
	}


	public void setAccountTargetId(int accountTargetId) 
	{
		Transaction.accountTargetId = accountTargetId;
	}


	@Override
	public String toString() 
	{
		return "Transaction [amount=" + amount + ", accountSourceId=" + accountSourceId + ", accountTargetId="
				+ accountTargetId + "]";
	}
	
	
	
	
	

}
