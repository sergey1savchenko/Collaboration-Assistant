package com.netcracker.ca.dao;

import java.util.List;

import com.netcracker.ca.model.Meeting;

/**
 * Created by Oleksandr on 10.11.2016.
 */
public interface MeetingDao extends Dao<Meeting, Integer> {

    List<Meeting> getAllTeamMeetings(int id);

    List<Meeting> getAllProjectMeetings(int id);
}
