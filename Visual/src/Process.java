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
	private List<Message> messagesInProcessList = new ArrayList<Message>(); // A
																			// List
	// with all
	// messages
	// that came

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

		if (markerSend) {
			System.out.println("MARKERASENASD");

			if (outGoingChannels.size() > 0) {
				for (int i = 0; i < outGoingChannels.size(); i++) {

					MarkerMessage marker = new MarkerMessage("This is a Marker Message", travelTime, processTime, this,
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
				NormalMessage message = new NormalMessage(
						"this message is coming from Process" + Integer.toString(getId()), randomTravelTime,
						randomProcessTime, this, outGoingChannels.get(randomProcess).getReceiver());
				messagesSend.add(message);
			}
		}

		//if there was no marker command send normal message
		if (outGoingChannels.size() > 0 && !markerSend) {

			int randomProcess = ThreadLocalRandom.current().nextInt(0, outGoingChannels.size()); // it
			// picks
			// one
			// of
			// the
			// connections
			NormalMessage message = new NormalMessage("this message is coming from Process" + Integer.toString(getId()),
					travelTime, processTime, this, outGoingChannels.get(randomProcess).getReceiver());
			messagesSend.add(message);
		}

		if (outGoingChannels.size() == 0) {
			NormalMessage noMessage = new NormalMessage("no connections", 0, 0, this, this);
			messagesSend.add(noMessage);
			return messagesSend;
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
System.out.println("receive");
		if (message.isMarker()) {

			this.localSnapshotTaken = true;
			sendMarker();
			System.out.println("ASDASD");

		}

		this.messagesInProcessList.add(message);
	}

	public void incrementTime() {

		this.time++;
		if (!outGoingChannels.isEmpty()) {
			dice(); // sending message or not
		}
	}

	public void dice() {

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

}
