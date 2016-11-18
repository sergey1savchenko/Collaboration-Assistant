package com.netcracker.ca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netcracker.ca.dao.MeetingDao;
import com.netcracker.ca.model.Meeting;
import com.netcracker.ca.service.MeetingService;

/**
 * Created by Oleksandr on 12.11.2016.
 */

@Service
@Transactional (readOnly = true)
public class MeetingServiceImpl implements MeetingService{

    @Autowired
    private MeetingDao meetingDao;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add(Meeting meeting) {
        meetingDao.add(meeting);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(Meeting meeting) {
        meetingDao.update(meeting);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int id) {
        meetingDao.delete(id);
    }

    @Override
    public List<Meeting> getAllTeamMeetings(int id) {
        return meetingDao.getAllTeamMeetings(id);
    }

    @Override
    public List<Meeting> getAllProjectMeetings(int id) {
        return meetingDao.getAllProjectMeetings(id);
    }
}
