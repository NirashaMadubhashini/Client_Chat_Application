package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerInitializer extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ServerForm.fxml"))));
        primaryStage.setTitle("Live_Chat_Application");
//        primaryStage.getIcons().add(new Image("assets/1.png"));
        primaryStage.show();
    }
}
