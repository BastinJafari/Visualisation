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

public class MainClass extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Processsimulation V 0.1");

		Pane root = new Pane();
		primaryStage.setScene(new Scene(root, 800, 800));
		primaryStage.show();

		int numberOfProcesses = 5;
		double buttonX = 300;
		double buttonY = 300;
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

		SimulationVisual simulationVisual = new SimulationVisual(simulation);
		simulation.setSimulationVisual(simulationVisual); // for the
															// communication of
															// simulation with
															// sv

		root.getChildren().add(simulationVisual);
		// Visualisation of channels

	

		// Button for sending control messages
		Button sendControl = new Button("Make Snapshot");

		sendControl.setLayoutX(buttonX);
		sendControl.setLayoutY(buttonY);
		sendControl.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (simulation.getSelected() != null) {
					simulation.getSelected().takeSnapShot();

				}

			}
		});

		root.getChildren().add(sendControl);

	}


}
