package model.entites;



public  class User  {
	
	private String userName; //nome completo do usuário
	private String userPassword; // senha de cadastro do usuário
	private String Id;// id do usuario
	private Boolean adm;
	//construtor para inicializar os atributos
	
	public User(String userName, String userPassword, String Id) {

		this.userName = userName;
		
		this.userPassword = userPassword;
		
		this.Id = Id;
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
	public String getId() {
		return Id;
	}
	
	// atribui valor a userIdentity
	public void setUserIdentity(String userIdentity) {
		this.Id = userIdentity;
	}

	public Boolean getAdm() {
		return adm;
	}

	public void setAdm(Boolean adm) {
		this.adm = adm;
	}
	
}
