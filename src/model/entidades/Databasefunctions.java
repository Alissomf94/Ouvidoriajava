package model.entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;







public class Databasefunctions {
	
	//variavel de conexão com o banco de dados 
	private Connection conn;
	
	//construtor
	public Databasefunctions(Connection conn) {
		
		this.conn = conn;
	}
	
	
	
	
	//função que adiciona um usuario 
	public void insert (User obj1) {
		
		//variável que recebe o metodo PreparedStatement
		PreparedStatement st = null;
		
		try {
			//metodo que cria um objeto para representar um instrução SQL que sera executada
			st = conn.prepareStatement("INSERT INTO users (id,password,name) VALUES (?,?,?)");
			
			//Atribuição dos valores necessarios para execução da instrução SQL
			st.setString(1, obj1.getUserIdentity());
			
			st.setString(2, obj1.getUserPassword());
			
			st.setString(3,obj1.getUserName());
			
			
			
			st.close();
			
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	//função que adiciona um feedbacks do tipo reclamção
	public void insertFeedbacks (String identytiUser, Feedbacks obj){
			
		//variável que recebe o metodo PreparedStatement
		PreparedStatement st = null;
			
		try {
			//metodo que cria um objeto para representar um instrução SQL que sera executada
			st = conn.prepareStatement("INSERT INTO feedbacks (id,tipo,feedback) VALUES (?,?,?)");
				
			//Atribuição dos valores necessarios para execução da instrução SQL
			st.setString(1, identytiUser);
				
			st.setString(2, obj.getTipo());
			
			st.setString(3, obj.getFeedback());
			
				
			System.out.println("\nFeedback adicionado.");
			st.close();
				
		} 
			
		catch (SQLException e) {
				
			e.printStackTrace();
		}	
	}
	
	
	
	
	
	//metodo que deleta um usuario
	public void deleteUser(String id) throws SQLException {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement(
					
					"DELETE FROM users WHERE id = ?");
			
			st.setString(1, id);
			
			st.executeUpdate();
			
			System.out.println("\nUsuario deletado");
			
			st.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//metodo que deleta um usuario
	public void deleteAllFeedbacks(String id)  {
			
		PreparedStatement st = null;
			
		try {
				
			st = conn.prepareStatement(
						
					"DELETE FROM feedbacks WHERE id = ?");
				
			st.setString(1, id);
				
			st.executeUpdate();
			st.close();
			
				
		} catch (Exception e) {
				
			e.printStackTrace();
				
		}
		
	}	
	
	
	//metodo que deleta um feedback
	public void deleteFeedback(int number) {
			
		PreparedStatement st = null;
			
		try {
				
			st = conn.prepareStatement(
						
					"DELETE FROM feedbacks WHERE number = ?");
				
			st.setInt(1,number);
				
			st.executeUpdate();
				
			System.out.println("\nFeedback deletado");
			st.close();
				
		} catch (Exception e) {
				
			e.printStackTrace();
				
		}
		
	}
	
	
	//função para atualizar senha do usuario
	public void updateUserPassword (String id, String newPassword)  {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
			
			st.setString(1, newPassword);
			
			st.setString(2, id);
			
			st.executeUpdate();
			System.out.println("\nSenha atualizada com sucesso.");
			st.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
		
	}
	
	
	
	//  função que buscar o id de todos os usuarios 
	public void findAll ()  {
		
		PreparedStatement st = null;
		
		ResultSet rs = null;
		
		try {
			
			st =  conn.prepareStatement("SELECT * FROM users ORDER BY id");
			
			rs = st.executeQuery();
			
			int pos = 0;
			
			while (rs.next()) {
				
				++ pos;
				System.out.println(pos + " - " + rs.getString("id"));
	
			}
			st.close();
			
			rs.close();
			
 		} catch (Exception e) {
			
 			e.printStackTrace();
 			
		}
		
		
	}
	
	//metodo que faz um busca dos feedbacks por id
	public void finBYId (String Indetyti) {
		
		PreparedStatement st = null;
		
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("SELECT * FROM feedbacks WHERE id =?");
			
			st.setString(1, Indetyti);
			
			rs =  st.executeQuery();
			
			while (rs.next()) {
				
				
				int numberFeedback = rs.getInt("number");
				
				String tipoFeedback = rs.getString("tipo");
				
				String feedback = rs.getString("feedback");
				
				System.out.println(" ");
				
				System.out.println("Numero do feedback: " + numberFeedback);
				
				System.out.println("tipo do feedback: " + tipoFeedback);
				
				System.out.println("Feedback: " + feedback);
	
				
			}
			
		} 
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	// metodo que verifica o login
	public boolean checkLogin(String Identyti,String password) {
		
		PreparedStatement st = null;
		
		ResultSet rs = null;
		String passwordUser = "false";
		
		
		try {
			
			
			st = conn.prepareStatement("SELECT * FROM users WHERE id =?");
			
			st.setString(1,Identyti);
			
			rs =  st.executeQuery();
			
			while(rs.next()) {
				
				passwordUser = rs.getString("password");
			}
			if (password.equals(passwordUser)) {
				
				return true;
			}
			st.close();
			rs.close();
		} 
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return false;
	}
}
