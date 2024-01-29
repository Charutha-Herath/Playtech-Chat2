package org.example.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.Server.Server;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Server server = Server.getServerSocket();
        Thread thread = new Thread(server);
        thread.start();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ServerForm.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Server Form");
        stage.show();
    }
}