import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SimulationVisual extends Group {

	double destroyListCounter = 0;  //to control the size of the destroylist
	private Pane root;
	private int time = 0; // to have a delay with the simulation
	private List<ProcessVisual> processVisualList;
	private Simulation simulation;
	private Timeline timeline;
	private List<List<MessageVisual>> destroyList = new ArrayList<List<MessageVisual>>(); // list
																							// of
																							// messages
																							// to
																							// destroy
																							// at
																							// a
																							// certain
																							// point
																							// in
																							// time
	int x1;
	int y1;
	int x2;
	int y2;
	int x3;
	int y3;
	int x4;
	int y4;
	int x5;
	int y5;

	public SimulationVisual(Simulation simulation, Pane root) {
		
		x1 = 30;
		y1 = 30;
		x2 = 70; // setting the x and y for each individual node
		y2 = 190;
		x3 = 100;
		y3 = 100;
		x4 = 190;
		y4 = 130;
		x5 = 180;
		y5 = 35;
		int[] coordinates = { x1, y1, x2, y2, x3, y3, x4, y4, x5, y5 };
		this.root = root;
		this.simulation = simulation;
		this.processVisualList = new ArrayList<ProcessVisual>();
		this.timeline = simulation.getTimeline();
		KeyFrame keyframe = new KeyFrame(Duration.millis(1000), ae -> this.incrementTime()); //
		timeline.getKeyFrames().add(keyframe);

		for (int i = 0; i < simulation.getProcessList().size(); i++) { // setting
																		// representations
																		// of
			// processes // processes and channels

			int k = i * 2;
			Process p = simulation.getProcessList().get(i);
			ProcessVisual pv = new ProcessVisual(p, coordinates[k], coordinates[k + 1]);

			this.processVisualList.add(pv);

			// setting channel representation
			List<ChannelVisual> channelVisualList = new ArrayList<ChannelVisual>(); // list
																					// of
																					// all
																					// channelvisuals
																					// to
																					// add
																					// to
																					// root

			root.getChildren().addAll(channelVisualList);
			System.out.println(root.getChildren().indexOf(channelVisualList));

		}

		root.getChildren().addAll(processVisualList);

		for (int i = 0; i < 100; i++) { // fills the destroylist with 100
										// timeslots
			List<MessageVisual> messageVisualToDestroylist = new ArrayList<MessageVisual>();
			destroyList.add(messageVisualToDestroylist);
		}
	}

	public List<ProcessVisual> getProcessVisualList() {

		return this.processVisualList;
	}

	public void incrementTime() { // increments time and visualizes messages
		this.time++;

		visualizeMessages();
		destroyMessages();

	}

	private void destroyMessages() { // destroys all visualisations of messages,
										// that arrived and checks if destroylist must get bigger
		destroyListCounter++;
		List<MessageVisual> messageVisualsToDestroy = destroyList.get(time);
		int size = messageVisualsToDestroy.size();

		for (int i = 0; i < size; i++) {

			root.getChildren().remove(messageVisualsToDestroy.get(i));
		}
		
		if (destroyListCounter > (destroyList.size()/2) ){
			expandDestroyList();
		}
	}

	private void visualizeMessages() { // visualizes all send messages at a
										// certain time and

		
		List<SimulationEvent> simulationEvents = simulation.getEventList().getEvents(time); // gets
																							// all
																							// Events
																							// for
																							// a
																							// certain
																							// time
		int size = simulationEvents.size();

		List<MessageVisual> messageVisualList = new ArrayList<MessageVisual>();
		for (int i = 0; i < size; i++) {

			if (simulationEvents.get(i).isSend()) {
				Message message = simulationEvents.get(i).getMessage();
				MessageVisual messageVisual = new MessageVisual(message, this);
				messageVisualList.add(messageVisual);
				int destroytime = time + message.getTravelTime(); // adding the
																	// messageVisual
																	// into the
																	// destroylist
				destroyList.get(destroytime).add(messageVisual);
				
			}
		}


		root.getChildren().addAll(messageVisualList);

	}

	public void visualizeProcess(Process process, double x, double y) {

		ProcessVisual pv = new ProcessVisual(process, x, y);
		this.processVisualList.add(pv);
		root.getChildren().add(pv);

	}

	public Pane getRoot() {
		return this.root;
	}

	private void expandDestroyList(){ // if Destroylist gets too big it fills it up
									// with additional time slots
	
		for (int i = destroyList.size(); i < destroyList.size() + 100; i++) {
			List<MessageVisual> messageVisualToDestroylist = new ArrayList<MessageVisual>();
			destroyList.add(messageVisualToDestroylist);
		}
	}
}
