package controller;

import controller.serializer.RecordLoader;
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
    public RecordLoader recordLoader;


    @FXML
    private void initialize() {
        recordList.setCellFactory(new RecordListItemFactory());

        recordLoader = new RecordLoader("records");

        recordLoader.getAvailableRecords();

        for (Record r : recordLoader.getAvailableRecords())
            recordList.getItems().add(r);


        recordList.getItems().add(new EventRecord("Kungens födelsedag", new Date(0), 123, "Andreas"));
    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        System.out.println("Clicked");
        recordList.getItems().add(new EventRecord("Kungens födelsedag", new Date(0), 123, "Andreas"));

    }

    public EventFormController openRecordForm(ActionEvent event) {
        Parent root;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(getClass().getResource("../event_form.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Event Request Form");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.show();



            // Hide this current window (if this is what you want)
            //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return loader.getController();
    }

    public void createRecordButton(ActionEvent actionEvent) {
        openRecordForm(actionEvent);
    }

    public void openRecordButton(ActionEvent actionEvent) {
        EventFormController controller = openRecordForm(actionEvent);
        controller.setRecord(recordList.getSelectionModel().getSelectedItems().get(0));
    }

}
