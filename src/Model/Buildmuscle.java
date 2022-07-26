package Model;

public class Buildmuscle {
    private String Day;
    private String Exercises;
    private String Set;
    private String Reps;

    public Buildmuscle() {
    }

    public Buildmuscle(String day, String exercises, String set, String reps) {
        Day = day;
        Exercises = exercises;
        Set = set;
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

    public String getSet() {
        return Set;
    }

    public void setSet(String set) {
        Set = set;
    }

    public String getReps() {
        return Reps;
    }

    public void setReps(String reps) {
        Reps = reps;
    }

    @Override
    public String toString() {
        return "Buildmuscle{" +
                "Day='" + Day + '\'' +
                ", Exercises='" + Exercises + '\'' +
                ", Set='" + Set + '\'' +
                ", Reps='" + Reps + '\'' +
                '}';
    }
}
