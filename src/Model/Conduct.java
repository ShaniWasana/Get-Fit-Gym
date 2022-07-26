package Model;

import org.exolab.castor.types.DateTime;

public class Conduct {
    private String Schedule_Id;
    private String trainer_Id;
    private String t_Date;

    public Conduct() {
    }

    public Conduct(String schedule_Id, String trainer_Id, String t_Date) {
        Schedule_Id = schedule_Id;
        this.trainer_Id = trainer_Id;
        this.t_Date = t_Date;
    }

    public String getSchedule_Id() {
        return Schedule_Id;
    }

    public void setSchedule_Id(String schedule_Id) {
        Schedule_Id = schedule_Id;
    }

    public String getTrainer_Id() {
        return trainer_Id;
    }

    public void setTrainer_Id(String trainer_Id) {
        this.trainer_Id = trainer_Id;
    }

    public String getT_Date() {
        return t_Date;
    }

    public void setT_Date(String t_Date) {
        this.t_Date = t_Date;
    }

    @Override
    public String toString() {
        return "Conduct{" +
                "Schedule_Id='" + Schedule_Id + '\'' +
                ", trainer_Id='" + trainer_Id + '\'' +
                ", t_Date='" + t_Date + '\'' +
                '}';
    }
}