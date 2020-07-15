package repos;

import java.util.Set;

import models.Users;

public interface UsersDAO 
{
	public boolean Insert(Users user); // Registering a User
	public boolean UpdateUser(Users user);
	public Users FindByUserId(int userId);
	public Set<Users> SelectAll();
}
