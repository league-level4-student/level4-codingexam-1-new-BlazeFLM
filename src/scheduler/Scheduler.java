package scheduler;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

	private HashMap<Day, LinkedList<Event>> week;

	public Scheduler() {
		week = new HashMap<Day, LinkedList<Event>>();
		for (Day d : Day.values()) {
			week.put(d, new LinkedList<>());
		}
	}

	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		Scanner s = new Scanner(System.in);

		while (true) {

			System.out.println("Choose an option: ");
			System.out.println("1. Add Event");
			System.out.println("2. View Events");
			System.out.println("3. Remove Event");
			System.out.println("4. Exit");
			String input = s.nextLine();

			scheduler.run(input, s, scheduler);
		}

	}

	void run(String num, Scanner s, Scheduler scheduler) {
		switch (num) {
		case "1":
			System.out.println("Enter day of the week (e.g., MONDAY): ");
			String dayInput = s.nextLine().toUpperCase();
			Day day = Day.valueOf(dayInput);

			System.out.println("Enter time (e.g., 10:00): ");
			String time = s.nextLine();

			System.out.println("Enter event description: ");
			String description = s.nextLine();

			try {
				scheduler.createEvent(day, time, description);
			} catch (SchedulingConflictException e) {
				System.out.println(e.getMessage());
			}
			break;
		case "2":
			System.out.println("Enter day of the week to view events (e.g., MONDAY): ");
			dayInput = s.nextLine().toUpperCase();
			day = Day.valueOf(dayInput);

			scheduler.viewEvents(day);
			break;
		case "3":
			System.out.println("Enter day of the week (e.g., MONDAY): ");
			dayInput = s.nextLine().toUpperCase();
			day = Day.valueOf(dayInput);

			System.out.println("Enter time of the event to remove (e.g., 10:00): ");
			time = s.nextLine();

			scheduler.removeEvent(day, time);
			break;
		case "4":
			System.out.println("Exiting...");
			s.close();
			System.exit(0);
			break;

		default:
			System.out.println("Invalid option, please try again.");

		}
	}

	public void createEvent(Day d, String time, String desc) throws SchedulingConflictException {
		Event event = new Event(time, desc);
		LinkedList<Event> eventDay = week.get(d);

		Node<Event> current = eventDay.getHead();

		while (current != null) {
			if (current.getValue().getTime().equals(event.getTime())) {
				throw new SchedulingConflictException();
			}

			if (current.getValue().getTime().isAfter(event.getTime())) {
				Node<Event> newNode = new Node<Event>(event);
				Node<Event> prev = current.getPrev();

				if (prev != null) {
					prev.setNext(newNode);
				} else {
					eventDay.setHead(newNode);
				}

				newNode.setPrev(prev);
				newNode.setNext(current);
				current.setPrev(newNode);
				return;
			}
			current = current.getNext();
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

	public void removeEvent(Day d, String time) {
		LinkedList<Event> dayEvents = week.get(d);
		Node<Event> current = dayEvents.getHead();

		LocalTime t = LocalTime.parse(time);

		int position = 0;
		boolean found = false;

		while (current != null) {
			if (current.getValue().getTime().equals(t)) {
				dayEvents.remove(position);
				System.out.println("Event at " + time + " removed from " + d);
				found = true;
				break;
			}
			current = current.getNext();
			position++;
		}

		if (!found) {
			System.out.println("Event not found");
		}
	}

}
