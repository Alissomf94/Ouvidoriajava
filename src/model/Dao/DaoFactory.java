package model.Dao;

import DB.DataBase;
import model.Dao.implement.FeedbackDaoJDCB;
import model.Dao.implement.UserDaoJDBC;

public class DaoFactory {
	
	public static UserDao createUserDao () {
		return new UserDaoJDBC(DataBase.getConn());
	}
	public static FeedbackDao createFeedbackDao () {
		return new FeedbackDaoJDCB(DataBase.getConn());
					
	}
}
