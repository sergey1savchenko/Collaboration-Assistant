package com.netcracker.model;

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

	public int getInterviewStatusId() {
		return interviewStatus.getId();
	}

	public void setInterviewStatusId(int id) {
		if (this.interviewStatus == null)
			this.interviewStatus = new InterviewStatus();
		this.interviewStatus.setId(id);
	}

	public String getInterviewStatusDescription() {
		return interviewStatus.getDescription();
	}

	public void setInterviewStatusDescription(String description) {
		if (this.interviewStatus == null)
			this.interviewStatus = new InterviewStatus();
		this.interviewStatus.setDescription(description);
	}

	public int getHRId() {
		return hr.getId();
	}

	public void setHRId(int id) {
		if (this.hr == null)
			this.hr = new User();
		this.hr.setId(id);
	}

	public String getHRFirstName() {
		return hr.getFirstName();
	}

	public void setHRFirstName(String firstName) {
		if (this.hr == null)
			this.hr = new User();
		this.hr.setFirstName(firstName);
	}

	public String getHRLastName() {
		return hr.getLastName();
	}

	public void setHRLastName(String lastName) {
		if (this.hr == null)
			this.hr = new User();
		this.hr.setLastName(lastName);
	}

	public int getStudentId() {
		return student.getId();
	}

	public void setStudentId(int id) {
		if (this.student == null)
			this.student = new Student();
		this.student.setId(id);
	}

	public String getStudentFirstName() {
		return student.getFirstName();
	}

	public void setStudentFirstName(String firstName) {
		if (this.student == null)
			this.student = new Student();
		this.student.setFirstName(firstName);
	}

	public String getStudentLastName() {
		return student.getLastName();
	}

	public void setStudentLastName(String lastName) {
		if (this.student == null)
			this.student = new Student();
		this.student.setLastName(lastName);
	}

	public int getProjectId() {
		return project.getId();
	}

	public void setProjectId(int id) {
		if (this.project == null)
			this.project = new Project();
		this.project.setId(id);
	}

	public String getProjectTitle() {
		return project.getTitle();
	}

	public void setProjectTitle(String title) {
		if (this.project == null)
			this.project = new Project();
		this.project.setTitle(title);
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
		return "Feedback [id=" + id + ", generalReport=" + generalReport + ", techReport=" + techReport
				+ ", interviewer=" + interviewer + ", interviewStatus=" + interviewStatus + ", hr=" + hr + ", student="
				+ student + ", project=" + project + "]";
	}
}