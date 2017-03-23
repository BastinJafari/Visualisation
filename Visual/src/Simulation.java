import java.util.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulation {

	private SimulationVisual simulationVisual; // to communicate with visuals
	private boolean fifo = true;
	private int currentId = 0; // to dynamically give Ids to processes
	private int time = 0;
	private Timeline timeline;
	private EventList eventList;
	private List<Process> processList;
	private Process selectedProcess;

	public Timeline getTimeline() {
		return this.timeline;
	}

	public Simulation(Process... processes) { // Simulation runs forever

		eventList = new EventList();
		this.processList = new ArrayList<Process>();

		for (int i = 0; i < processes.length; i++) {
			processes[i].setId(currentId);
			currentId++;
			this.processList.add(processes[i]);

		}

		timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> this.incrementTime())); // the
		// clock

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public Simulation(int cycles, Process... processes) { // adds an indefinite
															// number of
															// processes to the
															// Simulation and
															// sets the cycles

		this.processList = new ArrayList<Process>();
		for (int i = 0; i < processes.length; i++) {
			processes[i].setId(currentId);
			currentId++;
			this.processList.add(processes[i]);
		}

		timeline = new Timeline(); // the
		timeline.setCycleCount(cycles);
		timeline.play();
	}

	public List<Process> getProcessList() {
		return this.processList;
	}

	public void createProcess() {
		Process process = new Process(this);
		process.setId(currentId);
		this.processList.add(currentId, process);
		currentId++;
	}

	public void add(Process... processes) { // adds processes to the simulation

		for (int i = 0; i < processes.length; i++) {
			this.processList.add(currentId, processes[i]);
			processes[i].setId(currentId);
			currentId++;
		}

	}

	public void incrementTime() { // increments time of processes
									// and checks if messages were send

		this.time++;
		incrementTimeProcesses();
		checkMessages();
		receiveMessages();

	}

	private void receiveMessages() {
		// writes Messages into the Channelstate of processes
		List<SimulationEvent> events = eventList.getEvents(time);
		for (int i = 0; i < events.size(); i++) {
			
			SimulationEvent event = events.get(i);
			if (event.isReceive()) {
				System.out.println(time);

				event.getMessage().destination.receiveMessage(event.getMessage());
			}
		}

	}

	private void checkMessages() { // checks if messages were send and adds
									// Events

		for (int i = 0; i < this.processList.size(); i++) {

			if (this.processList.get(i).messageSend()) {

				List<Message> sendMessages = this.processList.get(i).sendMessage();

				for (int k = 0; k < sendMessages.size(); k++) {
					SimulationEvent send = new SimulationEvent(true, sendMessages.get(k), time);
					SimulationEvent receive = new SimulationEvent(false, sendMessages.get(k),
							time + sendMessages.get(k).getTravelTime());
					this.eventList.add(send);
					this.eventList.add(receive);
				}

			}
		}

	}

	private void incrementTimeProcesses() { // One tick for every process

		for (int i = 0; i < this.processList.size(); i++) {

			this.processList.get(i).incrementTime();

		}
	}

	public EventList getEventList() {

		return this.eventList;
	}

	public Process getSelected() {
		return selectedProcess;
	}

	public void setSelected(Process process) { // sets the process, that got
												// selected by the
		if (this.selectedProcess != null) {
			this.selectedProcess.setSelected(false);
			this.selectedProcess = process;

		}
		this.selectedProcess = process;
	}

	public SimulationVisual getSimulationVisual() {
		return simulationVisual;
	}

	public void setSimulationVisual(SimulationVisual simulationVisual) {
		this.simulationVisual = simulationVisual;
	}

}
