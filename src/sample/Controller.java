package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // Main pane komponenter
    @FXML TextField addTaskField;
    @FXML Label taskDoneLabel;
    @FXML Button addTaskBtn;
    @FXML VBox vbox;
    @FXML FlowPane goalPane;
    @FXML VBox logbokVbox;

    // Logboks Komponenter
    @FXML TextArea loggTextArea;
    @FXML TextField headerField;
    @FXML Button saveNoteBtn;

    // De olika panesen
    @FXML AnchorPane primaryAnchor;
    @FXML AnchorPane logbokAnchor;

    private int tasksAdded = 0;
    private int tasksDone = 0;
    private boolean showingGoalPane;
    private List<Path> paths;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatLabel();
        paths = FileHandling.getPathForNewestFiles();
        initializeTaskFieldListener();
        initializeMonthProgress();
        initializeLoggVBoxPanel();
        showingGoalPane = true;
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
        List<String> goals = Arrays.asList("Gympass", "Loggboksinlägg", "Bra middagar",
        "Läsa på om Spring", "Läsa på om REST", "Research & Develop WebbApp");
        goals.forEach(goal -> goalPane.getChildren().add(new GoalPane(goal)));
    }

    private void initializeLoggVBoxPanel(){
        paths.forEach(path -> logbokVbox.getChildren().add(new LoggbokPane(path, this)));
    }

    @FXML
    private void saveNote(){
        /**
         * Saves the note with the Format of:
         * Header
         * Tasks Completed / Tasks Added
         * Text in textBox
         */

        String textToSave = loggTextArea.getText();
        String folderPath = "C:\\Users\\Martin\\Documents\\JavaProjekt\\GoalAndScheduling\\Loggboksinlägg\\";
        String fileName = folderPath + FileHandling.getCurrentDate("dd-MM-yy") + "_" + headerField.getText() + ".txt";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
            bufferedWriter.append(
                    headerField.getText()).append("\n")
                    .append(String.valueOf(tasksDone)).append("/").append(String.valueOf(tasksAdded)).append("\n")
                    .append(textToSave);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            updateLogbokPanel();
        }
    }

    private void updateLogbokPanel(){
        loggTextArea.clear();
        headerField.clear();
        logbokVbox.getChildren().clear();
        paths = FileHandling.getPathForNewestFiles();
        initializeLoggVBoxPanel();
    }

    @FXML
    private void switchPane(){
        if(showingGoalPane){
            logbokAnchor.toFront();
            showingGoalPane = false;
        }
        else{
            primaryAnchor.toFront();
            showingGoalPane = true;
        }
    }

    public TextArea getLoggTextArea() {
        return loggTextArea;
    }

    public TextField getHeaderField() {
        return headerField;
    }

    /**
     * Todo -- Spara ner Data varje dag till ett Excel / Databas / TextDokument
     * Todo -- Fixa så vi får en ny Panel där vi kan skriva i Loggbok -- Done
     * Todo -- Fixa LoggboksPane för .. Done
     * Todo -- Möjligtvis även en VisionBoard Pane?
     * Todo -- Fixa en Pane för Att lägga in kosten
     * Todo -- Lägg in ShoppingLista -> Se till så den kan pusha till google docs.
     * Todo -- KunskapsPane -> Att kunna ta och lägga till saker ifrån så den poppar över till en annan pane
     * Todo -- En pane för alla saker jag vill köpa mig.
     * Todo -- En liten timer som jag kan trycka igång för att timea mina saker
     * Todo -- Ta reda på vilken månadens datum är för att uppdatera rätt kolumn.
     */
}
