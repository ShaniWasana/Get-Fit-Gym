package Model;

public class Suppliment {
    private String suppliment_Id;
    private String Suppliment_Name;
    private int Suppliment_Quantity;
    private Double sup_Price;


    public Suppliment() {
    }
    public Suppliment(Double sup_Price) {
    }

    public Suppliment(String suppliment_Id, String suppliment_Name, int suppliment_Quantity, Double sup_Price) {
        this.suppliment_Id = suppliment_Id;
        Suppliment_Name = suppliment_Name;
        Suppliment_Quantity = suppliment_Quantity;
        this.sup_Price = sup_Price;
    }

    public String getSuppliment_Id() {
        return suppliment_Id;
    }

    public void setSuppliment_Id(String suppliment_Id) {
        this.suppliment_Id = suppliment_Id;
    }

    public String getSuppliment_Name() {
        return Suppliment_Name;
    }

    public void setSuppliment_Name(String suppliment_Name) {
        Suppliment_Name = suppliment_Name;
    }

    public int getSuppliment_Quantity() {
        return Suppliment_Quantity;
    }

    public void setSuppliment_Quantity(int suppliment_Quantity) {
        Suppliment_Quantity = suppliment_Quantity;
    }

    public Double getSup_Price() {
        return sup_Price;
    }

    public void setSup_Price(Double sup_Price) {
        this.sup_Price = sup_Price;
    }

    @Override
    public String toString() {
        return "Suppliment{" +
                "suppliment_Id='" + suppliment_Id + '\'' +
                ", Suppliment_Name='" + Suppliment_Name + '\'' +
                ", Suppliment_Quantity=" + Suppliment_Quantity +
                ", sup_Price=" + sup_Price +
                '}';
    }
}
