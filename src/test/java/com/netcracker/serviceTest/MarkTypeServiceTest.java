package com.netcracker.serviceTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.netcracker.ca.dao.MarkTypeDao;
import com.netcracker.ca.model.MarkType;
import com.netcracker.ca.model.MarkTypeScope;
import com.netcracker.ca.model.Project;
import com.netcracker.ca.service.impl.MarkTypeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MarkTypeServiceTest {
	
	@Mock
    private MarkTypeDao markTypeDao;
	private MarkType markType;
	private Project project;
	private MarkTypeScope scope;
	private int id;
	
	@InjectMocks
    private MarkTypeServiceImpl markTypeService = new MarkTypeServiceImpl();
	
	@Test
    public void getByIdTest() {
		markTypeService.getById(id);
		verify(markTypeDao).getById(id);
	}
	
	@Test
    public void addTest() {
		markTypeService.add(markType);
		verify(markTypeDao).add(markType);
	}
	
	@Test
    public void updateTest() {
		markTypeService.update(markType);
		verify(markTypeDao).update(markType);
	}
	
	@Test
    public void deleteTest() {
		markTypeService.delete(markType.getId());
		verify(markTypeDao).delete(markType.getId());
	}
	
	@Test
    public void allowTest() {
		markTypeService.allow(markType.getId(), project.getId(), scope);
		verify(markTypeDao).allow(markType.getId(), project.getId(), scope);
	}
	
	@Test
    public void disallowTest() {
		markTypeService.disallow(markType.getId(), project.getId(), scope);
		verify(markTypeDao).disallow(markType.getId(), project.getId(), scope);
	}
	
	@Test
    public void getAllowedTest() {
		List<MarkType> markTypes = markTypeService.getAllowed(project.getId(), scope);
		verify(markTypeDao).getAllowed(project.getId(), scope);
		assertNotNull(markTypes);
	}
	
}
