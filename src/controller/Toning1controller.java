package controller;


import Model.Toning;
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

public class Toning1controller {
    public AnchorPane lblContext;
    public Label lblIToning;
    public TableView table;
    public TableColumn tblDay1;
    public TableColumn lblDay2;
    public TableColumn lblDay3;
    public TableColumn lblDay4;
    public TableColumn lblDay5;
    public TableColumn lblDay6;
    public TableColumn lblDay7;

    public  void initialize(){


        tblDay1.setCellValueFactory(new PropertyValueFactory("Day"));
        lblDay2.setCellValueFactory(new PropertyValueFactory("For_What"));
        lblDay3.setCellValueFactory(new PropertyValueFactory("Exercises"));
        lblDay4.setCellValueFactory(new PropertyValueFactory("Sets"));
        lblDay5.setCellValueFactory(new PropertyValueFactory("Reps"));
        lblDay6.setCellValueFactory(new PropertyValueFactory("Time"));

        try {
            LoadAllToning();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void LoadAllToning() throws ClassNotFoundException, SQLException {
        System.out.println("Load all");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmanagementsystem","root","1234");
        String sql="SELECT *FROM toning";
        Statement statement=con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println(result);

        ObservableList<Toning> oblist = FXCollections.observableArrayList();
        while (result.next()){
            oblist.add(
                    new Toning(
                            result.getString("Day"),
                            result.getString("For_What"),
                            result.getString("Exercises"),
                            result.getString("Sets"),
                            result.getString("Reps"),
                            result.getString("Time")
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
            URL resource = getClass().getResource("/view/Schedule.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage.setScene(scene);
            stage.show();

        }
    }
}