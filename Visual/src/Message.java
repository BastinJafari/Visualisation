import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Message implements java.io.Serializable{

	
	
	protected Process sender, receiver;
	protected int travelTime, processTime;
	protected String message;
	protected boolean arrived = false, finished = false, markerFlag;

	

	
	public void test(){
		
		this.message = "Test";
	}
	
public Message copy() {
		
		String stringCopy = this.message;
		Message copy = new NormalMessage(stringCopy, travelTime,  processTime,  sender,  receiver);
		copy.arrived = this.arrived;
		copy.finished = this.finished;
		copy.markerFlag = this.markerFlag;
		return copy;
}
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

		return getMessage() + " Traveltime: " + Integer.toString(travelTime) + " Processtime: "
				+ Integer.toString(processTime) +" Sender: " + Integer.toString(sender.getId()) + " Receiver: " + Integer.toString(receiver.getId());
	}

	public boolean isMarker() {
		return markerFlag;
	}

	

}