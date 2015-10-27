package ch.bfh.cssem.lambdaexpressions;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class JavaFXClass extends Application {

	public static void main(String... args) {

		Application.launch(args);
	}

	public static final double MIN_WIDTH = 350.;
	public static final double MAX_WIDTH = 500.;

	public static final double MIN_HEIGHT = 250.;
	public static final double MAX_HEIGHT = JavaFXClass.MAX_WIDTH;

	public static final int ASYNC_SECONDS_MIN = 1;
	public static final int ASYNC_SECONDS_ABORT = 3;
	public static final int ASYNC_SECONDS_MAX = 5;

	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	private final GridPane layout;

	private final Label sizeLabel;

	private int counter = 0;
	private final Button counterButton;
	private final Label counterLabel;

	private final ExecutorService asyncExecutor = Executors.newCachedThreadPool();
	private final Button asyncButton;
	private final Label asyncLabel;

	public JavaFXClass() {

		this.layout = new GridPane();
		this.layout.getColumnConstraints().addAll(new ColumnConstraints(0., Double.NaN, Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true),
																							new ColumnConstraints(JavaFXClass.MIN_WIDTH / 3., Double.NaN, Double.MAX_VALUE, Priority.NEVER, HPos.CENTER, true),
																							new ColumnConstraints(JavaFXClass.MIN_WIDTH / 3., Double.NaN, Double.MAX_VALUE, Priority.NEVER, HPos.CENTER, true),
																							new ColumnConstraints(0., Double.NaN, Double.MAX_VALUE, Priority.ALWAYS, HPos.RIGHT, true));
		this.layout.getRowConstraints().addAll(new RowConstraints(0., Double.NaN, Double.MAX_VALUE, Priority.ALWAYS, VPos.TOP, true),
																					 new RowConstraints(JavaFXClass.MIN_HEIGHT / 5., Double.NaN, Double.MAX_VALUE, Priority.NEVER, VPos.CENTER, true),
																					 new RowConstraints(JavaFXClass.MIN_HEIGHT / 5., Double.NaN, Double.MAX_VALUE, Priority.NEVER, VPos.CENTER, true),
																					 new RowConstraints(JavaFXClass.MIN_HEIGHT / 5., Double.NaN, Double.MAX_VALUE, Priority.NEVER, VPos.CENTER, true),
																					 new RowConstraints(0., Double.NaN, Double.MAX_VALUE, Priority.ALWAYS, VPos.BOTTOM, true));

		this.layout.add(this.sizeLabel = new Label("n/a"), 1, 1, 2, 1);

		this.layout.add(this.counterButton = new Button("increment counter"), 1, 2);
		this.layout.add(this.counterLabel = new Label("press button"), 2, 2);
		this.counterButton.setOnAction(e -> this.counterLabel.setText(String.format("counter value: %d", ++this.counter)));

		this.layout.add(this.asyncButton = new Button("run async task"), 1, 3);
		this.layout.add(this.asyncLabel = new Label("idle"), 2, 3);
		this.asyncButton.setOnAction(this::asyncButtonHandler);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("CSSem - Lambda Expression Examples");

		primaryStage.setMinWidth(JavaFXClass.MIN_WIDTH);
		primaryStage.setMaxWidth(JavaFXClass.MAX_WIDTH);
		primaryStage.setMinHeight(JavaFXClass.MIN_HEIGHT);
		primaryStage.setMaxHeight(JavaFXClass.MAX_HEIGHT);

		primaryStage.setScene(new Scene(this.layout));

		Runnable stageResized = () -> this.stageResized(primaryStage.getWidth(), primaryStage.getHeight());
		ChangeListener<? super Number> widthHeightListener = (v, o, n) -> stageResized.run();
		primaryStage.widthProperty().addListener(widthHeightListener);
		primaryStage.heightProperty().addListener(widthHeightListener);

		primaryStage.show();
		primaryStage.centerOnScreen();
		stageResized.run();
	}

	private void stageResized(double width, double height) {

		this.sizeLabel.setText(String.format("window size: %.0fx%.0f", width, height));
	}

	private void asyncButtonHandler(ActionEvent event) {

		this.asyncButton.setDisable(true);
		this.asyncLabel.setText(String.format("started at %s", LocalTime.now().format(JavaFXClass.TIME_FORMATTER)));

		new Thread(() -> {

			Future<LocalTime> task = this.asyncExecutor.submit(this::asyncTask);

			try {
				LocalTime finished = task.get(JavaFXClass.ASYNC_SECONDS_ABORT, TimeUnit.SECONDS);

				Platform.runLater(() -> {
					this.asyncLabel.setText(String.format("finished at %s", finished.format(JavaFXClass.TIME_FORMATTER)));
					this.asyncButton.setDisable(false);
				});

			} catch (TimeoutException ex) {
				Platform.runLater(() -> {
					this.asyncLabel.setText(String.format("aborted after %ss", JavaFXClass.ASYNC_SECONDS_ABORT));
					this.asyncButton.setDisable(false);
				});

			} catch (ExecutionException | InterruptedException ex) {
				ex.printStackTrace();
			}
		}).start();
	}

	private LocalTime asyncTask() {

		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(JavaFXClass.ASYNC_SECONDS_MAX - JavaFXClass.ASYNC_SECONDS_MIN + 1) + JavaFXClass.ASYNC_SECONDS_MIN);

		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		return LocalTime.now();
	}
}
