package ch.bfh.cssem.lambdaexpressions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class JavaFXClass implements Initializable {

	TextArea outputTextArea = new TextArea();
	Button clickButton1 = new Button();
	Button clickButton2 = new Button();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		clickButton1.setOnAction(event -> {
			// append text when clickButton1 is clicked
			outputTextArea.appendText("Button clicked");
		});

		clickButton1.setOnAction(this::handleButtonAction);
		clickButton2.setOnAction(this::handleButtonAction);
	}

	private void handleButtonAction(ActionEvent event) {
		// append text when clickButton1 is clicked
		outputTextArea.appendText("Button clicked");
	}
}
