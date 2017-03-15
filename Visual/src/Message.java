public abstract class Message {

	
	
	protected Process start, destination;
	protected int travelTime, processTime;
	protected String message;
	protected boolean arrived = false, finished = false, markerFlag;

	

public int getTravelTime() {

		return this.travelTime;
	}

	public Process getStart() {
		return this.start;
	}

	public int travelTime() {
		return this.travelTime;
	}

	public Process getDestination() {

		return this.destination;
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
				+ Integer.toString(processTime) + "Destination: " + Integer.toString(destination.getId());
	}

	public boolean isMarker() {
		return markerFlag;
	}

	

}