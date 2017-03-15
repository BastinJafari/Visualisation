import java.util.List;

public class Channel {

	
	private Process sender;
	private Process receiver;
	
	public Channel(Process sender, Process receiver){
		
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public Process getSender(){
		
		return this.sender;
	}
	
	public Process getReceiver(){
		
		return this.receiver;
	}
}
