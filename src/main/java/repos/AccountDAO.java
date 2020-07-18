package repos;

import models.Account;
import models.AccountStatus;
import models.AccountType;

public interface AccountDAO 
{
	public boolean createAccount(Account account);
	public boolean updateAccountById(Account account);
	public Account getAccountByID(int accountId);
	public void deleteAccount(int accountId);
	public float withdraw(float amount);
	public float deposit(float amount);
	public float transfer(float amount);
	public AccountStatus getAccountStatusById(int id);
	public AccountType getAccountTypeById(int id);

}
