package com.netcracker.ca.model;

/**
 * Created by Oleksandr on 09.11.2016.
 */
public class ProjectEvaluation {
	private int id;
	private int intValue;
	private String textValue;
	private MarkType marktype;

	public ProjectEvaluation() {
	}

	public ProjectEvaluation(int id, int intValue, String textValue, MarkType marktype) {
		this.id = id;
		this.intValue = intValue;
		this.textValue = textValue;
		this.marktype = marktype;
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

	public MarkType getMarktype() {
		return marktype;
	}

	public void setMarktype(MarkType marktype) {
		this.marktype = marktype;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("ProjectEvaluation");
		return builder.append(" [id=").append(id)
				.append(", intValue=").append(intValue)
				.append(", textValue=").append(textValue)
				.append(", marktype.id=").append(marktype != null ? marktype.getId() : 0)
				.append("]").toString();
	}

}
