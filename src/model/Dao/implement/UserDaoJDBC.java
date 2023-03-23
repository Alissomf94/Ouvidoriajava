package model.Dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DataBase;
import model.Dao.UserDao;
import model.entites.Employee;
import model.entites.Student;
import model.entites.User;
import model.execption.DomainExecption;

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

		catch (SQLException e) 
		{
			System.err.println(e.getMessage());
			
		} 
		finally 
		{
			DataBase.closeStament(st);
		}

	}

	@Override
	public String findAll() {
		String ListStudent = "";
		PreparedStatement st = null;

		ResultSet rs = null;

		try {
			st = con.prepareStatement("SELECT * FROM users ORDER BY id");
			rs = st.executeQuery();

			while (rs.next()) {
				if (rs.getBoolean("adm") == false) {
					ListStudent += "\nStudent: " + rs.getString("Id");
				}
			}
			if (ListStudent == "") {
				throw new DomainExecption("Lista vazia");
			}

		} 
		catch (SQLException e) 
		{
			System.err.println(e.getMessage());

		} 
		finally {
			
			DataBase.closeResultSet(rs);
			DataBase.closeStament(st);
		}
		
		return ListStudent;
	}

	@Override
	public boolean checkLogin(String Identyti, String password) {
		Object obj = findById(Identyti);
		try {
			if (obj == null) {
				throw new DomainExecption("Id incorreto.");
			}
	
			String passwordDb = ((User) obj).getUserPassword();

			if (!passwordDb.equals(password)) 
				throw new DomainExecption("Senha incorreta.");
			
				return true;
		}
		
		catch (DomainExecption e) 
		{
			System.err.println(e.getMessage());
		}
		
		return false;
	}

	@Override
	public boolean checkAccessType(String id) {
		Object obj = findById(id);
		try {
			if (obj == null) {
				throw new DomainExecption("Usuario não encontrado");
			}

		} catch (DomainExecption e) {
			System.err.println(e.getMessage());
		}

		return ((User) obj).getAdm();

	}

	@Override
	public Object findById(String id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement("SELECT * FROM users WHERE id =?");
			st.setString(1, id);
			rs = st.executeQuery();
			String name = null;
			String password;
			String idUser = null;
			boolean adm = false;

			while (rs.next()) 
			{
				name = rs.getString("name");
				password = rs.getString("password");
				idUser = rs.getString("id");
				adm = rs.getBoolean("adm");
				
				if (adm == true)

					return new Employee(name, password, idUser);

				else if (adm == false)
					return new Student(name, password, idUser);
			}
		}
		
		catch (SQLException e) 
		{
			System.err.println(e.getMessage());
		}
		
		return null;
	}

}
