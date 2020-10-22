package model.record;

import model.DepartmentType;

import java.util.Date;

public class JobRecord extends Record {

    public boolean fullTimeContract;
    public DepartmentType departmentType;
    public int years;
    public String title;
    public String desc;


    public JobRecord() {
        super("", new Date(), 0, "json");

    }

    public JobRecord(String name, Date creationDate, int id, String creator) {
        super(name, creationDate, id, creator);
    }

}
