package com.netcracker.ca.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.netcracker.ca.dao.UniversityDao;
import com.netcracker.ca.model.University;

@Repository
public class UniversityDaoImpl implements UniversityDao {

    private static final String SQL_SELECT_ALL_UNIVERSITIES = "SELECT id, title FROM universities";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<University> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_UNIVERSITIES, new UniversityMapper());
    }

    private static class UniversityMapper implements RowMapper<University> {

        public University mapRow(ResultSet rs, int rowNum) throws SQLException {
            University university = new University();
            university.setId(rs.getInt("id"));
            university.setTitle(rs.getString("title"));
            return university;
        }
    }
}
