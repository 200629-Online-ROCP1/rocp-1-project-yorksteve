package repos;

import java.util.Set;

import models.Users;

public interface UsersDAO 
{
	public boolean insert(Users user); // Registering a User
	public boolean updateUser(Users user);
	public Users findByUserId(int userId);
	public Users findByUserName(String username);
	public Users findByPassword(String password);
	public Set<Users> selectAll();
}
