package controller;


import Model.Member;
import com.jfoenix.controls.JFXTextField;
import db.DataSet;
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
import java.sql.*;
import java.util.Optional;

public class AddMembercontroller {
    public AnchorPane lblContext;
    public Label lblAddMenber;
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
    public Label lblPackageID;
    public JFXTextField txtPackageID;
    public Label lblScheduleID;
    public JFXTextField txtScheduleID;
    public Button btnAddMember;


    public void initialize(){
        txtMemberID.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^(M)[0-9]+$",newValue,txtMemberID,btnAddMember);
        });

        txtFullName.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-Z][a-z]*[ ][A-Z][a-z]*$",newValue,txtFullName,btnAddMember);
        });
        txtAdress.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-z]+$",newValue,txtAdress,btnAddMember);
        });
        txtContactNo.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9]+$",newValue,txtContactNo,btnAddMember);
        });
        txtAge.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9]{1,3}$",newValue,txtAge,btnAddMember);
        });

        txtPackageID.textProperty().addListener((observable, oldValue, newValue) -> {
        ValidationUtil.validate("^(P)[0-9]+$",newValue,txtPackageID,btnAddMember);
    });
        txtScheduleID.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^(S)[0-9]+$",newValue,txtScheduleID,btnAddMember);
        });
    }

    public void NewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) lblContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/AddMember.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OrderOnAction(ActionEvent actionEvent) {
        Member m = new Member(
                txtMemberID.getText(), txtFullName.getText(), txtAdress.getText(), txtContactNo.getText(),
                Integer.parseInt(txtAge.getText()), txtPackageID.getText(), txtScheduleID.getText()
        );
        if (DataSet.MemberTable.add(m))
            new Alert(Alert.AlertType.CONFIRMATION, "New Member Added Successfully!").show();
        else
            new Alert(Alert.AlertType.CONFIRMATION, "There  is a error,Please Try Again").show();


        try {
            if (CrudUtil.execute("INSERT INTO member VALUES (?,?,?,?,?,?,?)", m.getMember_Id(), m.getFull_Name(), m.getAddress(), m.getContact_No(), m.getAge(), m.getPackage_Id(), m.getSchedule_Id())) {
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
            URL resource = getClass().getResource("/view/DashBorad.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage.setScene(scene);
            stage.show();

        }
    }

    public void SearchOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) lblContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/SearchMember.fxml");

        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}