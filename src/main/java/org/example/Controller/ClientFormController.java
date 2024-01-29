package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.Client.Client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class ClientFormController implements Initializable {

    public JFXButton btnSend;
    public JFXTextField txtField;
    public javafx.scene.layout.VBox VBox;
    public ImageView imgSelection;
    public Label lblUsername;
    private Client client;

    public ClientFormController() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void btnSendOnAction(ActionEvent actionEvent) {

        String text = txtField.getText();
        try {
            if (text != null) {
                appendText(text);
                client.sendMsg(text);
            } else {
                ButtonType ok = new ButtonType("Ok");
                ButtonType cancel = new ButtonType("Cancel");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Empty message. Is it ok?", ok, cancel);
                alert.showAndWait();
                ButtonType results = alert.getResult();
                if (results.equals(ok)) {
                    client.sendMsg(null);
                }
                txtField.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void imgChooseOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                byte[] bytes = Files.readAllBytes(selectedFile.toPath());
                HBox hBox = new HBox();
                hBox.setStyle("-fx-fill-height: true; -fx-min-height: 50; -fx-pref-width: 520; -fx-max-width: 520; -fx-padding: 10; -fx-alignment: center-right;");



                ImageView imageView = new ImageView(new Image(new FileInputStream(selectedFile)));
                imageView.setStyle("-fx-padding: 10px;");
                imageView.setFitHeight(180);
                imageView.setFitWidth(180);

                hBox.getChildren().addAll(imageView);
                VBox.getChildren().add(hBox);

                client.sendImg(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void appendText(String msg) {
        HBox hBox = new HBox();
        hBox.setStyle("-fx-alignment: center-right;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
        Label label = new Label(msg);
        label.setStyle("-fx-background-color: #B3E5FC;-fx-background-radius: 15;-fx-font-size: 18;-fx-font-weight: normal;-fx-text-fill: black;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
        hBox.getChildren().add(label);
        VBox.getChildren().add(hBox);
        txtField.clear();
    }

    public void writeMsg(String msg) {
        Platform.runLater(() -> {
            HBox hBox = new HBox();
            hBox.setStyle("-fx-alignment: center-left;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
            Label label = new Label(msg);
            label.setStyle("-fx-background-color: #0277BD;-fx-background-radius: 15;-fx-font-size: 18;-fx-font-weight: normal;-fx-text-fill: black;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
            hBox.getChildren().add(label);
            VBox.getChildren().add(hBox);
        });
    }

    public void setImg(byte[] bytes, String sender) {

        Platform.runLater(() -> {

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefSize(180, 180);
            anchorPane.setStyle("-fx-background-color:#0277BD ;" +
                    "-fx-padding: 10px;" +
                    "-fx-background-radius: 20;");

            Label label = new Label(sender);
            label.setStyle("-fx-background-color: #0277BD;" +
                    "-fx-padding-bottom: 5px;" +
                    "-fx-font-size: 12px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-text-fill: #black;" +
//                    "-fx-font-family: Arial; " +
                    "-fx-background-radius: 10;");

            AnchorPane.setTopAnchor(label, 2.0);
            AnchorPane.setLeftAnchor(label, 2.0);
            anchorPane.getChildren().add(label);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Image image = new Image(byteArrayInputStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(180);
            imageView.setFitHeight(180);
            AnchorPane.setTopAnchor(imageView, 20.0);
            AnchorPane.setRightAnchor(imageView, 0.0);
            anchorPane.getChildren().add(imageView);

            HBox messageContainer = new HBox(anchorPane);
            messageContainer.setAlignment(Pos.CENTER_LEFT);
            VBox.getChildren().add(messageContainer);
            VBox.setSpacing(10);
        });

    }

    public void setLblname(String name) {
        lblUsername.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
