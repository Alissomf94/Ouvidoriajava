package model.Dao;

import model.entites.User;

public interface UserDao {
	void insert(User user);
	String findAll ();
	boolean checkLogin(String Identyti,String password);
	boolean checkAccessType (String id);
	Object findById (String id);
	
}
