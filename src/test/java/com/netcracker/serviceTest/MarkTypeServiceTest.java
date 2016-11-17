package com.netcracker.serviceTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import com.netcracker.dao.*;
import com.netcracker.model.*;
import com.netcracker.service.impl.*;

@RunWith(MockitoJUnitRunner.class)
public class MarkTypeServiceTest {
	
	@Mock
    private MarkTypeDAO markTypeDAO;
	private MarkType markType;
	private Project project;
	private MarkTypeScope scope;
	private int id;
	
	@InjectMocks
    private MarkTypeServiceImpl markTypeService = new MarkTypeServiceImpl();
	
	@Test
    public void getByIdTest() {
		markTypeService.getById(id);
		verify(markTypeDAO).getById(id);
	}
	
	@Test
    public void addTest() {
		markTypeService.add(markType);
		verify(markTypeDAO).add(markType);
	}
	
	@Test
    public void updateTest() {
		markTypeService.update(markType);
		verify(markTypeDAO).update(markType);
	}
	
	@Test
    public void deleteTest() {
		markTypeService.delete(markType);
		verify(markTypeDAO).delete(markType);
	}
	
	@Test
    public void allowTest() {
		markTypeService.allow(markType, project, scope);
		verify(markTypeDAO).allow(markType, project, scope);
	}
	
	@Test
    public void disallowTest() {
		markTypeService.disallow(markType, project, scope);
		verify(markTypeDAO).disallow(markType, project, scope);
	}
	
	@Test
    public void getAllowedTest() {
		List<MarkType> markTypes = markTypeService.getAllowed(project, scope);
		verify(markTypeDAO).getAllowed(project, scope);
		assertNotNull(markTypes);
	}
	
}
