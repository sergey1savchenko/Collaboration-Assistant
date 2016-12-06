package com.netcracker.ca.dao;

import com.netcracker.ca.model.InterviewStatus;

public interface InterviewStatusDao extends Dao<InterviewStatus, Integer>{

	InterviewStatus getByDesc(String desc);
}
