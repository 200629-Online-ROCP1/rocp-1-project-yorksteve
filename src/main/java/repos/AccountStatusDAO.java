package repos;

import java.util.Set;

import models.AccountStatus;

public interface AccountStatusDAO 
{
	public boolean Insert(AccountStatus accountStatus);
	public boolean UpdateStatus(AccountStatus accountStatus);
	public AccountStatus FindByFirstName(String firstName);
	public Set<AccountStatus> SelectAll();

}
