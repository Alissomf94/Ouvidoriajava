package model.entidades;

public class Adm {
	//senha do administrador do sistema de ouvidoria
	private static final String admPassword = "adm123";
		
	//metodo que retorna o valor da senha do administrador
	public static String getAdmpassword() {
		return admPassword;
	}
		
	public boolean checkPassword (String passWord) {
			
		if (passWord.equals(admPassword))
				
			return true;
			
		return false;
	}
}
