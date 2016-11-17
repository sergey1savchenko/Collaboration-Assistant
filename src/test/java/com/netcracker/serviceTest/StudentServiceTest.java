package com.netcracker.serviceTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import java.util.Map;

import com.netcracker.dao.*;
import com.netcracker.model.*;
import com.netcracker.service.impl.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
	
	@Mock
    private StudentDAO studentDAO;
	private Student student;
	private Project project;
	private int id;
	private int appFormId;
	
	@InjectMocks
    private StudentServiceImpl studentService = new StudentServiceImpl();
	
	@Test
    public void getByIdTest() {
		studentService.getById(id);
		verify(studentDAO).getById(id);
	}
	
	@Test
	public void getByAppFormIdTest() {
		studentService.getByAppFormId(appFormId);
		verify(studentDAO).getByAppFormId(appFormId);
	}
	
	@Test
	public void addTest() {
		studentService.add(student);
		verify(studentDAO).add(student);
	}
	
	@Test
	public void updateTest() {
		studentService.update(student);
		verify(studentDAO).update(student);
	}
	
	@Test
	public void getByProjectTest(){
		Map<Student, StudentProjectStatusType> stat = studentService.getByProject(project);
		verify(studentDAO).getByProject(project);
		assertNotNull(stat);
	}
	
	@Test
	public void getProjectStatusesTest(){
		List<StudentProjectStatus> sps = studentService.getProjectStatuses(student, project);
		verify(studentDAO).getProjectStatuses(student, project);
		assertNotNull(sps);
	}
		
}
