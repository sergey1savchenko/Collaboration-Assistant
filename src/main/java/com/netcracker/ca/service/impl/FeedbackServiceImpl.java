package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.FeedbackDao;
import com.netcracker.ca.model.Feedback;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.service.FeedbackService;

@Service
@Transactional (readOnly = true)
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;
	
	@Override
	public List<Feedback> getAll() {
		return feedbackDao.getAll();
	}

	@Override
	public Feedback getById(int id) {
		return feedbackDao.getById(id);
	}
	
	@Override
	public Feedback getByStudentId(int id) {
		return feedbackDao.getByStudentId(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(Feedback feedback, int studentId) {
		feedback.setStudent(new Student(studentId));
		feedbackDao.add(feedback);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Feedback feedback) {
		feedbackDao.update(feedback);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(int id) {
		feedbackDao.delete(id);
	}
}