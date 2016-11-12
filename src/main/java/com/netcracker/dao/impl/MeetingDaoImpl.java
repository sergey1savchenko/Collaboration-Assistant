package com.netcracker.dao.impl;

import com.netcracker.dao.MeetingDao;
import com.netcracker.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oleksandr on 10.11.2016.
 */

@Repository
public class MeetingDaoImpl implements MeetingDao {

    private static String SQL_INSERT_MEETING = "INSERT INTO meetings (address, title, datetime, project_id, team_id) VALUES (?, ?, ?, ?, ?)";
    private static String SQL_UPDATE_MEETING = "UPDATE meetings SET address=?, title=?, datetime=?, project_id=?, team_id=? WHERE id=?";
    private static String SQL_DELETE_MEETING = "DELETE FROM meetings WHERE id=?";
    private static String SQL_SELECT_ALL_MEETINGS = "SELECT meetings.id, meetings.title, datetime, projects.id, projects.title, teams.id, teams.title" +
            "FROM meetings INNER JOIN projects ON meetings.project_id = projects.id" +
            "INNER JOIN teams ON meetings.team_id = teams.id";
    private static String SQL_SELECT_TEAM_MEETINGS = SQL_SELECT_ALL_MEETINGS + " WHERE teams.id=?";
    private static String SQL_SELECT_PROJECT_MEETINGS = SQL_SELECT_ALL_MEETINGS + " WHERE projects.id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Meeting meeting) throws SQLException {
        PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_MEETING);
                ps.setString(1, meeting.getAddress());
                ps.setString(2, meeting.getTitle());
                ps.setTimestamp(3, meeting.getDatetime());
                ps.setInt(4, meeting.getProjectId());
                ps.setInt(5, meeting.getTeamId());
                ps.executeUpdate();
                ps.close();
            }


    @Override
    public void update(Meeting meeting) throws SQLException {
        PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_MEETING);
            ps.setString(1, meeting.getAddress());
            ps.setString(2, meeting.getTitle());
            ps.setTimestamp(3, meeting.getDatetime());
            ps.setInt(4, meeting.getProjectId());
            ps.setInt(5, meeting.getTeamId());
            ps.setInt(6, meeting.getId());
            ps.executeUpdate();
            ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_MEETING);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<Meeting> getAllTeamMeetings(int id) {
        return jdbcTemplate.query(SQL_SELECT_TEAM_MEETINGS, new MeetingMapper(), id);
    }

    @Override
    public List<Meeting> getAllProjectMeetings(int id) {
        return jdbcTemplate.query(SQL_SELECT_PROJECT_MEETINGS, new MeetingMapper(), id);
    }



    private static class MeetingMapper implements RowMapper<Meeting> {
        public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Meeting meeting = new Meeting();
            meeting.setId(rs.getInt("id"));
            meeting.setAddress(rs.getString("address"));
            meeting.setTitle(rs.getString("title"));
            meeting.setDatetime(rs.getTimestamp("datetime"));
            meeting.setProjectId(rs.getInt("id"));
            meeting.setProjectTitle(rs.getString("title"));
            meeting.setTeamId(rs.getInt("id"));
            meeting.setTeamTitle(rs.getString("title"));
            return meeting;
        }
    }
}
