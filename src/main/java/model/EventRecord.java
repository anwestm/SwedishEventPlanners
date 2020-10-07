package model;

import java.util.Date;

public class EventRecord extends Record {

    public String eventType;
    public Date startDate;
    public Date endDate;
    public int budget;
    public String budgetReason;
    public int numParticipants;
    public EventPreferences prefs;

    public EventRecord() {
        super("", new Date(), 0, "json");

    }

    public EventRecord(String name, Date creationDate, int id, String creator) {
        super(name, creationDate, id, creator);
    }
}
