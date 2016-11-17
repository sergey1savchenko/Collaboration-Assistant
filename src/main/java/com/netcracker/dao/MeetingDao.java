package com.netcracker.dao;

import com.netcracker.model.Meeting;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oleksandr on 10.11.2016.
 */
public interface MeetingDao {

    void add(Meeting meeting) throws SQLException;

    void update(Meeting meeting) throws SQLException;

    void delete(int id) throws SQLException;

    List<Meeting> getAllTeamMeetings(int id);

    List<Meeting> getAllProjectMeetings(int id);
}