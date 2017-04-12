import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class ChannelVisual extends Path {

	
	private Channel channel;
	
	public ChannelVisual (Channel channel, int[] coordinates){
		super();
		
		this.channel = channel;
		
		int id1 = 2*channel.getSender().getId();
		int id2 = 2*channel.getReceiver().getId();
		
		int x1 = coordinates[id1];
		int y1 = coordinates[id1+1];
		
		int x2 = coordinates[id2];
		int y2 = coordinates[id2+1];
		
//		int pitch = ((y1-y2)/(x1-x2));
//
//
		this.getElements().add(new MoveTo(x1, y1));
		this.getElements().add(new LineTo(x2, y2));
//		
//		this.getElements().add(new MoveTo((x1 + 0.8*(x1-x2))/2, (y1 + x1*pitch)));
//
//		this.getElements().add(new LineTo(200,200));

		
	}
	
}
