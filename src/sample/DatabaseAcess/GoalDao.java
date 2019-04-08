package sample.DatabaseAcess;

import java.util.List;

public interface GoalDao {
    List<Goal> getAllGoals();
    Goal getGoal(String name);
    void updateGoal(String goalName);
    void decrementGoal(String goalName);
    void resetCurrentProgress();
}
