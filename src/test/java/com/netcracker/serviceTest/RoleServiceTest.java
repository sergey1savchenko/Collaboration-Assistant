package com.netcracker.serviceTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.netcracker.ca.dao.RoleDao;
import com.netcracker.ca.model.Role;
import com.netcracker.ca.service.impl.RoleServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
	
	@Mock
    private RoleDao roleDao;
	private Role role;
	
	@InjectMocks
    private RoleServiceImpl roleService = new RoleServiceImpl();
	
	@Test
    public void getAllTest() {
		List<Role> roles = roleService.getAll();
		verify(roleDao).getAll();
		assertNotNull(roles);
	}
	
	@Test
	public void addTest(){
		roleService.add(role);
		verify(roleDao).add(role);
	}
	
	@Test
	public void updateTest(){
		roleService.update(role);
		verify(roleDao).update(role);
	}
	
	@Test
	public void deleteTest(){
		roleService.delete(role.getId());
		verify(roleDao).delete(role.getId());
	}
	
}
