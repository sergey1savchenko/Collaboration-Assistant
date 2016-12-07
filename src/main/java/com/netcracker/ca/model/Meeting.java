package com.netcracker.ca.model;
import java.sql.Timestamp;

/**
 * Created by Oleksandr on 09.11.2016.
 */
public class Meeting {

	private int id;
    private String title;
    private String address;
    private Timestamp datetime;

    public Meeting() {
    }

    public Meeting(int id, String title, String address, Timestamp datetime) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.datetime = datetime;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
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
		Meeting other = (Meeting) obj;
		if (id != other.id)
			return false;
		return true;
	}

    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder("Meeting");
		return builder
			.append(" [id=").append(id)
			.append(", title=").append(title)
			.append(", address=").append(address)
			.append(", datetime=").append(datetime)
			.append("]").toString();
    }
}
