package Model;

public class Order {
    private String order_Id;
    private String member_Id;
    private String suppliment_Id;
    private int quantity;

    public Order() {
    }

    public Order(String order_Id, String member_Id, String suppliment_Id, int quantity) {
        this.order_Id = order_Id;
        this.member_Id = member_Id;
        this.suppliment_Id = suppliment_Id;
        this.quantity = quantity;
    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    public String getMember_Id() {
        return member_Id;
    }

    public void setMember_Id(String member_Id) {
        this.member_Id = member_Id;
    }

    public String getSuppliment_Id() {
        return suppliment_Id;
    }

    public void setSuppliment_Id(String suppliment_Id) {
        this.suppliment_Id = suppliment_Id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_Id='" + order_Id + '\'' +
                ", member_Id='" + member_Id + '\'' +
                ", suppliment_Id='" + suppliment_Id + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

