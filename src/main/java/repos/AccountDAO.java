package repos;

import models.Account;

public interface AccountDAO 
{
	public boolean CreateAccount(Account account);
	public boolean UpdateAccount(Account account);
	public Account GetAccountByID(int accountId);
	public void DeleteAccount(int accountId);

}
