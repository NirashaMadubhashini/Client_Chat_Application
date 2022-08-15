package server;

import client.ClientHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInitializer{

    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10001);
        Socket accept;

        while (true) {
            System.out.println("waiting for client");
            accept = serverSocket.accept();
            System.out.println("new member connected!");
            ClientHandler clientHandler = new ClientHandler(accept, clientHandlers);
            clientHandlers.add(clientHandler);
            clientHandler.start();

        }


    }
}
