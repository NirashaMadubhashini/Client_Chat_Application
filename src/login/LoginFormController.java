package Login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController{


    public static String userName;
    public Button btnLogin;
    public TextField txtUserName;


    public void userLogInOnAction(ActionEvent primaryStage) throws IOException {
        userName=txtUserName.getText();
        Stage stage = (Stage) txtUserName.getScene().getWindow();
        stage.close();
        Stage stage1=new Stage();
        stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../client/ClientForm.fxml"))));
        stage1.setResizable(false);
        stage1.setTitle("Live_Chat_Application");
        stage1.centerOnScreen();
        stage1.show();
    }


}