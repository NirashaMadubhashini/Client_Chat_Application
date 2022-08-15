package server;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController extends Thread{


    public Label activeNow;
    public TextField txtServerMsg;
    public TextArea txtServerPane;
    public Button button;
    public VBox vBox;
    public Pane emojiPane;
    public Label serverlbl;
    Socket accept=null;
    String messageIn = "";

    public FileChooser chooser;
    public File path;

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String username;
    public void initialize() {
        emojiPane.setVisible(false);
        new Thread(() -> {
            try {
                ServerSocket serverSocket= new ServerSocket(5000);
                System.out.println("Server started!");
                accept= serverSocket.accept();
                System.out.println("Client Connected!");
                InputStreamReader inputStreamReader =
                        new InputStreamReader(accept.getInputStream());
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String record= bufferedReader.readLine();
                System.out.println(record);
                txtServerPane.appendText("\n\n" +record);

                while (!record.equals("Exit")) {
                    record = bufferedReader.readLine();
                    txtServerPane.appendText("\n\n" + record.trim() + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



    public void serverMsgSendOnAction(MouseEvent mouseEvent) throws IOException {
        String messageToSend = txtServerMsg.getText();
        PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
        printWriter.println(txtServerMsg.getText());
        System.out.println("Server Writer :" + printWriter);
//        txtServerPane.appendText("\n\n\t\t\t\t\t\t\t\t\t\t\t\tMe:" + txtServerMsg.getText());

        if (!messageToSend.isEmpty()){
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

            txtServerMsg.clear();
            printWriter.flush();
        }
    }


    public void serverUploadImageOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        chooser = new FileChooser();
        chooser.setTitle("Open Image");
        this.path = chooser.showOpenDialog(stage);
        printWriter.println(serverlbl.getText() + " " + "img" + path.getPath());
        printWriter.flush();
    }

    public void emoji2OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDE42");
    }

    public void emoji3OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDE0E");
    }

    public void emoji4OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDE02");
    }

    public void emoji5OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83E\uDD17");
    }

    public void emoji6OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDC96");
    }

    public void emoji7OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDE0D");
    }

    public void emoji8OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDE34");
    }

    public void emoji9OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDE2E");
    }

    public void emoji10OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83E\uDD73");
    }

    public void emoji11OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDE21");
    }

    public void emoji12OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDE1F");
    }

    public void emoji13OnAction(MouseEvent mouseEvent) {
        txtServerMsg.appendText("\uD83D\uDE2D");
    }

    public void openEmojiPaneOnAction(MouseEvent mouseEvent) {
        if (!emojiPane.isVisible()) {
            emojiPane.setVisible(true);
        } else {
            emojiPane.setVisible(false);
        }
    }
}