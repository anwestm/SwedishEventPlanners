package controller;

import controller.serializer.RecordLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DepartmentType;
import model.record.JobRecord;
import model.record.util.EventPreferences;
import model.record.EventRecord;
import model.record.Record;

import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class RecruitmentRequestController {


    public RadioButton fullTimeRadio;
    public RadioButton partTimeRadio;

    public RadioButton adminRadio;
    public RadioButton servicesRadio;
    public RadioButton prodRadio;
    public RadioButton financialRadio;

    public TextField yearsField;
    public TextField titleField;
    public TextArea descField;

    public Button sendButton;

    private JobRecord record;

    @FXML
    private void initialize() {
        record = new JobRecord();

        final ToggleGroup contractGroup = new ToggleGroup();
        fullTimeRadio.setToggleGroup(contractGroup);
        partTimeRadio.setToggleGroup(contractGroup);

        final ToggleGroup departmentGroup = new ToggleGroup();
        adminRadio.setToggleGroup(departmentGroup);
        servicesRadio.setToggleGroup(departmentGroup);
        prodRadio.setToggleGroup(departmentGroup);
        financialRadio.setToggleGroup(departmentGroup);

    }

    public void setRecord(Record newRecord) {
        this.record = (JobRecord) newRecord;

        if (record.fullTimeContract)
            fullTimeRadio.setSelected(true);
        else
            partTimeRadio.setSelected(true);

        switch (record.departmentType) {
            case ADMINISTRATION -> adminRadio.setSelected(true);
            case FINANCIAL -> financialRadio.setSelected(true);
            case PRODUCTION -> prodRadio.setSelected(true);
            case SERVICES -> servicesRadio.setSelected(true);
        }
        yearsField.setText(Integer.toString(record.years));
        titleField.setText(record.title);
        descField.setText(record.desc);
    }


    public void onRecruitmentSend(ActionEvent event) {

        record = new JobRecord();
        record.id = new Random().nextInt(100000);

        record.fullTimeContract = fullTimeRadio.isSelected();

        if(adminRadio.isSelected())
            record.departmentType = DepartmentType.ADMINISTRATION;
        else if (financialRadio.isSelected())
            record.departmentType = DepartmentType.FINANCIAL;
        else if (prodRadio.isSelected())
            record.departmentType = DepartmentType.PRODUCTION;
        else if (servicesRadio.isSelected())
            record.departmentType = DepartmentType.SERVICES;

        record.years = Integer.parseInt(yearsField.getText());
        record.title = titleField.getText();
        record.desc = descField.getText();

        RecordLoader loader = new RecordLoader("records");
        loader.saveRecord(record, JobRecord.class);

        System.out.println("Record Save");

        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }
}
