package controller;

import Model.Order;
import Model.Suppliment;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class Ordercontroller {
    public AnchorPane lblContext;
    public Label lblOrder;
    public Label lblOrderID;
    public Label lblMemberID;
    public Label lblQuantity;
    public JFXTextField txtMemberID;
    public JFXTextField txtOrderID;
    public ComboBox cmbQuantity;
    public Label lblSupplimentID;
    public JFXTextField txtSupplimentID;
    public JFXTextField txtQuantity;
    public Button btnOrderA;
    public JFXTextField txtAvailable;
    public ComboBox<String> cmbSupplementId;
    public Label lblQu;

    public void initialize() throws SQLException, ClassNotFoundException {
        cmbSupplementId.setItems(SupplimentCrudcontroller.getsupplimentId());
        txtMemberID.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^(M)[0-9]+$", newValue, txtMemberID, btnOrderA);
        });

        txtOrderID.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^(O)[0-9]+$", newValue, txtOrderID, btnOrderA);
        });


        txtQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9]+$", newValue, txtQuantity, btnOrderA);
        });
        cmbSupplementId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ResultSet resultSet = CrudUtil.execute("SELECT * FROM suppliment WHERE suppliment_Id=?", newValue);

                while (resultSet.next()) {
                    txtAvailable.setText(resultSet.getString(3));

                }


            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        showAvailableQuantity();


    }


    public void NewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) lblContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/Order.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OrderOnAction() {
        Order or = new Order(
                txtOrderID.getText(), txtMemberID.getText(), cmbSupplementId.getValue(), Integer.parseInt(txtQuantity.getText())
        );
        try {
            if (CrudUtil.execute("INSERT INTO orders VALUES (?,?,?,?)", or.getOrder_Id(), or.getMember_Id(), or.getSuppliment_Id(), or.getQuantity())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();

                if(SupplimentCrudcontroller.updateQty(cmbSupplementId.getValue(),txtAvailable.getText())){

                }


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
            URL resource = getClass().getResource("/view/DashBorad.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage.setScene(scene);
            stage.show();

        }
    }

    public void ShowASupplimentOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) lblContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/Suppliment.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

   public void showAvailableQuantity() {

        txtQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!txtQuantity.getText().isEmpty()) {

                if (!txtAvailable.getText().isEmpty() && !txtQuantity.getText().equals("")) {

                    int qty = Integer.parseInt(newValue);
                    int uP = 0;
                    uP = Integer.parseInt(txtAvailable.getText());
                    System.out.println(uP+ "   " +qty+ "   " +(uP-qty));
                    txtAvailable.setText(String.valueOf(uP - qty));
                }
            }

        });
    }


    public void TypeQuantiy(KeyEvent keyEvent) {
    }

    public void PrintOnAction(ActionEvent actionEvent) throws IOException, JRException, SQLException, ClassNotFoundException {
        OrderOnAction();


        String OrderId=txtOrderID.getText();
        String SuplementId=cmbSupplementId.getValue();
        String MemberId=txtMemberID.getText();
        int Quantity=Integer.parseInt(txtQuantity.getText());
        double UnitPrice=OrderDetailsCrudcontroller.getUnitPrice(SuplementId);
        double Total=Quantity * UnitPrice;

        HashMap paramMap=new HashMap();

        paramMap.put("OrderId",OrderId);
        paramMap.put("SuplementId",SuplementId);
        paramMap.put("MemberId",MemberId);
        paramMap.put("Quantity",Quantity);
          paramMap.put("UnitPrice",UnitPrice);
          paramMap.put("Total",Total);




        JasperReport compileReport= (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/reports/Order.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,paramMap,new JREmptyDataSource(1));
        JasperViewer.viewReport(jasperPrint,false);

    }
    }
