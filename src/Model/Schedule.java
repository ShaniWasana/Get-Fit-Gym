package Model;

public class Schedule {
    private String Schedule_Id;
    private String Schedule_Type;
    private String Duration;

    public Schedule(String schedule_Id, String schedule_Type, String duration) {
        Schedule_Id = schedule_Id;
        Schedule_Type = schedule_Type;
        Duration = duration;
    }

    public Schedule() {
    }

    public String getSchedule_Id() {
        return Schedule_Id;
    }

    public void setSchedule_Id(String schedule_Id) {
        Schedule_Id = schedule_Id;
    }

    public String getSchedule_Type() {
        return Schedule_Type;
    }

    public void setSchedule_Type(String schedule_Type) {
        Schedule_Type = schedule_Type;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "Schedule_Id='" + Schedule_Id + '\'' +
                ", Schedule_Type='" + Schedule_Type + '\'' +
                ", Duration='" + Duration + '\'' +
                '}';
    }
}
