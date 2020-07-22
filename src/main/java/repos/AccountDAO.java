package repos;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.AccountStatus;
import models.AccountType;
import models.Users;

public interface AccountDAO 
{
	public boolean createAccount(Account account);
	public boolean updateAccountById(Account account);
	public Account getAccountByID(int accountId);
	public boolean deleteAccount(Account account);
	public boolean withdraw(Account account, float amount);
	public boolean deposit(Account account, float amount);
	public boolean transfer(Account account, float amount);
	public AccountStatus getAccountStatusById(int id);
	public AccountType getAccountTypeById(int id);
	public Set<Account> findAll();

}
