package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import sample.DatabaseAcess.Goal;
import sample.DatabaseAcess.GoalAcess;


import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class GoalPane extends AnchorPane {
    private int currentProgress;
    private int progressToBeMade;
    private String goalName;
    private GoalAcess goalAcess;
    private Goal goal;
    private boolean showingMonth;

    @FXML ProgressBar progressBar;
    @FXML Label percentageLabel;
    @FXML Label goalLabel;
    @FXML Label progressLabel;
    @FXML Label goalDescLabel;


    public GoalPane(String goalName) {
        this.goalName = goalName;
        goalAcess = new GoalAcess();
        goal = getCurrentGoal();
        showingMonth = true;


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Styling/goal.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializeGoalLabel();
        showProgressMonth();
        updateProgress();

    }



    @FXML
    public void increaseProgress(){
        goalAcess.updateGoal(goalName);
        updateProgress();
    }

    @FXML
    public void decreaseProgress(){
        goalAcess.decrementGoal(goalName);
        updateProgress();
    }

    /**
     * Uppdaterar Progressen igenom att skapa en referens till det uppdatera målet och hämtar värdena från denna
     */
    private void updateProgress(){
        goal = getCurrentGoal();

        if(showingMonth){
            progressToBeMade = goal.getMonthlyTarget();
            showProgressMonth();
        }
        else{
            progressToBeMade = goal.getYearlyTarget();
            showProgressYear();
        }

        double progressDone = (double) goal.getCurrentProgress() / progressToBeMade;
        percentageLabel.setText(getFormatter().format(progressDone * 100) + "%");
        progressBar.setProgress(progressDone);
    }

    private void initializeGoalLabel(){
        goalLabel.setText(goalName);
    }

    /**
     * Formaterar percentage tecknet ordentligt
     * @return
     */
    private DecimalFormat getFormatter(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df;
    }

    private Goal getCurrentGoal(){
        return goalAcess.getGoal(goalName);
    }

    private void showProgressMonth(){
        progressLabel.setText(goal.getCurrentProgress()+ "/" + goal.getMonthlyTarget());
        goalDescLabel.setText("Monthly Goal");
    }

    private void showProgressYear(){
        progressLabel.setText(goal.getCurrentProgress()+ "/" + goal.getYearlyTarget());
        goalDescLabel.setText("Yearly Goal");
    }

    @FXML
    private void showNextProgressView(){
        showingMonth = !showingMonth;
        updateProgress();
    }

}
