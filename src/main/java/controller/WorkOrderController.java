package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ClientUser;
import model.EmployeeType;
import model.workorder.WorkOrder;
import model.workorder.WorkOrderRepository;

public class WorkOrderController {

    public Button saveWorkButton;
    public ChoiceBox<WorkOrder.WorkType> workerOrderTypeBox;
    public ChoiceBox<EmployeeType> initiatorBox;
    public ChoiceBox<EmployeeType> assigneeBox;
    public TextField recordIdField;
    public CheckBox completeBox;
    public TextArea descriptionArea;
    private WorkOrder workOrder;


    @FXML
    private void initialize() {
        workOrder = new WorkOrder();

        workerOrderTypeBox.getItems().addAll(WorkOrder.WorkType.values());
        initiatorBox.getItems().addAll(EmployeeType.values());
        assigneeBox.getItems().addAll(EmployeeType.values());
        initiatorBox.setValue(ClientUser.getInstance().getEmployeeType());
    }

    public void setRecord(WorkOrder workOrder) {
        this.workOrder = workOrder;

        workerOrderTypeBox.setValue(workOrder.type);
        initiatorBox.setValue(workOrder.initiator);
        assigneeBox.setValue(workOrder.worker);

        recordIdField.setText(Integer.toString(workOrder.recordId));
        completeBox.setSelected(workOrder.complete);
        descriptionArea.setText(workOrder.description);

    }

    public void saveWorkButtonPressed(ActionEvent event) {
        workOrder.type = workerOrderTypeBox.getValue();
        workOrder.initiator = initiatorBox.getValue();
        workOrder.worker = assigneeBox.getValue();
        workOrder.recordId = Integer.parseInt(recordIdField.getText());
        workOrder.complete = completeBox.isSelected();
        workOrder.description = descriptionArea.getText();

        WorkOrderRepository.getInstance().addWorkOrder(workOrder);

        Stage stage = (Stage) saveWorkButton.getScene().getWindow();
        stage.close();
    }


}
