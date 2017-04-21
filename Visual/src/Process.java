import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.*;

// A single Process 

public class Process {

	private Simulation simulation; // to communicate it's state to the
									// simulation

	private boolean isSelected = false; // if selected by mouse
	private boolean random = false; // if random travelTime and Processtime
	private int travelTime = 5;
	private int processTime = 5;
	private int chanceSendMessage; // Chance of sending a message
	private Boolean messageSend = false, localSnapshotTaken = false, markerSend = false;
	private int id;
	private int time; // The time that passed
	private ProcessState processState = new ProcessState(this); // list of
																// messages that
	// came
	private List<ChannelState> channelStates = new ArrayList<ChannelState>();
	private ProcessState snapShot;

	private List<Channel> outGoingChannels = new ArrayList<Channel>(); // A list
																		// with
																		// all
																		// outgoing
																		// channels

	private List<Channel> incommingChannels = new ArrayList<Channel>();

	public Process(Simulation simulation) {

		this.simulation = simulation;
		this.time = 0;
		this.chanceSendMessage = ThreadLocalRandom.current().nextInt(5, 7);

	}

	public void getConnected(Channel channel) {

		this.incommingChannels.add(channel);
	}

	public void connect(Process receiver) { // adds an outgoing channel

		Channel channel = new Channel(this, receiver);
		receiver.getConnected(channel);
		outGoingChannels.add(channel);

	}

	public void setId(int id) {

		this.id = id;
	}

	// returns a list of all send messages at a certain time
	public List<Message> sendMessage() {
		List<Message> messagesSend = new ArrayList<Message>();

		// if it should send marker messages

		if (markerSend) { // sending marker messages through all outgoing
							// channels

			if (outGoingChannels.size() > 0) {
				for (int i = 0; i < outGoingChannels.size(); i++) {

					MarkerMessage marker = new MarkerMessage("Marker Message", travelTime, processTime, this,
							outGoingChannels.get(i).getReceiver());
					messagesSend.add(marker);

				}

			}
		}

		// if there should be a random travel time
		if (random) {

			if (outGoingChannels.size() > 0) {

				int randomTravelTime = ThreadLocalRandom.current().nextInt(1, 11); // random
																					// travel
																					// time
				int randomProcessTime = ThreadLocalRandom.current().nextInt(1, 11); // random
																					// process
																					// time
				int randomProcess = ThreadLocalRandom.current().nextInt(0, outGoingChannels.size()); // it
				// picks
				// one
				// of
				// the
				// connections
				NormalMessage message = new NormalMessage("Normal Message", randomTravelTime, randomProcessTime, this,
						outGoingChannels.get(randomProcess).getReceiver());
				messagesSend.add(message);
			}
		}

		// if there was no marker command send normal message
		if (outGoingChannels.size() > 0 && !markerSend) {

			int randomProcess = ThreadLocalRandom.current().nextInt(0, outGoingChannels.size()); // it
			// picks
			// one
			// of
			// the
			// connections
			NormalMessage message = new NormalMessage("Normal Message",
					travelTime, processTime, this, outGoingChannels.get(randomProcess).getReceiver());
			messagesSend.add(message);
		}

		if (outGoingChannels.size() == 0) {
		
			return null;
		}
		this.messageSend = false;
		markerSend = false;
		return messagesSend;
	}

	public void sendMarker() { // sets marker bit so next sending
								// process it sends out marker messages

		markerSend = true;
		messageSend = true;

	}

	public void receiveMessage(Message message) {

		if (message.isMarker()) {

			takeSnapShot();
			channelStates.add(new ChannelState(getChannel(message)));

		}

		processState.add(message);
		addToChannelStates(message);

	}

	private void addToChannelStates(Message message) { // adds a message to a
														// channelstate, if a
														// marker came through

		for (int i = 0; i < channelStates.size(); i++) {

			if (channelStates.get(i).getChannel() == getChannel(message)) {

				channelStates.get(i).addMessage(message);
			}

		}

	}

	public void incrementTime() {

		this.time++;
		if (!outGoingChannels.isEmpty()) {
			dice(); // sending message or not
		}
	}

	private void dice() {

		if (ThreadLocalRandom.current().nextInt(1, this.chanceSendMessage + 1) == 1) { // chance
																						// of
																						// sending
																						// message

			this.messageSend = true;
		}
	}

	public int getId() {
		return id;
	}

	public boolean messageSend() {
		return this.messageSend;
	}

	public String toString() {

		String channels = new String();
		channels = channels + "ASD";
		return "Process iD: " + getId();

	}

	public List<Channel> getOutGoingChannels() {
		return outGoingChannels;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) { // sets the selected to this
													// process and delets the
													// other
		this.isSelected = isSelected;
		if (isSelected) {

			simulation.setSelected(this);

		}
	}

	public void takeSnapShot() { // takes snapshot of process

		localSnapshotTaken = true;
		sendMarker();
		snapShot = processState.clone();
		
	}

	private Channel getChannel(Message message) { // finds out the Channel of a
													// message
		Channel channel = null;

		for (int i = 0; i < incommingChannels.size(); i++) {
			if (incommingChannels.get(i).getSender() == message.getSender()) {

				channel = incommingChannels.get(i);
			}
		}
		return channel;
	}

	public ProcessState getProcessState() {
		return processState;
	}
	
	public Boolean localSnapshotTaken(){
		return localSnapshotTaken;
	}
	
	public ProcessState getSnapShot(){
		if(snapShot != null){
			
			return snapShot;

		}
		else{
			return null;
			
		}
	}
}
