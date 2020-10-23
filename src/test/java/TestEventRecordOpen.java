import com.fasterxml.jackson.databind.ObjectMapper;
import controller.EventFormController;
import controller.LoginController;
import controller.RecordTabController;
import controller.serializer.RecordLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.record.EventRecord;
import model.record.FinancialRequestRecord;
import model.record.JobRecord;
import model.record.Record;
import model.record.util.EventPreferences;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import view.RecordListItemFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(ApplicationExtension.class)
public class TestEventRecordOpen {

    static class RecordLoaderStub extends RecordLoader {

        private Record record;

        public RecordLoaderStub() {
            super("");
        }


        public void saveRecord(Record record, Class<?> recordType) {
            this.record = record;
        }

        public ArrayList<Record> getAvailableRecords() {
            ArrayList<Record> list = new ArrayList<>();
            list.add(record);
            return list;
        }

    }


    RecordTabController tabController;
    EventFormController eventFormController;

    @Start
    public void start(Stage stage) throws Exception {

        RecordLoader recordLoaderStub = new RecordLoaderStub();

        tabController = new RecordTabController();
        tabController.recordLoader = recordLoaderStub;
        tabController.recordList = new ListView<>();
        tabController.recordList.setCellFactory(new RecordListItemFactory());
        tabController.recordTypeChoiceBox = new ChoiceBox<>();
        tabController.recordTypeChoiceBox.setValue("All Records");


        eventFormController = new EventFormController();
        eventFormController.loader = recordLoaderStub;
        eventFormController.recordNumberField = new TextField();
        eventFormController.eventTypeField = new TextField();
        eventFormController.clientNameField = new TextField();
        eventFormController.startDatePicker = new DatePicker();
        eventFormController.endDatePicker = new DatePicker();
        eventFormController.numAttendeesSpinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        eventFormController.budgetSpinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        eventFormController.partiesBox = new CheckBox();
        eventFormController.foodBox = new CheckBox();
        eventFormController.drinksBox = new CheckBox();
        eventFormController.photosBox = new CheckBox();
        eventFormController.decorBox = new CheckBox();
        eventFormController.saveButton = new Button();




        //stage.setScene(new Scene(new StackPane(), 100, 100));
        //stage.show();
    }

    @Test
    public void testEditEvent() {

        // Setup Environment
        EventRecord e1 = new EventRecord("TestRecord", new Date(), 122333, "JUnit");
        e1.startDate = new Date();
        e1.endDate = new Date();
        e1.eventType = "Event Type";
        e1.prefs = new EventPreferences();


        tabController.recordList.getItems().add(e1);

        // Select Record
        tabController.recordList.getSelectionModel().select(0);
        eventFormController.setRecord(e1);

        // Input new Text
        eventFormController.eventTypeField.setText("testType");;

        // Save Record
        assertThrows(Exception.class, () -> {
            eventFormController.saveButtonPressed(null);
        });

        // Load record again
        tabController.recordList.getItems().clear();
        tabController.updateRecordList(null);
        EventRecord e2 = (EventRecord) tabController.recordList.getItems().get(0);

        // Test value
        assertEquals("testType", e2.eventType);

    }

}
