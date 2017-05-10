import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;;

public class MessageVisual extends Circle {

	private static final double radius = 10;
	private Message message;
	private Path path;

	public Message getMessage() {
		return this.message;

	}

	public MessageVisual(){}

	public MessageVisual(Message message, SimulationVisual simulationVisual) {
		

		super(simulationVisual.getProcessVisualList().get(message.getSender().getId()).getCenterX(), simulationVisual.getProcessVisualList().get(message.getSender().getId()).getCenterY(), radius);

	
		//sets Start and Destination coordinates
		double x1 = simulationVisual.getProcessVisualList().get(message.getSender().getId()).getCenterX();
		double y1 = simulationVisual.getProcessVisualList().get(message.getSender().getId()).getCenterY();
		double x2  = simulationVisual.getProcessVisualList().get(message.getReceiver().getId()).getCenterX();;
		double y2 = simulationVisual.getProcessVisualList().get(message.getReceiver().getId()).getCenterY();;

		
		if(message.isMarker()){
			
			this.setFill(Color.BLUE);
		}
		
		this.message = message;
		this.path = new Path();

		path.getElements().add(new MoveTo(x1, y1));
		path.getElements().add(new LineTo(x2, y2));
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(message.getTravelTime()));
		pathTransition.setPath(path);
		pathTransition.setNode(this);
		pathTransition.play();

	}


	
	
}