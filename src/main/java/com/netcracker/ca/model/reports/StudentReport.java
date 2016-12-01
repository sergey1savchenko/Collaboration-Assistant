package com.netcracker.ca.model.reports;

/**
 * Created by Oleksandr on 16.11.2016.
 */
public class StudentReport {
    private String firstName;
    private String lastName;
    private String defense;
    private String generalInt;
    private String techInt;
    private String status;

    public StudentReport() {
    }

    public StudentReport(String firstName, String lastName, String defense, String generalInt, String techInt, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.defense = defense;
        this.generalInt = generalInt;
        this.techInt = techInt;
        this.status = status;
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

    public String getDefense() { return defense; }

    public void setDefense(String defense) { this.defense = defense; }

    public String getGeneralInt() {
        return generalInt;
    }

    public void setGeneralInt(String generalInt) {
        this.generalInt = generalInt;
    }

    public String getTechInt() {
        return techInt;
    }

    public void setTechInt(String techInt) {
        this.techInt = techInt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentReport{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", defense='" + defense + '\'' +
                ", generalInt='" + generalInt + '\'' +
                ", techInt='" + techInt + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
