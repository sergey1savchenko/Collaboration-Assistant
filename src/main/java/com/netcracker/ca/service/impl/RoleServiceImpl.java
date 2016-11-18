package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.RoleDao;
import com.netcracker.ca.model.Role;
import com.netcracker.ca.service.RoleService;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getAll() {
		return roleDao.getAll();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void add(Role role) {
		roleDao.add(role);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void delete(int id) {
		roleDao.delete(id);
	}
}