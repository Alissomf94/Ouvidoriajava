package model.entites;

public class Employee extends User {
	
	public Employee(String userName, String userPassword, String Id) {
		super(userName, userPassword, Id);
		super.setAdm(true);
	}
}
