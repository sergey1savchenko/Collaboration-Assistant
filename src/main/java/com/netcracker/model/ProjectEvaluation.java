package com.netcracker.model;

/**
 * Created by Oleksandr on 09.11.2016.
 */
public class ProjectEvaluation {
    private int id;
    private int intValue;
    private String textValue;
    private Student student;
    private User curator;
    private MarkType marktype;
    private Project project;
    private int studentInProjectId;

    public ProjectEvaluation() {
    }

    public ProjectEvaluation(int id, int intValue, String textValue, Student student, User curator, MarkType marktype, Project project, int studentInProjectId) {
        this.id = id;
        this.intValue = intValue;
        this.textValue = textValue;
        this.student = student;
        this.curator = curator;
        this.marktype = marktype;
        this.project = project;
        this.studentInProjectId = studentInProjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public User getCurator() {
        return curator;
    }

    public void setCurator(User curator) {
        this.curator = curator;
    }

    public MarkType getMarktype() {
        return marktype;
    }

    public void setMarktype(MarkType marktype) {
        this.marktype = marktype;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getStudentId() {return student.getId(); }

    public void setStudentId(int id) {student.setId(id); }

    public String getStudentFirstName() {return student.getFirstName(); }

    public void setStudentFirstName(String lastName) {student.setLastName(lastName); }

    public String getStudentLastName() {return student.getFirstName(); }

    public void setStudentLastName(String lastName) {student.setLastName(lastName); }

    public int getCuratorId() {return curator.getId(); }

    public void setCuratorId(int id) {curator.setId(id); }

    public String getCuratorFirstName() {return curator.getFirstName(); }

    public void setCuratorFirstName(String lastName) {curator.setLastName(lastName); }

    public String getCuratorLastName() {return curator.getFirstName(); }

    public void setCuratorLastName(String lastName) {curator.setLastName(lastName); }

    public int getMarkTypeId() {return marktype.getId(); }

    public void setMarkTypeId(int id) {marktype.setId(id); }

    public String getMarkTypeTitle() {return marktype.getTitle(); }

    public void setMarkTypeTitle(String title) {marktype.setTitle(title); }

    public boolean getMarkTypeHasInt() {return marktype.hasInt(); }

    public void setMarkTypeHasInt(boolean hasInt) {marktype.setHasInt(hasInt); }

    public boolean getMarkTypeHasText() {return marktype.hasText(); }

    public void setMarkTypeHasText(boolean hasText) {marktype.setHasText(hasText); }

    public int getProjectId() {return project.getId(); }

    public void setProjectId(int id) {project.setId(id); }

    public String getProjectTitle() {return project.getTitle(); }

    public void setProjectTitle(String title) {project.setTitle(title); }

    public int getStudentInProjectId() {return studentInProjectId; }

    public void setStudentInProjectId(int studentInProjectId) {this.studentInProjectId = studentInProjectId; }

    @Override
    public String toString() {
        return "ProjectEvaluation{" +
                "id=" + id +
                ", intValue=" + intValue +
                ", textValue='" + textValue + '\'' +
                ", student=" + student.getFirstName() + " " + student.getLastName() +
                ", curator=" + curator.getFirstName() + " " + curator.getLastName() +
                ", marktype=" + marktype.getTitle() +
                ", project=" + project.getTitle() +
                ", stInProjectId=" + studentInProjectId +
                '}';
    }
}