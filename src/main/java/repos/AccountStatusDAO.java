package repos;

import java.util.Set;

import models.AccountStatus;

public interface AccountStatusDAO 
{
	public boolean insert(AccountStatus accountStatus);
	public boolean updateStatus(AccountStatus accountStatus);
	public AccountStatus findById(int id);
	public Set<AccountStatus> selectAll();

}
