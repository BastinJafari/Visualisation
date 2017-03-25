import java.util.ArrayList;
import java.util.List;

public class EventList {

	double x = 100; // for 100 timeslots at first
	int counter = 0; // to count the inserts
	private List<List<SimulationEvent>> timeEventList = new ArrayList<List<SimulationEvent>>(); // list
																								// of
																								// events
																								// +
																								// time

	public EventList() {

		for (int i = 0; i < x; i++) { // fills the eventlist with the first x
										// timeslots
			List<SimulationEvent> simulationEvent = new ArrayList<SimulationEvent>();
			timeEventList.add(simulationEvent);
		}

	}

	public void add(SimulationEvent event) { // adds Event on time

		counter++;
		if (counter == x / 2) {

			prolong(); // if list needs to get bigger
			counter = 0;
		}

		if (event.isSend()) {
			timeEventList.get(event.getSendTime()).add(event);
		}

		if (event.isReceive()) {
			timeEventList.get(event.getReceiveTime()).add(event);
		}

	}

	public List<SimulationEvent> getEvents(int time) {

		return this.timeEventList.get(time);
	}

	private void prolong() { // if list is full, then it makes the list 100
								// bigger


		for (double i = x; i < x + 100; i++) { // fills the eventlist with
												// another 100 timeslots

			List<SimulationEvent> simulationEvent = new ArrayList<SimulationEvent>();
			timeEventList.add(simulationEvent);
		}

		x = x + 100;
	}

	public String toString(int time) {
		String output = "";
		List<SimulationEvent> simulationEvents = timeEventList.get(time);
		for (int i = 0; i < simulationEvents.size(); i++) {

			SimulationEvent simulationEvent = simulationEvents.get(i);
			if (simulationEvent.isSend()) {
				output = output + "Message send from: " + simulationEvent.getMessage().getStart().getId() + "to "
						+ simulationEvent.getMessage().getDestination().getId() + "\n";

			}
			
			if (simulationEvent.isReceive()) {
				output = output + "Message received from: " + simulationEvent.getMessage().getStart().getId() + "to "
						+ simulationEvent.getMessage().getDestination().getId() + "\n";

			}
		}

		return output;

	}
}
