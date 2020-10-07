package model;

import java.util.Date;

public class EventRecord extends Record {

    public String eventType;
    public Date startDate;
    public Date endDate;
    public int integer;
    public String budgetReason;
    public int numParticipants;
    public String preferences;

    public EventRecord(String name, Date creationDate, int id, String creator) {
        super(name, creationDate, id, creator);
    }
}
