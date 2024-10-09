package scheduler;

public enum Day {
	SUNDAY(new LinkedList<>()), 
	MONDAY(new LinkedList<>()), 
	TUESDAY(new LinkedList<>()), 
	WEDNESDAY(new LinkedList<>()), 
	THURSDAY(new LinkedList<>()), 
	FRIDAY(new LinkedList<>()), 
	SATURDAY(new LinkedList<>());
	

	private LinkedList<Event> events;
	
    Day(LinkedList<Event> events) {
        this.events = events;
    }
    
     
    
}
