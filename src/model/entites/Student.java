package model.entites;

public class Student extends User {

	public Student(String userName, String userPassword, String Id) {
		super(userName, userPassword, Id);
		super.setAdm(false);
	}
}
