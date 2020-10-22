package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ClientUser;
import model.EmployeeType;

import java.io.IOException;

public class LoginController {


    private static class User {
        public String username;
        public String password;
        public EmployeeType type;

        public User(String username, String password, EmployeeType type) {
            this.username = username;
            this.password = password;
            this.type = type;
        }
    }

    private User[] users = new User[]{
            new User("FinMan1", "abc123", EmployeeType.FINANCIAL_MANAGER),
            new User("CustServ1", "abc123", EmployeeType.FINANCIAL_MANAGER)
    };


    public PasswordField passwordField;
    public TextField usernameField;
    public Button loginButton;

    public void loginButtonPressed(ActionEvent event) {
        String enteredPassword = passwordField.getText();
        String enteredUsername = usernameField.getText();

        for (User u : users) {
            if (enteredUsername.contentEquals(u.username) && enteredPassword.contentEquals(u.password)) {
                System.out.println("Login Succesfully");

                Parent root;
                FXMLLoader loader = null;
                try{
                    loader = new FXMLLoader(getClass().getResource("../scene.fxml"));
                    root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Swedish Event Planners");
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.setAlwaysOnTop(true);
                    stage.show();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                ClientUser.getInstance().setEmployeeType(u.type);

                ((Stage)loginButton.getScene().getWindow()).close();

            } else {
                Alert alert =  new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Invalid Password");
                alert.showAndWait();
            }
        }

    }
}
