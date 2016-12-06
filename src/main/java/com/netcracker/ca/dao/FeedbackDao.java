package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Feedback;

public interface FeedbackDao extends Dao<Feedback, Integer> {

	List<Feedback> getAll();
	
	Feedback getByStudentId(Integer id);
	
}