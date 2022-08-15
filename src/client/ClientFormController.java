package client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.net.Socket;

public class ClientFormController extends Thread{

    public TextArea txtClientPane;
    public TextField txtClientMsg;
    public Button button;
    public Label clientlbl;
    public ImageView imgSendImages;
    public VBox vBox;
    public Pane emojiPane;
    Socket socket = null;

    private FileChooser fileChooser;
    private File filePath;
    PrintWriter writer;
    BufferedReader reader;

    private PrintWriter printWriter;


    public void initialize() throws IOException {
        emojiPane.setVisible(false);
        String userName = Login.LoginFormController.userName;
        clientlbl.setText(userName);

        new Thread(() -> {
            try {
                socket = new Socket("localhost", 5000);
                InputStreamReader inputStreamReader =
                        new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                String record = bufferedReader.readLine();
                System.out.println(record);
                txtClientPane.appendText("\n\nServer :" + record);

                while (!record.equals("Exit")) {
                    record = bufferedReader.readLine();
                    txtClientPane.appendText("\n\nServer :" + record.trim() + "\n");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }



    public void clientMsgSendOnAction(MouseEvent mouseEvent) throws IOException {
        String messageToSend = txtClientMsg.getText();
        String massage = txtClientMsg.getText();
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(clientlbl.getText() + ": " + massage);
        System.out.println( "Client Writer :" + printWriter);

        if (massage.equalsIgnoreCase("exit")) {
            Stage stage = (Stage) txtClientMsg.getScene().getWindow();
            stage.close();
        }else if (!messageToSend.isEmpty()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);

            hBox.setPadding(new Insets(5, 5, 5, 10));
            Text text = new Text(messageToSend);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle("-fx-color: rgb(239,242,255);"+"-fx-background-color: rgb(15,125,242);"+" -fx-background-radius: 20px");

            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(0.934, 0.945, 0.996));

            hBox.getChildren().add(textFlow);
            vBox.getChildren().add(hBox);

            txtClientMsg.clear();
            printWriter.flush();
        }
    }


    public void clientUploadImageOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        printWriter.println(clientlbl.getText() + " " + "img" + filePath.getPath());
        printWriter.flush();

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