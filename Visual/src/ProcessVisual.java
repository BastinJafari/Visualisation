import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;


public class ProcessVisual extends Group {

	final double x;
	final double y;
	final double radius = 20;
	private Circle circle;
	private Process process;
	private int id;
	private SimulationVisual simulationVisual;
	Text text;
	public ProcessVisual(SimulationVisual simulationVisual, Process process, double x, double y) { // x and y for
																// coordinates
		super();
		this.simulationVisual = simulationVisual;
		this.x = x;
		this.y = y;
		this.circle = new Circle(x, y, radius, Color.ORANGE);
		this.process = process;
		id = this.process.getId();

		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent me) {
				if (!process.isSelected()) {
					changeColor(Color.ORANGE);
				}

			}
		});

		this.setOnMouseEntered(new EventHandler<MouseEvent>() { // if mouse
																// hovered over
																// the color
																// changes

			public void handle(MouseEvent me) {
				changeColor(Color.BROWN);

			}
		});

		this.setOnMousePressed(new EventHandler<MouseEvent>() { // if mouse
																// clicked over
																// the color
																// changes and
																// the node is
																// selected

			public void handle(MouseEvent me) {
				
				if(process.isSelected()){
					changeColor(Color.ORANGE);
					process.setSelected(false);
				}
				else{
				process.setSelected(true);

				changeColor(Color.BROWN);
				simulationVisual.getstateVisual().visualizeProcess();
				}
				

			}
		});
		
		//setting the process number in the middle
		
		this.text   = createText();
		this.getChildren().addAll(circle, text);

	}

	

	private Text createText() {
		
		String number = Integer.toString(process.getId());
		Text text = new Text(number);

        text.setFont(new Font(15));
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setLayoutX(x);
        text.setLayoutY(y);

        return text;
	}

	private void changeColor(Color color) {

		circle.setFill(color);
	}

	public Process getProcess() {
		return process;
	}

	public double getCenterX() {
		// TODO Auto-generated method stub
		return x;
	}

	public double getCenterY() {
		// TODO Auto-generated method stub
		return y;
	}

}
