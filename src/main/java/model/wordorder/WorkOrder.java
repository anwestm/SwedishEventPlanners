package model.wordorder;


import model.record.Record;

public abstract class WorkOrder {

    enum Department {
        FINANCIAL, PRODUCTION;
    }

    public String initiator;
    public Department department;
    public String name;

    public Record record;


}
