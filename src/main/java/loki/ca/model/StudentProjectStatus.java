package loki.ca.model;

import java.time.LocalDateTime;

public class StudentProjectStatus {

	private int id;
	
	private String comment;
	
	private LocalDateTime assigned;
	
	private StudentProjectStatusType type;
	
	public StudentProjectStatus() {}

	public StudentProjectStatus(String comment, LocalDateTime assigned, StudentProjectStatusType type) {
		super();
		this.comment = comment;
		this.assigned = assigned;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getAssigned() {
		return assigned;
	}

	public void setAssigned(LocalDateTime assigned) {
		this.assigned = assigned;
	}

	public StudentProjectStatusType getType() {
		return type;
	}

	public void setType(StudentProjectStatusType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assigned == null) ? 0 : assigned.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		StudentProjectStatus other = (StudentProjectStatus) obj;
		if (assigned == null) {
			if (other.assigned != null)
				return false;
		} else if (!assigned.equals(other.assigned))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
