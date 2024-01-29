package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import org.example.Client.Client;
import org.example.Model.ClientModel;
import org.example.Model.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController {
    public JFXComboBox UsernameCombo;
    public JFXButton btnLogin;
    public JFXButton btnSignIn;
    public JFXPasswordField TxtLoginPassword;
    public AnchorPane root;

    public void initialize() throws SQLException {
        loadClients();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, IOException {

        String name = (String) UsernameCombo.getSelectionModel().getSelectedItem();
        String password = TxtLoginPassword.getText();
        String pass = ClientModel.checkUser(name);

        if (pass.equalsIgnoreCase(password)) {

            Client client = new Client(name);
            new Thread(client).start();
            root.getScene().getWindow().hide();
        }

    }

    public void CmbLoadUsername(ActionEvent actionEvent) {

    }

    public void loadClients() throws SQLException {

        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> names = LoginModel.getClientName();

        for (String name : names) {
            obList.add(name);
        }
        UsernameCombo.setItems(obList);
    }

}
