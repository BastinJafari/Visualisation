
public class MarkerMessage extends Message {

	
	
	public MarkerMessage() {

	}

	public MarkerMessage(String message, int travelTime, int processTime, Process sender, Process receiver) {
		this.message = message;
		this.travelTime = travelTime;
		this.processTime = processTime;
		this.receiver = receiver;
		this.sender = sender;
		this.markerFlag = true;
	}
}
