package com.netcracker.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.dao.StudentDAO;
import com.netcracker.model.Project;
import com.netcracker.model.Student;
import com.netcracker.model.StudentProjectStatus;
import com.netcracker.model.StudentProjectStatusType;
import com.netcracker.service.StudentService;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;

	@Override
	public Student getById(int id) {
		return studentDAO.getById(id);
	}

	@Override
	public Student getByAppFormId(int appFormId) {
		return studentDAO.getByAppFormId(appFormId);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(Student student) {
		studentDAO.add(student);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Student student) {
		studentDAO.update(student);
	}

	@Override
	public Map<Student, StudentProjectStatusType> getByProject(Project project) {
		return studentDAO.getByProject(project);
	}

	@Override
	public List<StudentProjectStatus> getProjectStatuses(Student student, Project project) {
		return studentDAO.getProjectStatuses(student, project);
	}
}