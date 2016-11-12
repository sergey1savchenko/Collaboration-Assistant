package com.netcracker.service.impl;

import com.netcracker.dao.MeetingDao;
import com.netcracker.model.Meeting;
import com.netcracker.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

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
    public void add(Meeting meeting) throws SQLException {
        meetingDao.add(meeting);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(Meeting meeting) throws SQLException {
        meetingDao.update(meeting);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int id) throws SQLException {
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
