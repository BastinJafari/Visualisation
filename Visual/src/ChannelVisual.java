import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;

public class ChannelVisual extends Path {

	
	//DIT MUSSTE NOCH LÖSEN°!
	private Channel channel;
	
	//todo channelvisual
	public ChannelVisual (Channel channel, SimulationVisual simulationvisual){
		super();
		
		double x1 = simulationvisual.getProcessVisualList().get(channel.getSender().getId()).getCenterX();
		double y1 = simulationvisual.getProcessVisualList().get(channel.getSender().getId()).getCenterY();
		double x2 = simulationvisual.getProcessVisualList().get(channel.getReceiver().getId()).getCenterX();
		double y2 = simulationvisual.getProcessVisualList().get(channel.getReceiver().getId()).getCenterY();

		this.channel = channel;

		this.getElements().add(new MoveTo(x1, y1));
		this.getElements().add(new LineTo(x2, y2));
		simulationvisual.getRoot().getChildren().add(this);
	}
	



	

	

	
}
