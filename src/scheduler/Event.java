package scheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event {
	private LocalTime time;
    private String desc;

	public Event(String time, String desc) throws DateTimeParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(time, formatter);
		this.desc = desc;
	}

	public LocalTime getTime() {
        return time;
    }

	public String getDesc() {
		return desc;
	}

	
}
