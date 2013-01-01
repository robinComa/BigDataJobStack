package job;

import java.util.Date;

import utils.Coordinates;

public abstract class Job {

	private long id;
	private String requestedby;
	private long datetime;
	private Coordinates coordinates;
	
	public Job(){
		this.id = 0;
		Date d = new Date();
		this.datetime = d.getTime();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getRequestedby() {
		return requestedby;
	}

	public void setRequestedby(String requestedby) {
		this.requestedby = requestedby;
	}

	public long getDatetime() {
		return datetime;
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
}
