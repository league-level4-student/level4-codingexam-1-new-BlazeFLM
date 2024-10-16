package scheduler;

import java.util.HashMap;
import java.util.Scanner;

/*
 * Objective: Create a weekly scheduling application.
 * 
 * You may create any additional enums, classes, methods or variables needed
 * to accomplish the requirements below:
 * 
 * - You should use an array filled with enums for the days of the week and each
 *   enum should contain a LinkedList of events that includes a time and what is 
 *   happening at the event.
 * 
 * - The user should be able to to interact with your application through the
 *   console and have the option to add events, view events or remove events by
 *   day.
 *   
 * - Each day's events should be sorted by chronological order.
 *  
 * - If the user tries to add an event on the same day and time as another event
 *   throw a SchedulingConflictException(created by you) that tells the user
 *   they tried to double book a time slot.
 *   
 * - Make sure any enums or classes you create have properly encapsulated member
 *   variables.
 */
public class Scheduler {

	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		
		//unfinished
		
		s.close();
	}
	
	
	
	
	
	private HashMap<Day, LinkedList<Event>> week;

	public Scheduler() {
		week = new HashMap<Day, LinkedList<Event>>();
		for (Day d : Day.values()) {
			week.put(d, new LinkedList<>());
		}
	}

	

	public void createEvent(Day d, String time, String desc) throws SchedulingConflictException {
		Event event = new Event(time, desc);

		LinkedList<Event> eventDay = week.get(d);
		Node<Event> e = new Node<Event>(event);
		
		for (int i = 0; i < eventDay.size(); i++) {
			if (e.getValue().getTime().equals(time)) {
				throw new SchedulingConflictException();
			}
		}
		
		eventDay.add(event);
		
		
		System.out.println("Event added: " + desc + " at " + time + " on " + d);

	}
	
	public void viewEvents(Day d) {
		LinkedList<Event> eventDay = week.get(d);
		
		if (eventDay.size() == 0) {
            System.out.println("No events scheduled for " + d);
        } else {
            System.out.println("Events for " + d + ":");
            Node<Event> current = eventDay.getHead();
            while (current != null) {
                System.out.println(current.getValue().getTime() + " - " + current.getValue().getDesc());
                current = current.getNext();
            }
        }
	}

}
