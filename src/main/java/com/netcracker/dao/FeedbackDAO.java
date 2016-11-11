package com.netcracker.dao;

import java.sql.SQLException;
import java.util.List;

import com.netcracker.model.Feedback;

public interface FeedbackDAO {

	List<Feedback> getAll();

	Feedback getById(int id);

	void add(Feedback feedback) throws SQLException;

	void update(Feedback feedback) throws SQLException;

	void delete(int id) throws SQLException;
}