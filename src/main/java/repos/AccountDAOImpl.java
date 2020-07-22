package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.AccountStatus;
import models.AccountType;
import models.Role;
import models.Users;
import util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO 
{
	
	// Singleton Form
	private static AccountDAOImpl repo = new AccountDAOImpl();
	public AccountDAOImpl() {}
	public static AccountDAOImpl GetInstance()
	{
		return repo;
	}
	
	
	Account account = new Account();
	
	private static final AccountStatusDAO asdao = new AccountStatusDAOImpl();
	private static final AccountTypeDAO atdao = new AccountTypeDAOImpl();
	private static final UsersDAO udao = new UsersDAOImpl();
	
	
	@Override
	public boolean createAccount(Account account) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			int index = 0;
			
			String sql = "INSERT INTO accounts(account_id, account_balance, "
					+ "account_status_fk, account_type_fk, user_id_fk) VALUES(?,?,?,?,?);";
			
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setInt(++index, account.getAccountId());
			statement.setFloat(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUserId().getUserID());
			
			if (statement.execute()) 
			{
				return true;
			}
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return false;
	}

	
	
	@Override
	public boolean updateAccountById(Account account) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			int index = 0;
			
			String sql = "UPDATE accounts WHERE account_id = ?;";
			
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setFloat(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUserId().getUserID());
			
			if (statement.execute())
			{
				return true;
			}
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		
		return false;
	}

	
	
	@Override
	public Account getAccountByID(int accountId) 
	{
		
		
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM accounts WHERE account_id = ?;";
			
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setInt(1, accountId);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next())
			{
				Account a = new Account();
				a.setAccountId(result.getInt("account_id"));
				a.setBalance(result.getFloat("account_balance"));
				
				AccountStatus as = asdao.findById(result.getInt("account_status_fk"));
				a.setStatus(as);
				
				AccountType at = atdao.findById(result.getInt("account_type_fk"));
				a.setType(at);
				
				Users u = udao.findByUserId(result.getInt("user_id_fk"));
				a.setUserId(u);
				
				return a;			
			}
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return null;
	}


	
	
	@Override
	public boolean deleteAccount(Account account) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			String sql = "DELETE FROM accounts WHERE account_id = ?;";
			
			PreparedStatement statement = aconn.prepareStatement(sql);
			statement.setInt(1, account.getAccountId());
			
			int row = statement.executeUpdate();
			System.out.println("Rows deleted: " + row);
			if (row > 0)
			{
				return true;
			}
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return false;
	}
	
	@Override
	public boolean withdraw(Account account, float amount) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			if (amount < 0)
			{
				System.out.println("Not able to withdraw negative amount.");
				return false;
			}
			
			else if (amount > account.getBalance())
			{
				System.out.println("Withdraw amount greater than account balance.");
				return false;
			}
			
			else 
			{
				account.setBalance(account.getBalance() - amount);
				System.out.println("$" + amount + " has been withdrawn from Account #" + account);
				return true;
			}
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return false;
	}
	
		
	

	@Override
	public boolean deposit(Account account, float amount) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			if (amount < 0)
			{
				System.out.println("Not able to deposit negative amount.");
				return false;
			}
			
			else
			{
				account.setBalance(account.getBalance() + amount);
				System.out.println("$" + amount + " has been deposited to Account #" + account);
				return true;
			}
			
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return false;
	}
	
	
	@Override
	public boolean transfer(Account account, float amount) 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		
		return false;
	}
	
	
	@Override
	public AccountStatus getAccountStatusById(int id) 
	{
		AccountStatus accountStatus = asdao.findById(id);
		
		return accountStatus;
	}
	
	@Override
	public AccountType getAccountTypeById(int id) 
	{
		AccountType accountType = atdao.findById(id);
		
		return accountType;
	}

	@Override
	public Set<Account> findAll() 
	{
		try (Connection aconn = ConnectionUtil.GetConnection())
		{
			String sql = "SELECT * FROM accounts;";
			
			PreparedStatement statement = aconn.prepareStatement(sql);
			
			Set<Account> set = new HashSet<>();
			
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				Account a = new Account();
				a.setAccountId(result.getInt("account_id"));
				a.setBalance(result.getFloat("account_balance"));
				
				AccountStatus as = asdao.findById(result.getInt("account_status_fk"));
				a.setStatus(as);
				
				AccountType at = atdao.findById(result.getInt("account_type_fk"));
				a.setType(at);
				
				set.add(a);
			}
			
			return set;
			
			
		}
		
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return null;
	}
	

}
