
import java.util.Random;

public class NormalMessage extends Message {

	
	public NormalMessage() {

	}

	public NormalMessage(String message, int travelTime, int processTime, Process start, Process destination) {
		this.message = message;
		this.travelTime = travelTime;
		this.processTime = processTime;
		this.destination = destination;
		this.start = start;
		this.markerFlag = false;
	}



	

}
