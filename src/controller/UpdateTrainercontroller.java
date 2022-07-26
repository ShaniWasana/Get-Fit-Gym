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

import javax.management.Notification;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;

import static controller.TrainerCrudcontroller.UpdateTrainer;

//import static controller.TrainerCrudcontroller.Update;

public class UpdateTrainercontroller {
    public AnchorPane lblContext;
    public Label lblAddTrainer;
    public Label lblFullName;
    public Label lblMemberID;
    public Label lblAddress;
    public JFXTextField txtMemberID;
    public JFXTextField txtFullName;
    public Button btnAddT;
    public Label lblContactNo;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public Label lblAge;
    public JFXTextField txtAge;
    public JFXTextField txtSalary;
    public Label lblSalary;

    public void NewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/Updatetrainers.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();
    }

   public void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       Trainer t = new Trainer(txtMemberID.getText(), txtFullName.getText(), txtAddress.getText(), txtContactNo.getText(), Integer.parseInt(txtAge.getText()), Double.parseDouble(txtSalary.getText()));
       if (TrainerCrudcontroller.UpdateTrainer(t)) {
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Update Successfully");
            alert.show();
       } else {
           Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Update Unsuccessfully");
           alert.show();
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
}
