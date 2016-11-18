package com.netcracker.ca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.StudentDao;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.service.StudentService;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public Student getById(int id) {
		return studentDao.getById(id);
	}

	@Override
	public Student getByAppFormId(int appFormId) {
		return studentDao.getByAppFormId(appFormId);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(Student student) {
		studentDao.add(student);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Student student) {
		studentDao.update(student);
	}

}