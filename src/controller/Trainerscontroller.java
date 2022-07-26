package controller;


import Model.Member;
import Model.Trainer;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;

public class Trainerscontroller {
    public AnchorPane lblContext;
    public ImageView lblAddMember;
    public Label lblTrainers;
    public TableView<Trainer> tblTable;
    public TableColumn tblTrainerID;
    public TableColumn tblName;
    public TableColumn tblAddress;
    public TableColumn tblContactNo;
    public TableColumn tblAge;
    public TableColumn tblSalary;
    public JFXTextField txtSearch;
    public Label lblTrainerId;

    public  void initialize(){


        tblTrainerID.setCellValueFactory(new PropertyValueFactory("trainer_Id"));
        tblName.setCellValueFactory(new PropertyValueFactory("t_Name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory("t_Address"));
        tblContactNo.setCellValueFactory(new PropertyValueFactory("t_Cotactno"));
        tblAge.setCellValueFactory(new PropertyValueFactory("t_Age"));
        tblSalary.setCellValueFactory(new PropertyValueFactory("t_Salary"));



        try {
            loadAlltrainer();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAlltrainer() throws ClassNotFoundException, SQLException {
        System.out.println("Load all");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmanagementsystem","root","1234");
        String sql="SELECT * FROM trainer";
        Statement statement=con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println(result);

        ObservableList<Trainer> oblist = FXCollections.observableArrayList();
        while (result.next()){
            oblist.add(
                    new Trainer(
                            result.getString("trainer_Id"),
                            result.getString("t_Name"),
                            result.getString("t_Address"),
                            result.getString("t_Cotactno"),
                            result.getInt("t_Age"),
                            result.getDouble("t_Salary")





                    )
            );
        }
        tblTable.setItems(oblist);
        System.out.println(oblist);
    }




    public void UpdateOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/Updatetrainers.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void AddOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/AddTrainers.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();

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

    public void SearchKey(KeyEvent keyEvent) {
        if(txtSearch.getText().startsWith("T")){
            ObservableList<Trainer> ob = FXCollections.observableArrayList(
                    TrainerCrudcontroller.searchReportByTrainerId(
                            "%"+txtSearch.getText()+"%"
                    )
            );
            tblTable.setItems(ob);
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Trainer selectedItem = tblTable.getSelectionModel().getSelectedItem();

        BoxBlur blur = new BoxBlur(5, 5, 5);
        lblContext.setEffect(blur);


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure you want to Delete?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Member!!");
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            TrainerCrudcontroller.deleteTrainer(selectedItem);
            loadAlltrainer();

            lblContext.setEffect(null);
        } else {
            lblContext.setEffect(null);
        }

    }

}
