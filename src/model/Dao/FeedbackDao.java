package model.Dao;

import model.entites.Feedback;

public interface FeedbackDao {
	public String findAll();
	public String finBYStudent (String idEstudent, Object obj);
	public void delete(int number);
	public void insert(Feedback feedback, String idEstudent);
	public void deleteAllFeedbacks(Object user);
}
