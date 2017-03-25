public abstract class Message {

	
	
	protected Process sender, receiver;
	protected int travelTime, processTime;
	protected String message;
	protected boolean arrived = false, finished = false, markerFlag;

	

public int getTravelTime() {

		return this.travelTime;
	}

	public Process getSender() {
		return this.sender;
	}

	public int travelTime() {
		return this.travelTime;
	}

	public Process getReceiver() {

		return this.receiver;
	}


	public Boolean isFinished() {

		return this.finished;
	}


	public boolean arrived() {
		return this.arrived;
	}

	public String getMessage() {

		return message;
	}

	public String toString() {

		return "Message: " + getMessage() + "Traveltime: " + Integer.toString(travelTime) + "Processtime:"
				+ Integer.toString(processTime) + "Destination: " + Integer.toString(receiver.getId());
	}

	public boolean isMarker() {
		return markerFlag;
	}

	

}