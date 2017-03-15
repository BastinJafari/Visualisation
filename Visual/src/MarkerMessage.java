
public class MarkerMessage extends Message {

	
	
	public MarkerMessage() {

	}

	public MarkerMessage(String message, int travelTime, int processTime, Process start, Process destination) {
		this.message = message;
		this.travelTime = travelTime;
		this.processTime = processTime;
		this.destination = destination;
		this.start = start;
		this.markerFlag = true;
	}
}
