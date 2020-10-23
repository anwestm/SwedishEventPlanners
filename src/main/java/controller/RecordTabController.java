package controller;

import controller.serializer.RecordLoader;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ClientUser;
import model.EmployeeType;
import model.record.EventRecord;
import model.record.FinancialRequestRecord;
import model.record.JobRecord;
import model.record.Record;
import model.workorder.WorkOrder;
import model.workorder.WorkOrderRepository;
import view.RecordListItemFactory;
import view.WorkOrderItemFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecordTabController {

    public ListView<Record> recordList;
    public ListView<WorkOrder> workOrderList;
    public RecordLoader recordLoader;

    public ChoiceBox<String> recordTypeChoiceBox;

    public TabPane tabPane;
    public TextField searchField;

    public Button eventRecordButton;
    public Button finReqButton;
    public Button clientReqButton;
    public Button jobReqButton;
    public Button openButton;

    @FXML
    private void initialize() {
        recordList.setCellFactory(new RecordListItemFactory());

        recordLoader = new RecordLoader("records");
        recordLoader.getAvailableRecords();

        Set<Class<? extends Record>> recordTypes = new HashSet<>();

        for (Record r : recordLoader.getAvailableRecords()) {
            recordList.getItems().add(r);
            recordTypes.add(r.getClass());
        }

        recordTypeChoiceBox.getItems().add("All Records");
        for (Class<? extends Record> c : recordTypes) {
            recordTypeChoiceBox.getItems().add(c.getSimpleName());
        }
        recordTypeChoiceBox.setValue("All Records");

        workOrderList.setCellFactory(new WorkOrderItemFactory());
        WorkOrderRepository.getInstance().addListViewSubscription(workOrderList);

        EmployeeType currentUser = ClientUser.getInstance().getEmployeeType();

        if (currentUser != EmployeeType.DEBUG) {
            eventRecordButton.setDisable(true);
            finReqButton.setDisable(true);
            clientReqButton.setDisable(true);
            jobReqButton.setDisable(true);
        }

        System.out.println("CURRENT USER:" + currentUser);
        if(currentUser == EmployeeType.CUSTOMER_SERVICE)
            eventRecordButton.setDisable(false);

        if (currentUser == EmployeeType.SENIOR_CUSTOMER_SERVICE)
            clientReqButton.setDisable(false);

        if (currentUser == EmployeeType.SENIOR_CUSTOMER_SERVICE || currentUser == EmployeeType.PRODUCTION_MANAGER || currentUser == EmployeeType.SERVICE_MANAGER)
            finReqButton.setDisable(false);

        if (currentUser == EmployeeType.HR_EMPLOYEE || currentUser == EmployeeType.PRODUCTION_MANAGER || currentUser == EmployeeType.SERVICE_MANAGER)
            jobReqButton.setDisable(false);


        openButton.disableProperty().bind(Bindings.size(recordList.getSelectionModel().getSelectedItems()).lessThan(1));
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
            stage.setAlwaysOnTop(false);
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
        List<Record> selectedItems = recordList.getSelectionModel().getSelectedItems();
        if (selectedItems.size() > 0) {
            Record selectedRecord = selectedItems.get(0);

            if (selectedRecord instanceof EventRecord) {
                ClientRequestDetailsController controller = (ClientRequestDetailsController) openRecordForm("../client_request_details.fxml", "Event Request Form");
                controller.setRecord(selectedRecord);
            } else {
                Alert alert =  new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Selected record must be an event-record");
                alert.showAndWait();
            }
        } else {
            Alert alert =  new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("An existing event-record must selected");
            alert.showAndWait();
        }

    }

    @FXML
    public void recruitmentRequestClicked(ActionEvent actionEvent){
        createFormWindow("../recruitment_request.fxml", "Recruitment Request Form");
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

        if (selectedRecord instanceof JobRecord) {
            RecruitmentRequestController controller = (RecruitmentRequestController) openRecordForm("../recruitment_request.fxml", "Recruitment Request Form");
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

            //System.out.println("Class name: " + r1.getClass().getSimpleName());
            if (!recordTypeChoiceBox.getValue().equals("All Records")) {
                if (!recordTypeChoiceBox.getValue().equals(r1.getClass().getSimpleName()))
                    continue;
            }

            for (Record r2 : recordList.getItems()) {
                if (r1.id == r2.id && r1.getClass() == r2.getClass()) {
                    continue outer;
                }
            }
            recordList.getItems().add(r1);
        }
    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        for (int i = 0; i < recordList.getItems().size(); i++) {
            Record r = recordList.getItems().get(i);
            if (Integer.toString(r.id).startsWith(searchField.getText()))
                continue;
            recordList.getItems().remove(i--); // item removal will shift list to the left (after the index)
        }
        if (recordList.getItems().size() == 0) {
            Alert alert =  new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("No record found!");
            alert.showAndWait();
        }
    }

    public void onRecordTypeChoiceChanged(ActionEvent event) {
        recordList.getItems().clear();
        updateRecordList(event);
    }

    public void onCreateWorkOrder(ActionEvent event) {
        createFormWindow("../work_order.fxml", "Work Order Form");
    }

    public void onOpenWorkOrder(ActionEvent event) {

        WorkOrder selectedWorkOrder = workOrderList.getSelectionModel().getSelectedItems().get(0);

        WorkOrderController controller = (WorkOrderController) openRecordForm("../work_order.fxml", "Work Order Form");
        controller.setRecord(selectedWorkOrder);

    }
}
