package sample.DatabaseAcess;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GoalAcess implements GoalDao {

    private Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/martinsdb?allowPublicKeyRetrieval=true&useSSL=false&", "root","root");
    }


    /**
     * Resetar CurrentProgress för ALLA rader.
     */
    public void resetCurrentProgress(){
        //Todo --  Kanske borde kunna göra det om jag märker att det är ny månad
        String query = "update goals set currentProgress = 0";
        try {
            Statement st = getConnection().createStatement();
            st.executeUpdate(query);
            st.close();
            getConnection().close();
            System.out.println("Reseted the CurrentProgress");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Goal> getAllGoals() {

        List<Goal> goals = new ArrayList<>();
        String query = "select * from goals";
        try {
            PreparedStatement st = getConnection().prepareStatement(query);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()){
                goals.add(new Goal(
                        resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4))
                );
            }
            st.close();
            getConnection().close();
            return goals;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return goals;

    }

    /**
     * Returnerar ett nytt mål skapat ifrån Kolumnerna som används i databasen.
     * @param name
     * @return
     */
    @Override
    public Goal getGoal(String name) {
        String query = "select * from goals where goalName = ?";
        Goal returnGoal = null;
        try {
            PreparedStatement st = getConnection().prepareStatement(query);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            rs.next();
            returnGoal = new Goal(
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4));

            st.close();
            getConnection().close();

            return returnGoal;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return returnGoal;
    }


    @Override
    public void updateGoal(String goalName) {
        updateGoal(goalName, "+");
    }

    @Override
    public void decrementGoal(String goalName) {
        updateGoal(goalName, "-");
    }

    /**
     * Internt använd updateGoal metod som incrementerar / dekrementerar värdet för currentProgress samt TotalProgress
     * @param goalName
     * @param change
     */
    private void updateGoal(String goalName, String change){
        String query = "update goals" +
                " set currentprogress = currentProgress " + change + " 1, " +
                "totalProgress = totalProgress " + change + " 1 " +
                "where goalName = ? ";
        try {
            PreparedStatement st = getConnection().prepareStatement(query);
            st.setString(1, goalName);
            st.executeUpdate();
            st.close();
            getConnection().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO - Fungerar ej för tillfället då ? = ? ger felaktig syntax???
    private void incrementCurrentMonth(){
        String query = "update goals set ? = ? + ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(query);
            //st.setString(1, "Apr");
            st.setString(1, "Apr");
            st.setString(2, "Apr");
            st.setInt(3, 1);
            System.out.println(getColumnMonth());
            st.executeUpdate(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getColumnMonth(){
        return new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
    }

}
