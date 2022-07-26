package controller;

import Model.Suppliment;
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

public class AddSupplimentcontroller {
    public AnchorPane lblContext;
    public Label lblAddSuppliment;
    public Label lblSupplimentName;
    public Label lblSupplementID;
    public JFXTextField txtSupplimentID;
    public JFXTextField txtSupplimentName;
    public Label lblQuantity;
    public Label lblPrice;
    public JFXTextField txtPrice;
    public JFXTextField txtQuantity;
    public Button btnAddS;

    public void initialize(){
        txtSupplimentID.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^(Sp)[0-9]+$",newValue,txtSupplimentID,btnAddS);
        });

        txtSupplimentName.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-z]+$",newValue,txtSupplimentName,btnAddS);
        });
        txtQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9]+$",newValue,txtQuantity,btnAddS);
        });
        txtPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9].[0-9]{1,2}$",newValue,txtPrice,btnAddS);
        });

    }


    public void NewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/AddSuppliment.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void AddOnAction(ActionEvent actionEvent) {
        Suppliment sp = new Suppliment(
                txtSupplimentID.getText(),txtSupplimentName.getText(),Integer.parseInt(txtQuantity.getText()),Double.parseDouble(txtPrice.getText())
        );
        try {
            if (CrudUtil.execute("INSERT INTO suppliment VALUES (?,?,?,?)",sp.getSuppliment_Id(),sp.getSuppliment_Name(),sp.getSuppliment_Quantity(),sp.getSup_Price())) {
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
            URL resource = getClass().getResource("/view/Suppliment.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage.setScene(scene);
            stage.show();

        }

    }
}
