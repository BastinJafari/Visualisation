
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class NormalMessage extends Message {

	public NormalMessage() {
	}

	public NormalMessage(String message, int travelTime, int processTime, Process sender, Process receiver) {
		this.message = message;
		this.travelTime = travelTime;
		this.processTime = processTime;
		this.receiver = receiver;
		this.sender = sender;
		this.markerFlag = false;
	}



}