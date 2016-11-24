package com.netcracker.ca.model;

public class Feedback {

	private int id;
	private String generalReport;
	private String techReport;
	private String interviewer;
	private InterviewStatus interviewStatus;
	private User hr;
	private Student student;
	private Project project;

	public Feedback() {
	}

	public Feedback(int id, String generalReport, String techReport, String interviewer,
			InterviewStatus interviewStatus, User hr, Student student, Project project) {
		super();
		this.id = id;
		this.generalReport = generalReport;
		this.techReport = techReport;
		this.interviewer = interviewer;
		this.interviewStatus = interviewStatus;
		this.hr = hr;
		this.student = student;
		this.project = project;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGeneralReport() {
		return generalReport;
	}

	public void setGeneralReport(String generalReport) {
		this.generalReport = generalReport;
	}

	public String getTechReport() {
		return techReport;
	}

	public void setTechReport(String techReport) {
		this.techReport = techReport;
	}

	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}

	public InterviewStatus getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(InterviewStatus interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public User getHR() {
		return hr;
	}

	public void setHR(User hr) {
		this.hr = hr;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Feedback");
		return builder
			.append(" [generalReport=").append(generalReport)
			.append(", techReport=").append(techReport)
			.append(", interviewer=").append(interviewer)
			.append(", interviewStatus.id=").append(interviewStatus != null? interviewStatus.getId(): 0)
			.append(", hr.id=").append(hr != null? hr.getId(): 0)
			.append(", student.id=").append(student != null? student.getId(): 0)
			.append(", project.id=").append(project != null? project.getId(): 0)
			.append("]").toString();
	}
}