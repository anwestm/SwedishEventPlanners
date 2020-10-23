package model.workorder;


import model.EmployeeType;

public class WorkOrder {

    public enum WorkType {
        REVIEW_EVENT_REQUEST("Review Event Request"),
        REVIEW_EVENT_FINANCES("Review Event Finances"),
        APPROVE_EVENT("Approve Event"),
        REQUEST_CLIENT_DETAILS("Get Event Details"),

        REQUEST_CHEF_WORK("Event Chef Work"),
        REQUEST_PHOTO_WORK("Event photography Work"),

        HIRING_REQUEST("Hiring Request"),

        FINANCIAL_REQUEST("Financial Request");


        private final String name;
        WorkType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public EmployeeType initiator;
    public EmployeeType worker;
    public WorkType type;
    public boolean complete;
    public String description;
    public int recordId;

    public WorkOrder() {

    }

    public WorkOrder(WorkType type, EmployeeType initiator, EmployeeType worker, int recordId) {
        this.type = type;
        this.initiator = initiator;
        this.worker = worker;
        this.recordId = recordId;
        this.complete = false;
    }


}
