package controller;

import Model.Payments;
import Model.Suppliment;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.exolab.castor.types.DateTime;
import util.CrudUtil;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static java.lang.Double.parseDouble;
import static net.sf.jasperreports.engine.JasperFillManager.fillReport;

public class Paymentscontroller {
    public AnchorPane lblContext;
    public Label lblPayment;
    public Label lblPackageID;
    public Label lblMemberID;
    public Label lblPaymentID;
    public Label lblAmount;
    public JFXTextField txtMemberID;
    public JFXTextField txtPackageId;
    public JFXTextField txtPaymentID;
    public JFXTextField txtAmount;
    public Label lblDate;
    public JFXTextField txtDate;
    public DatePicker Datepicker;
    public Button btnAdds;
    public Button btnSearch;
    public Button btnPayment;
    public ComboBox<String> cmbPId;

    public void initialize() throws SQLException, ClassNotFoundException {
        cmbPId.setItems(PackageCrudcontroller.getPackageId());
        txtMemberID.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^(M)[0-9]+$",newValue,txtMemberID,btnPayment);
        });

        txtPaymentID.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^(Py)[0-9]+$",newValue,txtPaymentID,btnPayment);
        });

        txtAmount.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9].[0-9][0,2]+$",newValue,txtAmount,btnPayment);
        });
        cmbPId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ResultSet resultSet = CrudUtil.execute("SELECT * FROM package WHERE package_Id=?", newValue);

                while (resultSet.next()) {
                    txtAmount.setText(resultSet.getString(5));

                }


            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

    }




    public void AddOnAction() throws ParseException {
        //for  update date
        Date DD = new SimpleDateFormat("yyyy-MM-dd").parse(Datepicker.getValue().toString());


        java.sql.Date handOverDate = new java.sql.Date(DD.getTime());

        Payments py = new Payments(
                txtPaymentID.getText(),cmbPId.getValue(),txtMemberID.getText(), handOverDate, parseDouble(txtAmount.getText())
        );
        try {
            if (CrudUtil.execute("INSERT INTO payment VALUES (?,?,?,?,?)",py.getPayment_Id(),py.getPackage_Id(),py.getMember_Id(),py.getP_Date(),py.getPrice())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
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

    public void ShowPackageOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/Packages.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void SearchOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lblContext.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("/view/PaymentDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1 = new Stage();
        stage.setScene(scene);
        stage.show();


    }

    public void PrintOnAction(ActionEvent actionEvent) throws ParseException, JRException {

        AddOnAction();

        String MemberId=txtMemberID.getText();
        String PackageId=cmbPId.getValue();
        String PaymentId=txtPaymentID.getText();
        double Amount= Double.parseDouble(txtAmount.getText());

      HashMap paramMap=new HashMap();

        paramMap.put("MemberId",MemberId);
        paramMap.put("PackageId",PackageId);
        paramMap.put("PaymentId",PaymentId);
        paramMap.put("Amount",Amount);




        JasperReport compileReport= (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/reports/Payment2.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,paramMap,new JREmptyDataSource(1));
        JasperViewer.viewReport(jasperPrint,false);

    }



    public void NewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) lblContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/Payments.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
