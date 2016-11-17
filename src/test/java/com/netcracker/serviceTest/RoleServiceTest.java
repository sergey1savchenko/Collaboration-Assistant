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
public class RoleServiceTest {
	
	@Mock
    private RoleDAO roleDAO;
	private Role role;
	
	@InjectMocks
    private RoleServiceImpl roleService = new RoleServiceImpl();
	
	@Test
    public void getAllTest() {
		List<Role> roles = roleService.getAll();
		verify(roleDAO).getAll();
		assertNotNull(roles);
	}
	
	@Test
	public void addTest(){
		roleService.add(role);
		verify(roleDAO).add(role);
	}
	
	@Test
	public void updateTest(){
		roleService.update(role);
		verify(roleDAO).update(role);
	}
	
	@Test
	public void deleteTest(){
		roleService.delete(role);
		verify(roleDAO).delete(role);
	}
	
}
