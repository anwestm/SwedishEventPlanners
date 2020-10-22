package controller;

import controller.serializer.RecordLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.record.EventRecord;
import model.record.Record;
import model.workorder.WorkOrderRepository;

import java.time.ZoneId;
import java.util.Date;

public class ClientRequestDetailsController {


    public TextArea decorationsArea;
    public TextArea foodArea;
    public TextArea filmingArea;
    public TextArea artArea;
    public TextArea musicArea;
    public TextArea computerArea;
    public TextField otherArea;
    public Button saveButton;
    public TextField clientNameField;
    public TextField clientRecordIdField;
    public TextField eventTypeField;
    public TextField descriptionField;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TextField attendeesField;
    public TextField budgetField;

    private EventRecord record;

    @FXML
    private void initialize() {
        record = new EventRecord();
    }

    public void setRecord(Record newRecord) {
        this.record = (EventRecord) newRecord;

        clientNameField.setText(record.name);
        clientRecordIdField.setText(Integer.toString(record.id));
        eventTypeField.setText(record.eventType);

        startDatePicker.setValue(record.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        endDatePicker.setValue(record.endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        attendeesField.setText(Integer.toString(record.numParticipants));
        budgetField.setText(Integer.toString(record.budget));

        descriptionField.setText(record.description);

        decorationsArea.setText(record.decorations);
        foodArea.setText(record.foodAndDrinks);
        filmingArea.setText(record.filmingAndPhotos);
        musicArea.setText(record.music);
        artArea.setText(record.postersAndArt);
        computerArea.setText(record.computerIssues);
        otherArea.setText(record.otherNeeds);

    }

    public void onSaveButtonClicked(ActionEvent event) {

        record.id = Integer.parseInt(clientRecordIdField.getText());
        record.name = clientNameField.getText();
        record.eventType = eventTypeField.getText();
        record.description = descriptionField.getText();

        record.numParticipants = Integer.parseInt(attendeesField.getText());
        record.budget = Integer.parseInt(budgetField.getText());

        record.startDate = Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        record.endDate = Date.from(endDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        record.decorations = decorationsArea.getText();
        record.foodAndDrinks = foodArea.getText();
        record.filmingAndPhotos = filmingArea.getText();
        record.music = musicArea.getText();
        record.postersAndArt = artArea.getText();
        record.computerIssues = computerArea.getText();
        record.otherNeeds = otherArea.getText();

        RecordLoader loader = new RecordLoader("records");
        loader.saveRecord(record, EventRecord.class);

        //WorkOrderRepository.getInstance().addWorkOrder(new Wo);

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }
}
