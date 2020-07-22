package repos;

import models.AccountType;

public interface AccountTypeDAO 
{
	public AccountType findById(int id);
	public boolean updateType(AccountType accountType);

}
