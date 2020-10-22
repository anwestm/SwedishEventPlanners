package controller;

import controller.serializer.RecordLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.EmployeeType;
import model.record.util.EventPreferences;
import model.record.EventRecord;
import model.record.Record;
import model.workorder.WorkOrder;
import model.workorder.WorkOrderRepository;

import java.time.ZoneId;
import java.util.Date;

public class EventFormController {

    public TextField clientNameField;
    public TextField recordNumberField;
    public TextField eventTypeField;

    public DatePicker startDatePicker;
    public DatePicker endDatePicker;

    public Spinner<Integer> numAttendeesSpinner;
    public Spinner<Integer> budgetSpinner;

    public CheckBox partiesBox;
    public CheckBox foodBox;
    public CheckBox drinksBox;
    public CheckBox photosBox;
    public CheckBox decorBox;

    public Button saveButton;


    private EventRecord record;

    @FXML
    private void initialize() {
        record = new EventRecord();

    }

    public void setRecord(Record newRecord) {
        this.record = (EventRecord) newRecord;

        clientNameField.setText(record.name);
        recordNumberField.setText(Integer.toString(record.id));
        eventTypeField.setText(record.eventType);

        startDatePicker.setValue(record.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        endDatePicker.setValue(record.endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        numAttendeesSpinner.getValueFactory().setValue(record.numParticipants);
        budgetSpinner.getValueFactory().setValue(record.budget);

        partiesBox.setSelected(record.prefs.parties);
        foodBox.setSelected(record.prefs.food);
        drinksBox.setSelected(record.prefs.drinks);
        photosBox.setSelected(record.prefs.photos);
        decorBox.setSelected(record.prefs.decorations);
    }

    public void saveButtonPressed(ActionEvent actionEvent) {

        String clientName = clientNameField.getText();
        int recordNumber = Integer.parseInt(recordNumberField.getText());
        String eventType = eventTypeField.getText();

        Date startDate = Date.from(startDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        int numAttendees = numAttendeesSpinner.getValue();
        int budget = budgetSpinner.getValue();

        EventPreferences prefs = new EventPreferences(
                decorBox.isSelected(),
                partiesBox.isSelected(),
                photosBox.isSelected(),
                foodBox.isSelected(),
                drinksBox.isSelected()
        );

        record = new EventRecord(clientName+""+recordNumber, new Date(), recordNumber, "Me");
        record.eventType = eventType;
        record.startDate = startDate;
        record.endDate = endDate;
        record.budget = budget;
        record.numParticipants = numAttendees;
        record.prefs = prefs;

        RecordLoader loader = new RecordLoader("records");
        loader.saveRecord(record, EventRecord.class);

        System.out.println("Record Save");

        WorkOrder scsReq = new WorkOrder(WorkOrder.WorkType.EVENT_DETAILS_REQUEST, EmployeeType.CUSTOMER_SERVICE, EmployeeType.SENIOR_CUSTOMER_SERVICE, recordNumber);
        scsReq.description = "Please get in contact with the client to get more details for the event.";

        WorkOrderRepository.getInstance().addWorkOrder(scsReq);

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }
}
