package loki.ca.model;

/**
 * Defines a type of mark used for project/meeting evaluations
 */
public class MarkType {

	private int id;
	
	private String title;
	
	private boolean hasText;
	
	private boolean hasInt;
	
	public MarkType() {}

	public MarkType(String title, boolean hasText, boolean hasInt) {
		this.title = title;
		this.hasText = hasText;
		this.hasInt = hasInt;
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

	public boolean hasText() {
		return hasText;
	}

	public void setHasText(boolean hasText) {
		this.hasText = hasText;
	}

	public boolean hasInt() {
		return hasInt;
	}

	public void setHasInt(boolean hasInt) {
		this.hasInt = hasInt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasInt ? 1231 : 1237);
		result = prime * result + (hasText ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		MarkType other = (MarkType) obj;
		if (hasInt != other.hasInt)
			return false;
		if (hasText != other.hasText)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MarkType [id=" + id + ", title=" + title + ", hasText=" + hasText + ", hasInt=" + hasInt + "]";
	}
	
}
