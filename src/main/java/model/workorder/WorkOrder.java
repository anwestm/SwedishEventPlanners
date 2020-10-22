package model.workorder;


import model.EmployeeType;

public class WorkOrder {

    public enum WorkType {
        EVENT_DETAILS_REQUEST("Request Client Details"),
        FINANCIAL_REQUEST("Financial Request"),
        HIRING_REQUEST("Hiring Request"),
        PRODUCTION_REQUEST("Production Request");


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
