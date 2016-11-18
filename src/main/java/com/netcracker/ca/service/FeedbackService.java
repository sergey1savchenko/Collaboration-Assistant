package com.netcracker.ca.service;

import java.util.List;

import com.netcracker.ca.model.Feedback;

public interface FeedbackService {

	List<Feedback> getAll();

	Feedback getById(int id);

	void add(Feedback feedback);

	void update(Feedback feedback);

	void delete(int id);
}