package scheduler;

public class Event {
	private String time;
	private String desc;
	
	public Event(String time, String desc) {
		this.time = time;
		this.desc = desc;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getDesc() {
		return desc;
	}
}
