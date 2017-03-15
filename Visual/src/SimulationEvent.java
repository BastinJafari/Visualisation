
public class SimulationEvent {

	private int sendTime;
	private int receiveTime;
	private boolean isSend;
	private boolean isReceive;
	private Message message;

	
	public SimulationEvent(boolean isSend, Message message) {

		this.isSend = isSend;
		this.isReceive = !isSend;
		this.message = message;
		this.sendTime = 0;}
	
	public SimulationEvent(boolean isSend, Message message, int time) {

		this.isSend = isSend;
		this.isReceive = !isSend;
		this.message = message;
		this.sendTime = time;
		this.receiveTime = time + message.getTravelTime();
	}

	public boolean isSend() {

		return isSend;
	}

	public boolean isReceive() {

		return isReceive;
	}

	public int getSendTime() {
		return sendTime;
	}
	public int getReceiveTime(){
		
		return receiveTime;
	}

	public Message getMessage() {
		return message;
	}

	

	
}
