package model.entidades;

import model.execption.DomainExecption;

public class User {
	
	private String userName; //nome completo do usuário
	
	private String userPassword; // senha de cadastro do usuário
	
	private String userIdentity;// id do usuario

	//construtor para inicializar os atributos
	
	public User(String userName, String userPassword, String userIdentity) {
		
		if (userName == "" || userName ==  " " || userIdentity == "" || userIdentity ==  " " 
				|| userPassword == "" || userPassword ==  " ") {
			
			throw new DomainExecption("Você deve preencher  todos os dados.");
		}
	
		this.userName = userName;
		
		this.userPassword = userPassword;
		
		this.userIdentity = userIdentity;
	}
	
	// retorna o valor de Username
	public String getUserName() {
		return userName;
	}
	
	//atribui valor a UserName
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//retorna o valor de userPassword
	public String getUserPassword() {
		return userPassword;
	}
	
	//atribui valor a userPassword
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	// retorna o valor a de userIdentity
	public String getUserIdentity() {
		return userIdentity;
	}
	
	// atribui valor a userIdentity
	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}
}
