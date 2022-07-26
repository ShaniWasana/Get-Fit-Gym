package controller;

import Model.Trainer;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CrudUtil;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;

public class AddTrainercontroller {
    public AnchorPane lblContext;
    public Label lblAddTrainer;
    public Label lblFullName;
    public Label lblMemberID;
    public Label lblAddress;
    public JFXTextField txtMemberID;
    public JFXTextField txtFullName;
    public JFXTextField txtAdress;
    public Label lblContactNo;
    public JFXTextField txtContactNo;
    public Label lblAge;
    public JFXTextField txtAge;
    public Label lblSalary;
    public JFXTextField txtSalary;
    public Button btnAddT;

    public void initialize(){
        txtMemberID.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^(T)[0-9]+$",newValue,txtMemberID,btnAddT);
        });

        txtFullName.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-Z][a-z]*[ ][A-Z][a-z]*$",newValue,txtFullName,btnAddT);
        });
        txtAdress.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-z]+$",newValue,txtAdress,btnAddT);
        });
        txtContactNo.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9]{1,2}$",newValue,txtContactNo,btnAddT);
        });
        txtAge.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9]{1,3}$",newValue,txtAge,btnAddT);
        });
        txtSalary.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9].[0.9]{1,2}$",newValue,txtSalary,btnAddT);
        });


    }
    public void AddOnAction(ActionEvent actionEvent) {
        Trainer t = new Trainer(
                txtMemberID.getText(),txtFullName.getText(),txtAdress.getText(),txtContactNo.getText(),Integer.parseInt(txtAge.getText()),Double.parseDouble(txtSalary.getText())
        );
        try {
            if (CrudUtil.execute("INSERT INTO trainer VALUES (?,?,?,?,?,?)",t.getTrainer_Id(),t.getT_Name(),t.getT_Address(),t.getT_Contactno(),t.getT_Age(),t.getT_Salary())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void CancelOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Exit", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            Stage stage = (Stage) lblContext.getScene().getWindow();
            stage.close();
            URL resource = getClass().getResource("/view/Trainers.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage.setScene(scene);
            stage.show();

        }



        }

    public void NewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) lblContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/AddTrainers.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}