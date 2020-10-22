import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ClientUser;
import model.EmployeeType;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        ClientUser.getInstance().setEmployeeType(EmployeeType.CUSTOMER_SERVICE);

        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        setUserAgentStylesheet(STYLESHEET_MODENA);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("JavaFX 13");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        // Bring window to front
        stage.setAlwaysOnTop(true);
        stage.setAlwaysOnTop(false);



    }

    public static void main(String[] args) {
        launch();
    }

}