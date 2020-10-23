import controller.LoginController;
import controller.serializer.RecordLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ClientUser;
import model.EmployeeType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class TestLogin {



    LoginController controller;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     */
    @Start
    public void start(Stage stage) throws Exception {

        controller = new LoginController();
        controller.passwordField = new PasswordField();
        controller.usernameField = new TextField();

        //stage.setScene(new Scene(new StackPane(), 100, 100));
        //stage.show();
    }

    @Test
    public void testLoginSuccess() throws IOException {

        controller.usernameField.setText("FinMan1");
        controller.passwordField.setText("abc123");

        assertThrows(IllegalStateException.class, () -> {
            controller.loginButtonPressed(null);
        });

        assertEquals(ClientUser.getInstance().getEmployeeType(), EmployeeType.FINANCIAL_MANAGER);
        ClientUser.getInstance().setEmployeeType(null);

    }

    @Test
    public void testLoginFail() throws IOException {

        controller.passwordField.setText("a");
        controller.usernameField.setText("b");

        assertThrows(IllegalStateException.class, () -> {
            controller.loginButtonPressed(null);
        });
        assertEquals(ClientUser.getInstance().getEmployeeType(), null);
        ClientUser.getInstance().setEmployeeType(null);
    }

    @Test
    public void testDivide() {
        assertThrows(ArithmeticException.class, () -> {
            Integer.divideUnsigned(42, 0);
        });
    }


}
