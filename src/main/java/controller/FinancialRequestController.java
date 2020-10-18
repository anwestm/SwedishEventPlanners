package controller;

import controller.serializer.RecordLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DepartmentType;
import model.record.EventRecord;
import model.record.FinancialRequestRecord;
import model.record.Record;

import java.util.Date;

public class FinancialRequestController {


    public RadioButton adminRadio;
    public RadioButton servicesRadio;
    public RadioButton prodRadio;
    public RadioButton finRadio;

    public TextField projReferenceField;
    public TextField budgetField;
    public TextArea reason;

    public Button saveButton;

    private FinancialRequestRecord record;

    @FXML
    private void initialize() {
        record = new FinancialRequestRecord();


        final ToggleGroup group = new ToggleGroup();
        adminRadio.setToggleGroup(group);
        servicesRadio.setToggleGroup(group);
        prodRadio.setToggleGroup(group);
        finRadio.setToggleGroup(group);

    }

    public void setRecord(Record newRecord) {
        this.record = (FinancialRequestRecord) newRecord;


        DepartmentType type = record.requestingDepartment;

        adminRadio.setSelected(type == DepartmentType.ADMINISTRATION);
        servicesRadio.setSelected(type == DepartmentType.SERVICES);
        prodRadio.setSelected(type == DepartmentType.PRODUCTION);
        finRadio.setSelected(type == DepartmentType.FINANCIAL);

        projReferenceField.setText(Integer.toString(record.id));
        budgetField.setText(Integer.toString(record.budget));
        reason.setText(record.budgetReason);
    }

    public void saveButtonPressed(ActionEvent actionEvent) {

        DepartmentType type = null;

        if (adminRadio.isSelected())
            type=DepartmentType.ADMINISTRATION;
        else if (servicesRadio.isSelected())
            type=DepartmentType.SERVICES;
        else if (prodRadio.isSelected())
            type=DepartmentType.PRODUCTION;
        else if (finRadio.isSelected())
            type=DepartmentType.FINANCIAL;

        int recordNumber = Integer.parseInt(projReferenceField.getText());
        int budget = Integer.parseInt(budgetField.getText());

        String reasonStr = reason.getText();
        record = new FinancialRequestRecord(Integer.toString(recordNumber), new Date(), recordNumber, "finPanel");
        record.budget = budget;
        record.budgetReason = reasonStr;
        record.requestingDepartment = type;


        RecordLoader loader = new RecordLoader("records");
        loader.saveRecord(record, FinancialRequestRecord.class);

        System.out.println("Record Save");

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }
}
