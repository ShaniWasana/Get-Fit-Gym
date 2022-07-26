package controller;

import Model.SignUp;
import Model.Suppliment;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class SignUpcontroller {
    public AnchorPane lblContextSignUp;
    public Label lblSignUp;
    public Label lblFullName;
    public Label lblEmail;
    public JFXTextField txtFullName;
    public JFXTextField txtEmail;
    public Label lblUserName;
    public JFXTextField txtUserName;
    public Label lblPassword;
    public JFXPasswordField pwdPassword;
    public Label lblConfirmPassword;
    public JFXPasswordField pwdConfirmPassword;

    public void SignUpOnAction(ActionEvent actionEvent) throws IOException {

        SignUp su = new SignUp(
                txtFullName.getText(),txtEmail.getText(),txtUserName.getText(),pwdPassword.getText(),pwdConfirmPassword.getText()
        );
        try {
            if (CrudUtil.execute("INSERT INTO signup VALUES (?,?,?,?,?)",su.getFull_Name(),su.getEmail(),su.getUserName(),su.getPassword(),su.getConfirm_Password())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
                Stage stage1= (Stage) lblContextSignUp.getScene().getWindow();
                stage1.close();
                URL resource = getClass().getResource("/view/DashBorad.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene=new Scene(load);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }





    }

    public void SignInOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage) lblContextSignUp.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/SignIn.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }
}
