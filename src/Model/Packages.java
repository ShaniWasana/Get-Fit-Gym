package Model;

public class Packages {
    private String package_Id;
    private String p_Name;
    private String package_Type;
    private String p_Duration;
    private double p_Cost;

    public Packages() {
    }

    public Packages(String package_Id, String p_Name, String package_Type, String p_Duration, double p_Cost) {
        this.package_Id = package_Id;
        this.p_Name = p_Name;
        this.package_Type = package_Type;
        this.p_Duration = p_Duration;
        this.p_Cost = p_Cost;
    }

    public String getPackage_Id() {
        return package_Id;
    }

    public void setPackage_Id(String package_Id) {
        this.package_Id = package_Id;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getPackage_Type() {
        return package_Type;
    }

    public void setPackage_Type(String package_Type) {
        this.package_Type = package_Type;
    }

    public String getP_Duration() {
        return p_Duration;
    }

    public void setP_Duration(String p_Duration) {
        this.p_Duration = p_Duration;
    }

    public double getP_Cost() {
        return p_Cost;
    }

    public void setP_Cost(double p_Cost) {
        this.p_Cost = p_Cost;
    }

    @Override
    public String toString() {
        return "Packages{" +
                "package_Id='" + package_Id + '\'' +
                ", p_Name='" + p_Name + '\'' +
                ", package_Type='" + package_Type + '\'' +
                ", p_Duration='" + p_Duration + '\'' +
                ", p_Cost=" + p_Cost +
                '}';
    }
}