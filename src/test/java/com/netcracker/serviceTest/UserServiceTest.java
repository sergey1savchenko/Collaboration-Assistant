package com.netcracker.serviceTest;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.netcracker.ca.dao.UserDao;
import com.netcracker.ca.model.Role;
import com.netcracker.ca.model.User;
import com.netcracker.ca.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
    private UserDao userDao;
	private User user;
	private Role role;
	private int id;
	
	@InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();
	
	@Test
	public void getByIdTest(){
		userService.getById(id);
		verify(userDao).getById(id);
	}
	
	@Test
	public void getByEmailTest(){
		userService.getByEmail("email");
		verify(userDao).getByEmail("email");
	}
	
	@Test
	public void addTest(){
		userService.add(user);
		verify(userDao).add(user);
	}
	
	@Test
	public void updateTest(){
		userService.update(user);
		verify(userDao).update(user);
	}
	
}
