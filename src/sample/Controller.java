package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML TextField addTaskField;
    @FXML Label taskDoneLabel;
    @FXML Button addTaskBtn;
    @FXML VBox vbox;
    @FXML FlowPane monthPane;
    private int tasksAdded = 0;
    private int tasksDone = 0;
    @FXML ProgressBar progressBar;
    @FXML FlowPane yearPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatLabel();
        initializeTaskFieldListener();
        initializeMonthProgress();
    }


    private void updatLabel(){
        taskDoneLabel.setText((tasksAdded) + "/" + (tasksDone));
    }



    /**
     * Used for Sending task for initializtion with enter button, then clearing window
     */
    private void initializeTaskFieldListener(){
        addTaskField.setOnAction(e -> {
            addToTaskScheduelVBox();
            addTaskField.clear();
        });
    }

    /**
     * Used for adding tasks to the scheduele, by first adding listener then the vBox
     */
    @FXML private void addToTaskScheduelVBox(){
        CheckBox newBox = new CheckBox(addTaskField.getText());
        newBox.getStyleClass().add("cText");
        newBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                tasksDone++;
                newBox.setStyle("-fx-opacity: 0.5");
                newBox.setDisable(true);
                //vbox.getChildren().remove(newBox);
                updatLabel();
            }
        });
        vbox.getChildren().add(newBox);
        tasksAdded++;
        updatLabel();
    }

    private void initializeMonthProgress(){
        monthPane.getChildren().add(new Goal());
    }


    /**
     * Todo -- Spara ner Data varje dag till ett Excel / Databas / TextDokument
     * Todo -- Fixa GoalItems så att de syns i Den andra viewen
     * Todo -- Fixa så vi får en ny Panel där vi kan skriva i Loggbok
     * Todo -- Fixa LoggboksPane för
     * Todo - Möjligtvis även en VisionBoard Pane?
     * Todo - Rearrangea färdiga Tasks så de hamnar längst ned
     * Todo - Fixa en Pane för Att lägga in kosten
     * Todo - Lägg in ShoppingLista -> Se till så den kan pusha till google docs.
     */
}
