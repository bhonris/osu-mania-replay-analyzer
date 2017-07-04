import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Trainer extends Application implements EventHandler<ActionEvent> {
	
	Stage window;
	Scene scene1, scene2; 
	Button browseB, analyzeB;
	TextField directory;
	Label mode;
	File file, initDirectory;
	
	ReplayData rpData;


	Path path;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception  {
		window = primaryStage;
		window.setTitle("4ksrub's Mania Analyzer");
		
		initDirectory = new File("C:/");
		
		directory = new TextField();
		mode = new Label();
		
		browseB = new Button("Browse");
		browseB.setOnAction(this);
		analyzeB = new Button("Analyze");
		analyzeB.setOnAction(this);
		

		VBox layout1 = new VBox();
		layout1.getChildren().add(directory);
		layout1.getChildren().add(browseB);
		layout1.getChildren().add(analyzeB);
		layout1.getChildren().add(mode);
		
		
		Scene scene = new Scene(layout1, 300, 250);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void handle(ActionEvent event) {
		//event.getSource();
		//System.out.println(((Button)event.getSource()).getText());
		switch(((Button)event.getSource()).getText()){
			case "Browse":
				System.out.println("Browsin'");
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				fileChooser.setInitialDirectory(initDirectory);
				try{
					file = fileChooser.showOpenDialog(window);
					directory.setText(file.getPath());
					path = Paths.get(file.getPath());
				}catch (NullPointerException e){
					
				}

				break;
			case "Analyze":
				System.out.println("Analyzin");
				try {
					rpData = new ReplayData(Files.readAllBytes(path));
					System.out.println("attempting to analyze");
					if(rpData.mode!=3){
						throw new IOException();
					}
					mode.setText(rpData.mode.toString());
					
				} catch (IOException e) {
					mode.setText("Not a valid osu!mania file");
				}catch (NullPointerException np){
					mode.setText("No file found"); 
				};
				break;
			default:
				System.out.println("Error!");
		}
	}

}
