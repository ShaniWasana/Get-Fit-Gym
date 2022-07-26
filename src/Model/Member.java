package Model;

public class Member {
    private String Member_Id;
    private String Full_Name;
    private String Address;
    private String Contact_No;
    private int Age;
    private String Package_Id;
    private String Schedule_Id;

    public Member() {
    }

    public Member(String member_Id, String full_Name, String address, String contact_No, int age, String package_Id, String schedule_Id) {
        Member_Id = member_Id;
        Full_Name = full_Name;
        Address = address;
        Contact_No = contact_No;
        Age = age;
        Package_Id = package_Id;
        Schedule_Id = schedule_Id;
    }

    public String getMember_Id() {
        return Member_Id;
    }

    public void setMember_Id(String member_Id) {
        Member_Id = member_Id;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact_No() {
        return Contact_No;
    }

    public void setContact_No(String contact_No) {
        Contact_No = contact_No;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getPackage_Id() {
        return Package_Id;
    }

    public void setPackage_Id(String package_Id) {
        Package_Id = package_Id;
    }

    public String getSchedule_Id() {
        return Schedule_Id;
    }

    public void setSchedule_Id(String schedule_Id) {
        Schedule_Id = schedule_Id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "Member_Id='" + Member_Id + '\'' +
                ", Full_Name='" + Full_Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact_No='" + Contact_No + '\'' +
                ", Age=" + Age +
                ", Package_Id='" + Package_Id + '\'' +
                ", Schedule_Id='" + Schedule_Id + '\'' +
                '}';
    }
}


