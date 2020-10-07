import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.EventRecord;
import model.Record;
import view.RecordListItem;
import view.RecordListItemFactory;

import java.io.IOException;
import java.util.Date;

public class RecordTabController {


    public ListView<Record> recordList;

    @FXML
    private void initialize() {
        recordList.setCellFactory(new RecordListItemFactory());
    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        System.out.println("Clicked");
        recordList.getItems().add(new EventRecord("Kungens födelsedag", new Date(0), 123, "Andreas"));

    }

    public void createRecordButton(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("event_form.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Event Request Form");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
