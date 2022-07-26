package controller;

import com.sun.xml.internal.fastinfoset.tools.FI_SAX_XML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFormcontroller {
    public AnchorPane lblContext;
    public Label lblGetfit;

    public void SignInOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage) lblContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/SignIn.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void SignUpOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage) lblContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/view/SignUp.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
