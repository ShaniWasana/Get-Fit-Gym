package controller;

import Model.LoseFat;
import Model.Packages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;

public class Packagescontroller {
    public AnchorPane lblContext;
    public Label lblPackages;
    public TableView table;
    public TableColumn tblPackageId;
    public TableColumn tblPName;
    public TableColumn tblDuration;
    public TableColumn tblType;
    public TableColumn tblPrice;
    public  void initialize(){


        tblPackageId.setCellValueFactory(new PropertyValueFactory("package_Id"));
        tblPName.setCellValueFactory(new PropertyValueFactory("p_Name"));
        tblType.setCellValueFactory(new PropertyValueFactory("package_Type"));
        tblDuration.setCellValueFactory(new PropertyValueFactory("p_Duration"));
        tblPrice.setCellValueFactory(new PropertyValueFactory("p_Cost"));


        try {
            LoadAllIE();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void LoadAllIE() throws ClassNotFoundException, SQLException {
        System.out.println("Load all");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmanagementsystem", "root", "1234");
        String sql = "SELECT *FROM package";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println(result);

        ObservableList<Packages> oblist = FXCollections.observableArrayList();
        while (result.next()) {
            oblist.add(
                    new Packages(
                            result.getString("package_Id"),
                            result.getString("p_Name"),
                            result.getString("package_Type"),
                            result.getString("p_Duration"),
                            result.getDouble("p_Cost")


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
}
