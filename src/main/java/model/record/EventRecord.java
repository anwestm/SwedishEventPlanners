package model.record;

import model.record.util.EventPreferences;

import java.util.Date;

public class EventRecord extends Record {

    public String eventType;
    public Date startDate;
    public Date endDate;
    public int budget;
    public String budgetReason;
    public int numParticipants;
    public EventPreferences prefs;

    public String description;
    public String decorations;
    public String foodAndDrinks;
    public String filmingAndPhotos;
    public String music;
    public String postersAndArt;
    public String computerIssues;
    public String otherNeeds;

    public EventRecord() {
        super("", new Date(), 0, "json");

    }

    public EventRecord(String name, Date creationDate, int id, String creator) {
        super(name, creationDate, id, creator);
    }
}
