package com.netcracker.ca.model;

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

	public ProjectEvaluation(int id, int intValue, String textValue, Student student, User curator, MarkType marktype,
			Project project, int studentInProjectId) {
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

	public int getStudentInProjectId() {
		return studentInProjectId;
	}

	public void setStudentInProjectId(int studentInProjectId) {
		this.studentInProjectId = studentInProjectId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("ProjectEvaluation");
		return builder.append(" [id=").append(id).append(", intValue=").append(intValue).append(", textValue=")
				.append(textValue).append(", student.id=").append(student != null ? student.getId() : 0)
				.append(", curator.id=").append(curator != null ? curator.getId() : 0).append(", marktype.id=")
				.append(marktype != null ? marktype.getId() : 0).append(", project.id=")
				.append(project != null ? project.getId() : 0).append(", studentInProjectId").append(studentInProjectId)
				.append("]").toString();
	}
}
