package repos;

import java.util.Set;

import models.AccountType;

public interface AccountTypeDAO 
{
	public boolean Insert(AccountType accountType);
	public boolean InsertStatement(AccountType accountType);
	public AccountType FindByFirstName(String firstName);
	public Set<AccountType> SelectAll();
	public boolean UpdateType(AccountType accountType);

}
