package Model;

public class SignUp {
private String Full_Name;
private String Email;
private String UserName;
private String Password;
private String Confirm_Password;

    public SignUp() {
    }

    public SignUp(String full_Name, String email, String userName, String password, String confirm_Password) {
        Full_Name = full_Name;
        Email = email;
        UserName = userName;
        Password = password;
        Confirm_Password = confirm_Password;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirm_Password() {
        return Confirm_Password;
    }

    public void setConfirm_Password(String confirm_Password) {
        Confirm_Password = confirm_Password;
    }

    @Override
    public String toString() {
        return "SignUp{" +
                "Full_Name='" + Full_Name + '\'' +
                ", Email='" + Email + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", Confirm_Password='" + Confirm_Password + '\'' +
                '}';
    }
}
