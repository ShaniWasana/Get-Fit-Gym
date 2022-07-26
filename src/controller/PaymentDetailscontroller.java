package controller;

import Model.Order;
import Model.Payments;
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

public class PaymentDetailscontroller {

    public AnchorPane lblContext;
    public Label lblOderDetails;
    public TableView<Payments> tblTrainerId;
    public TableColumn tblPayment;
    public TableColumn tblPackageid;
    public TableColumn tblMember;
    public TableColumn tblPDate;
    public TableColumn tblPrice;
    public JFXTextField txtSearch;
    public  void initialize(){


        tblPayment.setCellValueFactory(new PropertyValueFactory("payment_Id"));
        tblPackageid.setCellValueFactory(new PropertyValueFactory("package_Id"));
        tblMember.setCellValueFactory(new PropertyValueFactory("member_Id"));
        tblPDate.setCellValueFactory(new PropertyValueFactory("p_Date"));
        tblPrice.setCellValueFactory(new PropertyValueFactory("price"));


        try {
            LoadAllpayment();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void LoadAllpayment() throws ClassNotFoundException, SQLException {
        System.out.println("Load all");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmanagementsystem","root","1234");
        String sql="SELECT *FROM payment ";
        Statement statement=con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println(result);

        ObservableList<Payments> oblist = FXCollections.observableArrayList();
        while (result.next()){
            oblist.add(
                    new Payments(
                            result.getString("payment_Id"),
                            result.getString("package_Id"),
                            result.getString("member_Id"),
                            result.getDate("p_Date"),
                            result.getDouble("price")

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
        Payments selectedItem = tblTrainerId.getSelectionModel().getSelectedItem();

        BoxBlur blur = new BoxBlur(5, 5, 5);
        lblContext.setEffect(blur);


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure you want to Delete?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Payment!!");
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            PaymentDetailsCrudcontroller.deletePayment(selectedItem);
            LoadAllpayment();;

            lblContext.setEffect(null);
        } else {
            lblContext.setEffect(null);
        }

    }

    public void SearchKey(KeyEvent keyEvent) {

            if(txtSearch.getText().startsWith("Py")){
                ObservableList<Payments> ob = FXCollections.observableArrayList(
                        PaymentDetailsCrudcontroller.searchReportByPaymentId(
                                "%"+txtSearch.getText()+"%"
                        )
                );
                tblTrainerId.setItems(ob);
            }

        }
    }

