package com.netcracker.ca.model;

/**
 * Created by Oleksandr on 09.11.2016.
 */
public class MeetingEvaluation {
	private int id;
	private int intValue;
	private String textValue;
	private MarkType marktype;

	public MeetingEvaluation() {
	}

	public MeetingEvaluation(int id, int intValue, String textValue, MarkType marktype) {
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
		StringBuilder builder = new StringBuilder("MeetingEvaluation");
		return builder.append(" [id=").append(id)
				.append(", intValue=").append(intValue)
				.append(", textValue=").append(textValue)
				.append(", marktype.id=").append(marktype != null ? marktype.getId() : 0)
				.append("]").toString();
	}

}
