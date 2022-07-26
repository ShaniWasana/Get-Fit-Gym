package controller;

import Model.LoseFat;
import Model.Member;
import Model.Order;
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

public class OrderDetailscontroller {
    public AnchorPane lblContext;
    public Label lblOderDetails;
    public TableView<Order> tblTrainerId;
    public TableColumn tblOrderId;
    public TableColumn tblMemberId;

    public TableColumn tblSup;
    public TableColumn tblQuantity;
    public JFXTextField txtSearch;

    public  void initialize(){


        tblOrderId.setCellValueFactory(new PropertyValueFactory("order_Id"));
        tblMemberId.setCellValueFactory(new PropertyValueFactory("member_Id"));
        tblSup.setCellValueFactory(new PropertyValueFactory("suppliment_Id"));
        tblQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));


        try {
            LoadAllLOrders();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void LoadAllLOrders() throws ClassNotFoundException, SQLException {
        System.out.println("Load all");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmanagementsystem","root","1234");
        String sql="SELECT *FROM orders ";
        Statement statement=con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println(result);

        ObservableList<Order> oblist = FXCollections.observableArrayList();
        while (result.next()){
            oblist.add(
                    new Order(
                            result.getString("order_Id"),
                            result.getString("member_Id"),
                            result.getString("suppliment_Id"),
                            result.getInt("quantity")

                    )
            );
        }
        tblTrainerId.setItems(oblist);
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

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Order selectedItem = tblTrainerId.getSelectionModel().getSelectedItem();

        BoxBlur blur = new BoxBlur(5, 5, 5);
        lblContext.setEffect(blur);


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure you want to Delete?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Order!!");
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            OrderDetailsCrudcontroller.deleteOrder(selectedItem);
            LoadAllLOrders();;

            lblContext.setEffect(null);
        } else {
            lblContext.setEffect(null);
        }

    }



    public void SearchKey(KeyEvent keyEvent) {
        if(txtSearch.getText().startsWith("M")){
            ObservableList<Order> ob = FXCollections.observableArrayList(
                    OrderDetailsCrudcontroller.searchReportByOrderId(
                            "%"+txtSearch.getText()+"%"
                    )
            );
            tblTrainerId.setItems(ob);
        }

    }
    }

