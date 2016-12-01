package com.netcracker.ca.model.reports;

import java.sql.Timestamp;

/**
 * Created by Oleksandr on 25.11.2016.
 */
public class StudentInProjectReport {

    private String firstName;
    private String lastName;
    private String status;
    private Timestamp datetime;
    private String comment;

    public StudentInProjectReport() {
    }

    public StudentInProjectReport(String firstName, String lastName, String status, Timestamp datetime, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.datetime = datetime;
        this.comment = comment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "StudentInProjectReport{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                ", datetime=" + datetime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
