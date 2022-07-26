package Model;

public class Increase {
    private String Day;
    private String Exercises;
    private String Sets;
    private String Reps;

    public Increase() {
    }

    public Increase(String day, String exercises, String sets, String reps) {
        Day = day;
        Exercises = exercises;
        Sets = sets;
        Reps = reps;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getExercises() {
        return Exercises;
    }

    public void setExercises(String exercises) {
        Exercises = exercises;
    }

    public String getSets() {
        return Sets;
    }

    public void setSets(String sets) {
        Sets = sets;
    }

    public String getReps() {
        return Reps;
    }

    public void setReps(String reps) {
        Reps = reps;
    }

    @Override
    public String toString() {
        return "Increase{" +
                "Day='" + Day + '\'' +
                ", Exercises='" + Exercises + '\'' +
                ", Sets='" + Sets + '\'' +
                ", Reps='" + Reps + '\'' +
                '}';
    }
}
