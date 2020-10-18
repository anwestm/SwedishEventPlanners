package model.record;

import model.DepartmentType;

import java.util.Date;

public class FinancialRequestRecord extends Record {

    public DepartmentType requestingDepartment;
    public int budget;
    public String budgetReason;

    public FinancialRequestRecord() {
        super("", new Date(), 0, "json");

    }

    public FinancialRequestRecord(String name, Date creationDate, int id, String creator) {
        super(name, creationDate, id, creator);
    }
}
