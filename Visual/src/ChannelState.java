import java.util.ArrayList;
import java.util.List;

public class ChannelState {
	
	private Channel channel;
	private List<Message> messageList = new ArrayList<Message>();
	
	
	public ChannelState(Channel channel){
		
		this.channel=channel;
		
	}
	
	public void addMessage(Message message){
		
		this.messageList.add(message);
	}

	public Channel getChannel(){
		return channel;
	};
}
