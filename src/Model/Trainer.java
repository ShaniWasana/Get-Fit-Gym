package Model;

public class Trainer {
    private String trainer_Id;
    private String t_Name;
    private String t_Address;
    private String t_Contactno;
    private int t_Age;
    private double t_Salary;

    public Trainer(String text, String txtFullNameText) {
    }

    public Trainer(String trainer_Id, String t_Name, String t_Address, String t_Contactno, int t_Age, double t_Salary) {
        this.trainer_Id = trainer_Id;
        this.t_Name = t_Name;
        this.t_Address = t_Address;
        this.t_Contactno = t_Contactno;
        this.t_Age = t_Age;
        this.t_Salary = t_Salary;
    }

    public String getTrainer_Id() {
        return trainer_Id;
    }

    public void setTrainer_Id(String trainer_Id) {
        this.trainer_Id = trainer_Id;
    }

    public String getT_Name() {
        return t_Name;
    }

    public void setT_Name(String t_Name) {
        this.t_Name = t_Name;
    }

    public String getT_Address() {
        return t_Address;
    }

    public void setT_Address(String t_Address) {
        this.t_Address = t_Address;
    }

    public String getT_Contactno() {
        return t_Contactno;
    }

    public void setT_Contactno(String t_Contactno) {
        this.t_Contactno = t_Contactno;
    }

    public int getT_Age() {
        return t_Age;
    }

    public void setT_Age(int t_Age) {
        this.t_Age = t_Age;
    }

    public double getT_Salary() {
        return t_Salary;
    }

    public void setT_Salary(double t_Salary) {
        this.t_Salary = t_Salary;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "trainer_Id='" + trainer_Id + '\'' +
                ", t_Name='" + t_Name + '\'' +
                ", t_Address='" + t_Address + '\'' +
                ", t_Contactno='" + t_Contactno + '\'' +
                ", t_Age=" + t_Age +
                ", t_Salary=" + t_Salary +
                '}';
    }
}
