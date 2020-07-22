package repos;

import models.AccountStatus;

public interface AccountStatusDAO 
{
	public boolean updateStatus(AccountStatus accountStatus);
	public AccountStatus findById(int id);

}
