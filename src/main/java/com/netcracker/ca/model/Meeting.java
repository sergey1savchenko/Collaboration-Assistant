package com.netcracker.ca.model;
import java.sql.Timestamp;

/**
 * Created by Oleksandr on 09.11.2016.
 */
public class Meeting {

    private int id;
    private String title;
    private String address;
    private Timestamp datetime;
    private Project project;
    private Team team;

    public Meeting() {
    }

    public Meeting(int id, String title, String address, Timestamp datetime, Project project, Team team) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.datetime = datetime;
        this.project = project;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", datetime=" + datetime +
                ", project=" + project.getTitle() +
                ", team=" + team.getTitle() +
                '}';
    }
}
