package com.netcracker.ca.service.impl;

import java.util.List;

import com.netcracker.ca.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.UniversityDao;

import com.netcracker.ca.service.UniversityService;

@Service
@Transactional(readOnly = true)
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityDao universityDao;

    @Override
    public List<University> getAll() {
        return universityDao.getAll();
    }

}
