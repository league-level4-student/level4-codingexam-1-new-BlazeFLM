package scheduler;

@SuppressWarnings("serial")
public class SchedulingConflictException extends Exception {

	public SchedulingConflictException() {
		System.out.println("This time slot is already booked");
	}
}
