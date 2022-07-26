package controller;

import Model.Improving;
import Model.Schedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;

public class Schedulecontroller {
    public AnchorPane lblContext;
    public Label lblSchedule;
    public VBox vbox;
    public TableView table;
    public TableColumn colSchedule;
    public TableColumn colType;
    public TableColumn colDuratiion;
    public  void initialize(){


        colSchedule.setCellValueFactory(new PropertyValueFactory("Schedule_Id"));
        colType.setCellValueFactory(new PropertyValueFactory("Schedule_Type"));
        colDuratiion.setCellValueFactory(new PropertyValueFactory("Duration"));



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
        String sql = "SELECT *FROM schedule";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println(result);

        ObservableList<Schedule> oblist = FXCollections.observableArrayList();
        while (result.next()) {
            oblist.add(
                    new Schedule(
                            result.getString("Schedule_Id"),
                            result.getString("Schedule_Type"),
                            result.getString("Duration")

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

    public void LoseFatOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/Losefat.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage1=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void BMuscleOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/BuildMuscles.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void ImproveOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/ImprovingEndurance.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void IFexibilityOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/IncreaseFexibility.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();//
    }

    public void ToningOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/Toning1.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
