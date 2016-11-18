package com.netcracker.serviceTest;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.netcracker.ca.dao.StudentDao;
import com.netcracker.ca.model.Student;
import com.netcracker.ca.service.impl.StudentServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
	
	@Mock
    private StudentDao studentDao;
	private Student student;
	private int id;
	private int appFormId;
	
	@InjectMocks
    private StudentServiceImpl studentService = new StudentServiceImpl();
	
	@Test
    public void getByIdTest() {
		studentService.getById(id);
		verify(studentDao).getById(id);
	}
	
	@Test
	public void getByAppFormIdTest() {
		studentService.getByAppFormId(appFormId);
		verify(studentDao).getByAppFormId(appFormId);
	}
	
	@Test
	public void addTest() {
		studentService.add(student);
		verify(studentDao).add(student);
	}
	
	@Test
	public void updateTest() {
		studentService.update(student);
		verify(studentDao).update(student);
	}
		
}
