package Model;

import java.util.Date;

public class Payments {
    private String payment_Id;
    private String package_Id;
    private String member_Id;
    private Date p_Date;
    private double price;

    public Payments() {
    }

    public Payments(String payment_Id, String package_Id, String member_Id, Date p_Date, double price) {
        this.payment_Id = payment_Id;
        this.package_Id = package_Id;
        this.member_Id = member_Id;
        this.p_Date = p_Date;
        this.price = price;
    }

    public String getPayment_Id() {
        return payment_Id;
    }

    public void setPayment_Id(String payment_Id) {
        this.payment_Id = payment_Id;
    }

    public String getPackage_Id() {
        return package_Id;
    }

    public void setPackage_Id(String package_Id) {
        this.package_Id = package_Id;
    }

    public String getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(String member_Id) {
        this.member_Id = member_Id;
    }

    public Date getP_Date() {
        return p_Date;
    }

    public void setP_Date(Date p_Date) {
        this.p_Date = p_Date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "payment_Id='" + payment_Id + '\'' +
                ", package_Id='" + package_Id + '\'' +
                ", member_Id='" + member_Id + '\'' +
                ", p_Date=" + p_Date +
                ", price=" + price +
                '}';
    }
}