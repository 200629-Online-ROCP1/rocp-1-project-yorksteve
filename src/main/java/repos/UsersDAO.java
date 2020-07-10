package repos;

import java.util.Set;

import models.Users;

public interface UsersDAO 
{
	public boolean Insert(Users user);
	public boolean InsertStatement(Users user);
	public Users FindByFirstName(String firstName);
	public Set<Users> SelectAll();
}
