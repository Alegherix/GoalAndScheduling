package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML TextField addTaskField;
    @FXML Label taskDoneLabel;
    @FXML Button addTaskBtn;
    @FXML VBox vbox;
    @FXML FlowPane goalPane;
    @FXML VBox logbokVbox;

    @FXML TextArea loggTextArea;
    @FXML Label dateLabel;
    @FXML TextField headerField;
    @FXML Button saveNoteBtn;

    private int tasksAdded = 0;
    private int tasksDone = 0;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatLabel();
        initializeTaskFieldListener();
        initializeMonthProgress();
        initializeAVbox();
    }


    private void updatLabel(){
        taskDoneLabel.setText((tasksDone) + "/" + (tasksAdded));
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
                updatLabel();
            }
        });
        vbox.getChildren().add(newBox);
        tasksAdded++;
        updatLabel();
    }

    private void initializeMonthProgress(){
        goalPane.getChildren().add(new GoalPane("Gympass"));
        goalPane.getChildren().add(new GoalPane("Loggboksinlägg"));
        goalPane.getChildren().add(new GoalPane("Läsa Programmeringsbok"));
        goalPane.getChildren().add(new GoalPane("Bra middagar"));
    }

    private void initializeAVbox(){
        logbokVbox.getChildren().add(new LoggbokPane());
        logbokVbox.getChildren().add(new LoggbokPane());
        logbokVbox.getChildren().add(new LoggbokPane());
    }

    @FXML
    private void saveNote(){
        String textToSave = loggTextArea.getText();
        String path_to_save = "C:\\Users\\Martin\\Documents\\JavaProjekt\\GoalAndScheduling\\Loggboksinlägg\\";
        String fileName = path_to_save + FileHandling.getCurrentDate("dd-MM-yy") + "_" + headerField.getText() + ".txt";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.append(headerField.getText()).append("\n").append(textToSave);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    /**
     * Todo -- Spara ner Data varje dag till ett Excel / Databas / TextDokument
     * Todo -- Fixa så vi får en ny Panel där vi kan skriva i Loggbok
     * Todo -- Fixa LoggboksPane för
     * Todo -- Möjligtvis även en VisionBoard Pane?
     * Todo -- Fixa en Pane för Att lägga in kosten
     * Todo -- Lägg in ShoppingLista -> Se till så den kan pusha till google docs.
     * Todo -- KunskapsPane -> Att kunna ta och lägga till saker ifrån så den poppar över till en annan pane
     * Todo -- En pane för alla saker jag vill köpa mig.
     * Todo -- En liten timer som jag kan trycka igång för att timea mina saker
     * Todo -- Ta reda på vilken månadens datum är för att uppdatera rätt kolumn.
     */
}
