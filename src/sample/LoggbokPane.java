package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class LoggbokPane extends AnchorPane {

    @FXML private Label dateLabel;
    @FXML private Label headerLabel;
    @FXML private Label scoreLabel;
    @FXML private TextArea textArea;
    @FXML private Tooltip headerTooltip;

    private Path path;
    private Controller controller;


    public LoggbokPane(Path path, Controller controller) {
        this.path = path;
        this.controller = controller;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Styling/logbok.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializeDateLabel();
        initializeHeaderLabel();
        initializeScoreLabel();
        initializeTextArea();
        initializeTooltip();
    }


    private void initializeDateLabel(){
        dateLabel.setText(FileHandling.getCreationTime(path));
    }

    private void initializeHeaderLabel(){
       headerLabel.setText(FileHandling.readFirstLine(path));
    }

    private void initializeScoreLabel(){
        scoreLabel.setText(FileHandling.readTasksCompleted(path));
    }

    private void initializeTextArea(){
        textArea.setText(FileHandling.readTextFromFile(path));
    }

    /**
     * Callas när man trycker på ett logginlägg
     */
    @FXML
    private void prepareTextForEdit(){
        controller.getLoggTextArea().setText(textArea.getText());
        controller.getHeaderField().setText(headerLabel.getText());
    }

    private void initializeTooltip(){
        headerTooltip.activatedProperty().addListener((observable, oldValue, newValue) -> {
            headerTooltip.setText(FileHandling.readFirstLine(path));
        });
    }


    // Todo -- Spara loggboksinlägg som .txt filer -- DONE
    // Todo -- Läs in de 3 senaste filerna, och passera in deras lastModified sträng till loggbokPane -> använd senast modified för att sätta dateLabel -- DONE
    // Todo -- Spara .txt filen som d/m-yy_Header.txt .. Done
    // Todo använd första raden ifrån filen som header.... Done
}
