package controller;

import Model.Increase;
import Model.Member;
import Model.Suppliment;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;

public class Supplimentcontroller {
    public AnchorPane lblContext;
    public Label lblSuppliment;
    public TableView<Suppliment> table;
    public TableColumn tblSid;
    public TableColumn tblSname;
    public TableColumn quantity;
    public TableColumn tblSprice;
    public JFXTextField txtSearch;

    public  void initialize() throws SQLException, ClassNotFoundException {


        tblSid.setCellValueFactory(new PropertyValueFactory("suppliment_Id"));
        tblSname.setCellValueFactory(new PropertyValueFactory("suppliment_Name"));
        quantity.setCellValueFactory(new PropertyValueFactory("suppliment_Quantity"));
        tblSprice.setCellValueFactory(new PropertyValueFactory("sup_Price"));


        try {
            loadAllsup();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllsup() throws ClassNotFoundException, SQLException {
        System.out.println("Load all");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmanagementsystem", "root", "1234");
        String sql = "SELECT *FROM suppliment";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println(result);

        ObservableList<Suppliment> oblist = FXCollections.observableArrayList();
        while (result.next()) {
            oblist.add(
                    new Suppliment(
                            result.getString("suppliment_Id"),
                            result.getString("suppliment_Name"),
                            result.getInt("suppliment_Quantity"),
                            result.getDouble("sup_Price")

                    )
            );
        }
        table.setItems(oblist);
        System.out.println(oblist);
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

    public void AddSupplimentOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/AddSuppliment.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Suppliment selectedItem = table.getSelectionModel().getSelectedItem();

        BoxBlur blur = new BoxBlur(5, 5, 5);
        lblContext.setEffect(blur);


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure you want to Delete?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Suppliment!!");
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            SupplimentCrudcontroller.deleteSup(selectedItem);
            loadAllsup();

            lblContext.setEffect(null);
        } else {
            lblContext.setEffect(null);
        }

    }

    public void KeyRealease(KeyEvent keyEvent) {
        if(txtSearch.getText().startsWith("Sp")){
            ObservableList<Suppliment> ob = FXCollections.observableArrayList(
                    SupplimentCrudcontroller.searchReportBySupplimentId(
                            "%"+txtSearch.getText()+"%"
                    )
            );
            table.setItems(ob);
        }

    }
    }

