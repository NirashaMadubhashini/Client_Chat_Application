package client;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import login.LoginFormController;


import java.io.*;
import java.net.Socket;

public class ClientFormController extends Thread{
    public Label clientlbl;
    public TextField txtClientMsg;
    public ImageView imgSendImages;
    public VBox vBox;
    public Pane emojiPane;

    public FileChooser chooser;
    public File path;
    private PrintWriter printWriter;
    public Label activeNow;
    private Socket socket;
    private BufferedReader bufferedReader;
    private String username;


    public void initialize() throws IOException {
        emojiPane.setVisible(false);

        String userName = LoginFormController.userName;
        clientlbl.setText(userName);

        try {
            socket = new Socket("localhost", 5000);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clientMsgSendOnAction(MouseEvent mouseEvent) {
        String massage = txtClientMsg.getText();
        printWriter.println(clientlbl.getText() + ": " + massage);

        if (massage.equalsIgnoreCase("exit")) {
            Stage stage = (Stage) txtClientMsg.getScene().getWindow();
            stage.close();
        }else if (!massage.isEmpty()) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);

            hBox.setPadding(new Insets(5, 5, 5, 10));
            Text text = new Text(massage);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle("-fx-color: rgb(239,242,255);" + "-fx-background-color: rgb(15,125,242);" + " -fx-background-radius: 20px");

            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(0.934, 0.945, 0.996));

            hBox.getChildren().add(textFlow);
            vBox.getChildren().add(hBox);

            txtClientMsg.clear();
            printWriter.flush();
        }
    }

    public void clientUploadImageOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        chooser = new FileChooser();
        chooser.setTitle("Open Image");
        this.path = chooser.showOpenDialog(stage);
        printWriter.println(clientlbl.getText() + " " + "img" + path.getPath());
        printWriter.flush(); 
    }


    public void run() {
        try {
            while (true) {
                String massage = bufferedReader.readLine();
                String[] tokens = massage.split(" ");
                String command = tokens[0];

                StringBuilder clientMassage = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    clientMassage.append(tokens[i] + " ");
                }

                String[] massageAr = massage.split(" ");
                String string = "";
                for (int i = 0; i < massageAr.length - 1; i++) {
                    string += massageAr[i + 1] + " ";
                }

                Text text = new Text(string);
                String fChar = "";

                if (string.length() > 3) {
                    fChar = string.substring(0, 3);
                }

                if (fChar.equalsIgnoreCase("img")) {
                    string = string.substring(3, string.length() - 1);

                    File file = new File(string);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitWidth(150);
                    imageView.setFitHeight(200);

                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);

                    if (!command.equalsIgnoreCase(clientlbl.getText())) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);

                        Text text1 = new Text("  " + command + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);
                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(" ");
                        hBox.getChildren().add(text1);
                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));

                } else {
                    TextFlow tempTextFlow = new TextFlow();

                    if (!command.equalsIgnoreCase(clientlbl.getText() + ":")) {
                        Text name = new Text(command + " ");
                        name.getStyleClass().add("name");
                        tempTextFlow.getChildren().add(name);
                    }

                    tempTextFlow.getChildren().add(text);
                    tempTextFlow.setMaxWidth(200);

                    TextFlow textFlow = new TextFlow(tempTextFlow);
                    HBox hBox = new HBox(12);

                    if (!command.equalsIgnoreCase(clientlbl.getText() + ":")) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(textFlow);
                    }
//                    else {
//                        Text text1 = new Text(clientMassage+"");
//                        TextFlow textFlow1 = new TextFlow(text1);
//                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
//                        hBox.getChildren().add(textFlow1);
//                    }
                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void emoji2OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE42");
    }

    public void emoji3OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE0E");
    }

    public void emoji4OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE02");
    }

    public void emoji5OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83E\uDD17");
    }

    public void emoji6OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDC96");
    }

    public void emoji7OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE0D");
    }

    public void emoji8OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE34");
    }

    public void emoji9OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE2E");
    }

    public void emoji10OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83E\uDD73");
    }

    public void emoji11OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE21");
    }

    public void emoji12OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE1F");
    }

    public void emoji13OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE2D");
    }

    public void openEmojiPaneOnAction(MouseEvent mouseEvent) {
        if (!emojiPane.isVisible()) {
            emojiPane.setVisible(true);
        } else {
            emojiPane.setVisible(false);
        }
    }

}