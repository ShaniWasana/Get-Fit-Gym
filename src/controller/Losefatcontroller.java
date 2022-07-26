package controller;

import Model.LoseFat;
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

public class Losefatcontroller {
    public AnchorPane lblContext;
    public Label lblLoseFat;
    public TableView table;

    public TableColumn lblDay2;
    public TableColumn lblDay4;

    public TableColumn lblDay21;
    public TableColumn lblDay211;

    public  void initialize(){


        lblDay2.setCellValueFactory(new PropertyValueFactory("Day"));
        lblDay4.setCellValueFactory(new PropertyValueFactory("Exercises"));
        lblDay21.setCellValueFactory(new PropertyValueFactory("Sets"));
        lblDay211.setCellValueFactory(new PropertyValueFactory("Reps"));


        try {
            LoadAllLoseFat();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void LoadAllLoseFat() throws ClassNotFoundException, SQLException {
        System.out.println("Load all");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmanagementsystem","root","1234");
        String sql="SELECT *FROM lose_fat";
        Statement statement=con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println(result);

        ObservableList<LoseFat> oblist = FXCollections.observableArrayList();
        while (result.next()){
            oblist.add(
                    new LoseFat(
                            result.getString("Day"),
                            result.getString("Exercises"),
                            result.getString("Sets"),
                            result.getString("Reps")

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
