package repos;

import java.util.Set;

import models.AccountType;

public interface AccountTypeDAO 
{
	public boolean insert(AccountType accountType);
	public boolean insertStatement(AccountType accountType);
	public AccountType findById(int id);
	public Set<AccountType> selectAll();
	public boolean updateType(AccountType accountType);

}
