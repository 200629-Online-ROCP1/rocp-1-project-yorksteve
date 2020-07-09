package models;

public class AccountStatus 
{
	private int statusId;
	private String status;

	private void Open()
	{
		status = "Open";
	}
	
	private void Closed()
	{
		status = "closed";
	}
	
	private void Pending()
	{
		status = "Pending";
	}
	
	private void Denied()
	{
		status = "Denied";
	}
}
