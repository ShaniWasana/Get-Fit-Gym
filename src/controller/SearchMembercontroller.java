package controller;


import Model.Member;
import com.jfoenix.controls.JFXTextField;
import db.DataSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;

public class SearchMembercontroller {
    public AnchorPane lblContext;
    public Label lblSearchMenber;
    public TableView<Member> SMTable;
    public JFXTextField txtMemberID;
    public TableColumn lblMemberID;

    public TableColumn lblAddress;
    public TableColumn lblContactNo;
    public TableColumn lblAge;
    public TableColumn lblPackageID;
    public TableColumn lblSheduleID;
    public TableColumn lblFullName;
    public JFXTextField txtSearch;

    public  void initialize(){


        lblMemberID.setCellValueFactory(new PropertyValueFactory("Member_Id"));
        lblFullName.setCellValueFactory(new PropertyValueFactory("Full_Name"));
        lblAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        lblContactNo.setCellValueFactory(new PropertyValueFactory("Cotact_No"));
        lblAge.setCellValueFactory(new PropertyValueFactory("Age"));
        lblPackageID.setCellValueFactory(new PropertyValueFactory("Package_Id"));
        lblSheduleID.setCellValueFactory(new PropertyValueFactory("Schedule_Id"));


        try {
            loadAllmember();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllmember() throws ClassNotFoundException, SQLException {
        System.out.println("Load all");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymmanagementsystem","root","1234");
        String sql="SELECT *FROM member";
        Statement statement=con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println(result);

        ObservableList<Member> oblist = FXCollections.observableArrayList();
        while (result.next()){
            oblist.add(
                    new Member(
                            result.getString("member_Id"),
                            result.getString("name"),
                            result.getString("address"),
                            result.getString("contact_No"),
                            result.getInt("age"),
                            result.getString("package_Id"),
                            result.getString("schedule_Id")




                    )
            );
        }
        SMTable.setItems(oblist);
        System.out.println(oblist);
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) lblContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/AddMember.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }


    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Member selectedItem = SMTable.getSelectionModel().getSelectedItem();

        BoxBlur blur = new BoxBlur(5, 5, 5);
        lblContext.setEffect(blur);


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure you want to Delete?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Member!!");
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            SearchCrudcontroller.deleteMember(selectedItem);
            loadAllmember();

            lblContext.setEffect(null);
        } else {
            lblContext.setEffect(null);
        }

    }

    public void searchKeyReleased(javafx.scene.input.KeyEvent keyEvent) {
        if(txtSearch.getText().startsWith("M")){
            ObservableList<Member> ob = FXCollections.observableArrayList(
                    SearchCrudcontroller.searchReportByMemberId(
                            "%"+txtSearch.getText()+"%"
                    )
            );
            SMTable.setItems(ob);
        }else{
            ObservableList<Member> obj = FXCollections.observableArrayList(SearchCrudcontroller.searchReportByMemberName("%"+txtSearch.getText()+"%"));
            SMTable.setItems(obj);
        }

    }
}


