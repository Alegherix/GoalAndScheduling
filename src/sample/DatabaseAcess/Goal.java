package sample.DatabaseAcess;

public class Goal {
    private String name;
    private int monthlyTarget;
    private int yearlyTarget;
    private int currentProgress;

    public Goal(String name, int monthlyTarget, int yearlyTarget, int currentProgress) {
        this.name = name;
        this.monthlyTarget = monthlyTarget;
        this.yearlyTarget = yearlyTarget;
        this.currentProgress = currentProgress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthlyTarget() {
        return monthlyTarget;
    }

    public void setMonthlyTarget(int monthlyTarget) {
        this.monthlyTarget = monthlyTarget;
    }

    public int getYearlyTarget() {
        return yearlyTarget;
    }

    public void setYearlyTarget(int yearlyTarget) {
        this.yearlyTarget = yearlyTarget;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "name='" + name + '\'' +
                ", monthlyTarget=" + monthlyTarget +
                ", yearlyTarget=" + yearlyTarget +
                '}';
    }
}
