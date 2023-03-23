package model.Dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DataBase;
import model.Dao.FeedbackDao;
import model.entites.Feedback;
import model.entites.User;
import model.execption.DomainExecption;

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
			if (listFeedbacks.equals("")) {
				throw new DomainExecption("Lista vazia");
			}
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (DomainExecption e) {
			System.err.println(e.getMessage());
		}
		
		finally {
			DataBase.closeStament(st);
			DataBase.closeResultSet(rs);
		}
		
		return listFeedbacks;
	}

	@Override
	public String finBYStudent(String idEstudent,Object obj){
		
		PreparedStatement st = null;
		String feedbackList = "";
		ResultSet rs = null;
		try {
			
			if (obj == null) {
				throw new DomainExecption ("Usuario não encontrado");
			}
			st =  con.prepareStatement("SELECT * FROM feedbacks WHERE id =?");
			
			st.setString(1, idEstudent);
			
			rs =  st.executeQuery();
			
			while (rs.next()) {
				feedbackList += "\nTipo do feedback: " + rs.getString("tipo") + "\nFeedback: " + rs.getString("feedback");
			}
			
			if (feedbackList == "") {
				throw new DomainExecption("Lista vazia");
			}
		} 
		catch (DomainExecption e) {
			System.err.println(e.getMessage());
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		finally {
			DataBase.closeStament(st);
			DataBase.closeResultSet(rs);
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
				
		} 
		catch (SQLException e) {
				
			System.err.println(e.getMessage());
				
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
			
		} 
			
		catch (SQLException e) {
				
			System.err.println(e.getMessage());
		}	
		finally {
			DataBase.closeStament(st);
		}
		
	}
	@Override
	public void deleteAllFeedbacks(Object user) {
		PreparedStatement st = null;	
		try {
			if (user == null) {
				throw new DomainExecption("Usuario não encontrado");
			}
			st = con.prepareStatement(
						
					"DELETE FROM feedbacks WHERE id = ?");
				
			st.setString(1, ((User)user).getId());
				
			st.executeUpdate();
			st.close();
				
		} 
		catch (SQLException e) 
		{
				
			System.err.println(e.getMessage());	
		}
		catch (DomainExecption e) {
			System.err.println(e.getMessage());
		}
		finally 
		{
			DataBase.closeStament(st);
		}
		
	}
}
