package model.entidades;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseConnection {
	
	//atributos para realizar 
	
	private String url;
	private String dataBaseUser;
	private String dataBasePassword;
	private Connection conn;
	
	public DataBaseConnection(){
		//configuração dos atributos 
		url = "jdbc:postgresql://localhost:5432/Ouvidoria";
		
		dataBaseUser = "postgres";
		
		dataBasePassword = "clansofbr1";
		
		// caso não ocorra erros a conexão sera feita 
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(url,dataBaseUser,dataBasePassword);
			
		} 
		
		//printar erro ao conectar banco de dados 
		catch (Exception e) {
			
			e.printStackTrace();
			
			System.out.println("\nFalha ao conectar o banco de dados.");
		}
		
		
		
	}

	//retoro da variável de conexão
	public Connection getConn() {
		
		return conn;
	}
}
