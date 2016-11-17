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
public class UserServiceTest {
	
	@Mock
    private UserDAO userDAO;
	private User user;
	private Role role;
	private int id;
	
	@InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();
	
	@Test
	public void getByIdTest(){
		userService.getById(id);
		verify(userDAO).getById(id);
	}
	
	@Test
	public void getByEmailTest(){
		userService.getByEmail("email");
		verify(userDAO).getByEmail("email");
	}
	
	@Test
	public void addTest(){
		userService.add(user);
		verify(userDAO).add(user);
	}
	
	@Test
	public void updateTest(){
		userService.update(user);
		verify(userDAO).update(user);
	}
	
	@Test
	public void updateRolesTest(){
		userService.updateRoles(user);
		verify(userDAO).updateRoles(user);
	}
	
	@Test
	public void addRoleTest(){
		userService.addRole(user, role);
		verify(userDAO).addRole(user, role);
	}
	
	@Test
	public void deleteRoleTest(){
		userService.deleteRole(user, role);
		verify(userDAO).deleteRole(user, role);
	}
	
}
