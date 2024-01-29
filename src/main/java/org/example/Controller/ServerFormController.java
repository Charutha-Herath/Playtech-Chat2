package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerFormController {


    public void btnClientAddOnAction(ActionEvent mouseEvent) throws IOException {
        System.out.println(" btn");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginForm.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setTitle("Add New Client");
        stage.setScene(scene);
        stage.show();
    }

    public void btnClientAddOnAction1(MouseEvent mouseEvent) {

    }
}
