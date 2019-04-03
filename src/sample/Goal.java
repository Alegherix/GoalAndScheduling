package sample;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Goal extends AnchorPane {



    private int progressGoal = 50;
    private int currentProgress = 35;
    @FXML ProgressBar progressBar;
    @FXML Button incButton;
    @FXML Label percentageLabel;

    public Goal() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("goal.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateProgress();
        //initializeButton();
    }

    public void initializeButton(){
        incButton.onMouseClickedProperty().addListener((observable, oldValue, newValue) -> {
            increaseProgress();
        });
    }

    @FXML
    public void increaseProgress(){
        currentProgress++;
        updateProgress();
    }

    @FXML
    public void decreaseProgress(){
        currentProgress--;
        updateProgress();
    }

    private boolean validChange(){
        return currentProgress < progressGoal && currentProgress >= 0;
    }

    public void updateProgress(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        double progressDone = (double)currentProgress/progressGoal;
        double progressDonePercent = progressDone * 100;

        percentageLabel.setText(df.format(progressDonePercent) + "%");
        progressBar.setProgress(progressDone);
    }

    private void initializeGoals(){
        Gson gson = new Gson();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader("Goals.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
