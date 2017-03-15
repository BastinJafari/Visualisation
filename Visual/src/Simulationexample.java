import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Simulationexample extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	
	//TEST TEST // Halloballo
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Processsimulation V 0.1");

		Pane root = new Pane();
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();

		int numberOfProcesses = 5;
		double buttonX = 300;
		double buttonY = 300;
		Text selectedProcess;
		Simulation simulation = new Simulation();
		
		Process p1 = new Process(simulation);
		Process p2 = new Process(simulation);
		Process p3 = new Process(simulation);
		Process p4 = new Process(simulation);
		Process p5 = new Process(simulation);

		p1.connect(p2);
		p1.connect(p3);
		p3.connect(p4);
		p5.connect(p1);
		
		simulation.add(p1, p2, p3, p4, p5);

		SimulationVisual simulationVisual = new SimulationVisual(simulation, root);
		simulation.setSimulationVisual(simulationVisual);  //for the communication of simulation with sv

		// Visualisation of channels

		for (int i = 0; i < numberOfProcesses; i++) {

			List<Channel> channelList = simulation.getProcessList().get(i).getOutGoingChannels();

			for (int k = 0; k < channelList.size(); k++) {

				new ChannelVisual(channelList.get(k), simulationVisual);
			}

		}
		
		
		// Button for sending control messages
		
		Button sendControl = new Button("Send");
		
		

		sendControl.setLayoutX(buttonX );
		sendControl.setLayoutY(buttonY );
		sendControl.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        simulation.getSelected().sendMarker();
		    	
		    	

		    }
		});
		root.getChildren().add(sendControl);


	}
	
	
}
