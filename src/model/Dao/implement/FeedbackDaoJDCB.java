package model.Dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DataBase;
import model.Dao.FeedbackDao;
import model.entites.Feedback;

public class FeedbackDaoJDCB implements FeedbackDao{
	
	private  Connection con;
	
	public FeedbackDaoJDCB (Connection con) {
		this.con = con;
	}
	@Override
	public String findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		String listFeedbacks = "";
		
		try {
			st = con.prepareStatement("SELECT * FROM feedbacks ORDER BY number");
			rs = st.executeQuery();
		
			while (rs.next()) {
				listFeedbacks += "\n------------------------------" + "\nNumero: " + rs.getInt("number") + "\nId do estudante: " + rs.getString("id") + "\nFeedbacks: " + rs.getString ("feedback")
				+ "\nTipo do feedback: " + rs.getString("tipo") + "\n------------------------------";
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		finally {
			DataBase.closeStament(st);
			DataBase.closeResultSet(rs);
		}
		if (listFeedbacks == "") {
			return "\nO sistema não possui feedback cadastrado";
		}
		
		return listFeedbacks;
	}

	@Override
	public String finBYStudent(String idEstudent) {
		
		PreparedStatement st = null;
		String feedbackList = "";
		ResultSet rs = null;
		try {
			
			st =  con.prepareStatement("SELECT * FROM feedbacks WHERE id =?");
			
			st.setString(1, idEstudent);
			
			rs =  st.executeQuery();
			
			while (rs.next()) {
				feedbackList += "\nTipo do feedback: " + rs.getString("tipo") + "\nFeedback: " + rs.getString("feedback");
			}
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			DataBase.closeStament(st);
			DataBase.closeResultSet(rs);
		}
		
		if (feedbackList == "") {
			return "\nNão possui feedbacks cadastrado.";
		}
		return feedbackList;
	}

	@Override
	public void delete(int number) {
		PreparedStatement st = null;	
		try {
				
			st = con.prepareStatement(
						
					"DELETE FROM feedbacks WHERE number = ?");	
			st.setInt(1,number);
				
			st.executeUpdate();
			st.close();
				
		} catch (Exception e) {
				
			e.printStackTrace();
				
		}
		finally {
			DataBase.closeStament(st);
		}
	}

	@Override
	public void insert(Feedback feedback,String idEstudent) {
		
		//variável que recebe o metodo PreparedStatement
		PreparedStatement st = null;
			
		try {
			//metodo que cria um objeto para representar um instrução SQL que sera executada
			st = con.prepareStatement("INSERT INTO feedbacks (id,tipo,feedback) VALUES (?,?,?)");
				
			//Atribuição dos valores necessarios para execução da instrução SQL
			st.setString(1, idEstudent);
				
			st.setObject(2,feedback.getTipo());
			
			st.setString(3,feedback.getFeedback());
			
			st.executeUpdate();	
			System.out.println("\nFeedback adicionado.");
			
			st.close();
				
		} 
			
		catch (SQLException e) {
				
			e.printStackTrace();
		}	
		finally {
			DataBase.closeStament(st);
		}
		
	}
	@Override
	public void deleteAllFeedbacks(String id) {
		PreparedStatement st = null;	
		try {
				
			st = con.prepareStatement(
						
					"DELETE FROM feedbacks WHERE id = ?");
				
			st.setString(1, id);
				
			st.executeUpdate();
			st.close();
			
				
		} catch (Exception e) {
				
			e.printStackTrace();
				
		}
		finally {
			DataBase.closeStament(st);
		}
		
	}
}
