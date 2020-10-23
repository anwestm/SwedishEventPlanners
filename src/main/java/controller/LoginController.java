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

    private final User[] users = new User[]{

            new User("CustServ1", "abc123", EmployeeType.CUSTOMER_SERVICE),
            new User("SenCustServ1", "abc123", EmployeeType.SENIOR_CUSTOMER_SERVICE),
            new User("FinMan1", "abc123", EmployeeType.FINANCIAL_MANAGER),
            new User("AdmMan1", "abc123", EmployeeType.ADMINISTRATION_MANAGER),

            new User("ServMan1", "abc123", EmployeeType.SERVICE_MANAGER),
            new User("ProdMan1", "abc123", EmployeeType.PRODUCTION_MANAGER),

            new User("chef1", "abc123", EmployeeType.CHEF),
            new User("photo1", "abc123", EmployeeType.PHOTOGRAPHER),

            new User("HR1", "abc123", EmployeeType.HR_EMPLOYEE),

    };


    public PasswordField passwordField;
    public TextField usernameField;
    public Button loginButton;

    public void loginButtonPressed(ActionEvent event) {
        String enteredPassword = passwordField.getText();
        String enteredUsername = usernameField.getText();

        User succes_user = null;
        for (User u : users) {
            if (enteredUsername.contentEquals(u.username) && enteredPassword.contentEquals(u.password)) {
                succes_user = u;
            }
        }

        if (succes_user != null) {
            System.out.println("Login Succesfully");

            ClientUser.getInstance().setEmployeeType(succes_user.type);

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
                stage.setAlwaysOnTop(false);
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            ((Stage)loginButton.getScene().getWindow()).close();

        } else {
            Alert alert =  new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Password");
            alert.setContentText("Please contact system administrator (phone: 08-580 321 12 12) to reset your password");
            alert.showAndWait();
        }

    }
}
