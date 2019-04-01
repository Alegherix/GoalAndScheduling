package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Goal extends AnchorPane {



    private int progressGoal = 150;
    private int currentProgress = 35;
    @FXML ProgressBar progressBar;
    @FXML Button incButton;

    public Goal() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("goal.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateProgressBar();
        //initializeButton();
    }

    public void initializeButton(){
        incButton.onMouseClickedProperty().addListener((observable, oldValue, newValue) -> {
            increaseProgress();
        });
    }

    @FXML
    public void increaseProgress(){
        progressGoal++;
        updateProgressBar();
    }

    public void updateProgressBar(){
        double progress = (double)currentProgress/progressGoal;
        progressBar.setProgress(progress);
    }


}
