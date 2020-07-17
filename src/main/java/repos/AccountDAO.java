package repos;

import models.Account;

public interface AccountDAO 
{
	public boolean createAccount(Account account);
	public boolean updateAccountById(Account account);
	public Account getAccountByID(int accountId);
	public void deleteAccount(int accountId);

}
