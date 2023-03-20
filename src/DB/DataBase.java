package DB;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBase {
	
	//atributos para realizar 
	
	
	private static Connection con;
	
	public static Connection getConn(){
		//configuração dos atributos 
		String url = "jdbc:postgresql://localhost:5432/Ouvidoria";
		
		String dataBaseUser = "postgres";
		
		String dataBasePassword = "clansofbr1";
		
		if (con == null) {
		// caso não ocorra erros a conexão sera feita 
			try {
				Class.forName("org.postgresql.Driver");
				
				con = DriverManager.getConnection(url,dataBaseUser,dataBasePassword);
				
			} 
			
			//printar erro ao conectar banco de dados 
			catch (Exception e) {
				
				e.printStackTrace();
				
				System.out.println("\nFalha ao conectar o banco de dados.");
			}
		}
		return con;
	}
	 
	
	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
			}
			catch (SQLException e){
				
				System.out.println(e.getMessage());
			}
		}
	}
	 public static void closeStament (Statement st) {
		 if (st != null) {
			 try {
				 st.close();
			 }
			 catch (SQLException e){
				 System.out.println(e.getMessage());
			 }
		 }
	 }
	 public static void closeResultSet (ResultSet rs) {
		 if (rs != null) {
			 try {
				 rs.close();
			 }
			 catch (SQLException e) {
				 System.out.println(e.getMessage());
			 }
		 }
	 }
}
