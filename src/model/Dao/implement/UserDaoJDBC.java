package model.Dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DataBase;
import model.Dao.UserDao;
import model.entites.User;

public class UserDaoJDBC implements UserDao {
	private Connection con;
	
	public UserDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(User user) {
		// variável que recebe o metodo PreparedStatement
		PreparedStatement st = null;

		try {
			// metodo que cria um objeto para representar um instrução SQL que sera
			// executada
			st = con.prepareStatement("INSERT INTO users (id,password,name,adm) VALUES (?,?,?,?)");

			// Atribuição dos valores necessarios para execução da instrução SQL
			st.setString(1, user.getId());

			st.setString(2, user.getUserPassword());

			st.setString(3, user.getUserName());
			st.setBoolean(4, user.getAdm());
			st.executeUpdate();
			

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			DataBase.closeStament(st);
		}

	}

	@Override
	public String findAll() {
		String ListStudent  = "";
		PreparedStatement st = null;
		
		ResultSet rs = null;
		
		try {
			st =  con.prepareStatement("SELECT * FROM users ORDER BY id");
			rs = st.executeQuery();
			
			while (rs.next()) {
				if (rs.getBoolean("adm") == false) {
					ListStudent +="\nStudent: " + rs.getString("Id");
				}
			}
			
 		} catch (Exception e) {
 			e.printStackTrace();
 			
		}
		finally {
			DataBase.closeStament(st);
		}
		if (ListStudent == "") {
			return "\nO sistema não possui alunos cadastrados.";
		}
		return ListStudent;
	}

	@Override
	public boolean checkLogin(String Identyti, String password) {
		PreparedStatement st = null;
		ResultSet rs = null;
		String passwordUser = "false";
		
		try {
			st = con.prepareStatement("SELECT * FROM users WHERE id =?");
			st.setString(1,Identyti);
			rs =  st.executeQuery();
			
			while(rs.next()) {
				passwordUser = rs.getString("password");
			}
			
			if (password.equals(passwordUser)) {
				return true;
			}
		
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DataBase.closeResultSet(rs);
			DataBase.closeStament(st);
		}
		
		
		return false;
	}

	@Override
	public boolean checkAccessType(String id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement("SELECT * FROM users WHERE id =?");
			st.setString(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				return rs.getBoolean("adm");
			}
		}
		catch (SQLException e) {
			
		}
		finally {
			DataBase.closeResultSet(rs);
			DataBase.closeStament(st);
		}
		return false;
	}

}
