package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoggbokPane extends AnchorPane {

    @FXML Label dateLabel;
    @FXML Label headerLabel;
    @FXML Label scoreLabel;
    @FXML TextArea textArea;


    public LoggbokPane() {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Styling/logbok.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initializeDateLabel(){
        //dateLabel.setText(.getCurrentDate("d/M-YY"));
    }

    private String getWrittenDate(){
        File file = new File("");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M-yy");
        return simpleDateFormat.format(cal.getTime());
    }


    // Todo -- Spara loggboksinlägg som .txt filer
    // Todo -- Läs in de 3 senaste filerna, och passera in deras lastModified sträng till loggbokPane -> använd senast modified för att sätta dateLabel
    // Todo -- Spara .txt filen som d/m-yy_Header.txt
    // Todo använd t.xt filens resterande del av namn som header.
}
