package controller;

import controller.serializer.RecordLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.record.EventRecord;
import model.record.FinancialRequestRecord;
import model.record.Record;
import view.RecordListItemFactory;

import java.io.IOException;
import java.util.Date;

public class RecordTabController {

    public ListView<Record> recordList;
    public RecordLoader recordLoader;

    public TabPane tabPane;

    @FXML
    private void initialize() {
        recordList.setCellFactory(new RecordListItemFactory());

        recordLoader = new RecordLoader("records");
        recordLoader.getAvailableRecords();

        for (Record r : recordLoader.getAvailableRecords())
            recordList.getItems().add(r);


        //recordList.getItems().add(new EventRecord("Kungens födelsedag", new Date(0), 123, "Andreas"));
    }

    public void focusGained() {

    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        System.out.println("Clicked");
        recordList.getItems().add(new EventRecord("Kungens födelsedag", new Date(0), 123, "Andreas"));

    }

    private Object createFormWindow(String path, String title){

        Parent root;
        FXMLLoader loader = null;
        try{
            loader = new FXMLLoader(getClass().getResource(path));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return loader.getController();
    }

    @FXML
    public void financialRequestClicked(ActionEvent actionEvent){
        createFormWindow("../financial_request.fxml", "Financial Request Form");
    }

    @FXML
    public void clientRequestDetailsClicked(ActionEvent actionEvent){
        createFormWindow("../client_request_details.fxml", "Client Request Details Form");
    }

    @FXML
    public void recruitmentRequestClicked(ActionEvent actionEvent){
        createFormWindow("../recruitment_request.fxml", "Recruitment Request Form");
    }

    @FXML
    public void createClientRecordClicked(ActionEvent actionEvent){
        createFormWindow("../client_record.fxml", "Client Form");
    }

    public void createEventRecordButton(ActionEvent actionEvent) {
        createFormWindow("../event_form.fxml", "Event Request Form");
    }

    public Object openRecordForm(String path, String title) {
        return createFormWindow(path, title);
    }



    public void openRecordButton(ActionEvent actionEvent) {

        Record selectedRecord = recordList.getSelectionModel().getSelectedItems().get(0);
        
        if (selectedRecord instanceof EventRecord) {
            EventFormController controller = (EventFormController) openRecordForm("../event_form.fxml", "Event Request Form");
            controller.setRecord(selectedRecord);
        }

        if (selectedRecord instanceof FinancialRequestRecord) {
            FinancialRequestController controller = (FinancialRequestController) openRecordForm("../financial_request.fxml", "Financial Request Form");
            controller.setRecord(selectedRecord);
        }
        
        //EventFormController controller = (EventFormController) openRecordForm(actionEvent);
        //controller.setRecord(recordList.getSelectionModel().getSelectedItems().get(0));
    }

    public void deleteRecordButton(ActionEvent event) {
    }

    public void updateRecordList(ActionEvent event) {
        recordLoader.getAvailableRecords();

        outer : for (Record r1 : recordLoader.getAvailableRecords()) {
            for (Record r2 : recordList.getItems()) {
                if (r1.id == r2.id) {
                    continue outer;
                }
            }
            recordList.getItems().add(r1);
        }
    }
}
