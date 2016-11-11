package com.netcracker.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.dao.FeedbackDAO;
import com.netcracker.model.Feedback;
import com.netcracker.service.FeedbackService;

@Service
@Transactional (readOnly = true)
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDAO feedbackDAO;
	
	@Override
	public List<Feedback> getAll() {
		return feedbackDAO.getAll();
	}

	@Override
	public Feedback getById(int id) {
		return feedbackDAO.getById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(Feedback feedback) throws SQLException {
		feedbackDAO.add(feedback);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Feedback feedback) throws SQLException {
		feedbackDAO.update(feedback);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(int id) throws SQLException {
		feedbackDAO.delete(id);
	}
}