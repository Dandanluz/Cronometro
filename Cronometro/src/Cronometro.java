import javafx.util.Duration;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.Insets;

public class Cronometro extends Application{

    private Label timeLabel = new Label("00:00:00");
    private int secondsElapsed = 0;
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Cronômetro");

        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);

        timeLabel.setId("timeLabel");
        root.getChildren().add(timeLabel);

        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Button startButton = new Button("Iniciar");
        startButton.setMinSize(100, 50);
        startButton.setId("startButton");
        startButton.setOnAction(e -> startTimer());

        Button pauseButton = new Button("Pausar");
        pauseButton.setMinSize(100, 50);
        pauseButton.setId("pauseButton");
        pauseButton.setOnAction(e -> pauseTimer());

        Button resetButton = new Button("Resetar");
        resetButton.setMinSize(100, 50);
        resetButton.setId("resetButton");
        resetButton.setOnAction(e -> resetTimer());

        hbox.getChildren().addAll(startButton, pauseButton, resetButton);
        root.getChildren().addAll(hbox);

        Scene scene = new Scene(root, 400, 200);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startTimer(){
        if(timeline == null || timeline.getStatus() != Timeline.Status.RUNNING){
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }
    private void updateTimer(){
        secondsElapsed++;
        updateTimerDisplay();
    }
    private void updateTimerDisplay(){
        int hours = secondsElapsed / 3600;
        int minutes = (secondsElapsed % 3600) / 60;
        int seconds = secondsElapsed % 60;

        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
    private void pauseTimer(){
        if(timeline != null){
            timeline.pause();
        }
    }
    private void resetTimer(){
        if(timeline != null){
            timeline.stop();
        }
        secondsElapsed = 0;
        updateTimerDisplay();
    }

public static void main(String[] args) {
        launch(args);
    }
}
