package Model;

import org.exolab.castor.types.DateTime;

import java.util.Date;

public class Toning {
    private String Day;
    private String For_What;
    private String Exercises;
    private String Set;
    private String Reps;
    private String Time;

    public Toning() {
    }

    public Toning(String day, String for_What, String exercises, String set, String reps, String time) {
        Day = day;
        For_What = for_What;
        Exercises = exercises;
        Set = set;
        Reps = reps;
        Time = time;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getFor_What() {
        return For_What;
    }

    public void setFor_What(String for_What) {
        For_What = for_What;
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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "Toning{" +
                "Day='" + Day + '\'' +
                ", For_What='" + For_What + '\'' +
                ", Exercises='" + Exercises + '\'' +
                ", Set='" + Set + '\'' +
                ", Reps='" + Reps + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
