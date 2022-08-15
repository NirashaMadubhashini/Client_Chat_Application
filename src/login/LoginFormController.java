package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController{

    public Button button;
    public TextField txtUsername;

    public static String userName;


    public void userLogInOnAction(ActionEvent primaryStage) throws IOException {
        userName=txtUsername.getText();
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        stage.close();
        Stage stage1=new Stage();
        stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../client/ClientForm.fxml"))));
        stage1.setResizable(false);
        stage1.setTitle("Live_Chat_Application");
        stage1.centerOnScreen();
        stage1.show();
    }


}