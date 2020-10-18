package model.record.util;

public class EventPreferences {

    public boolean decorations;
    public boolean parties;
    public boolean photos;
    public boolean food;
    public boolean drinks;

    public EventPreferences(boolean decorations, boolean parties, boolean photos, boolean food, boolean drinks) {
        this.decorations = decorations;
        this.parties = parties;
        this.photos = photos;
        this.food = food;
        this.drinks = drinks;
    }

    public EventPreferences() {

    }


}
