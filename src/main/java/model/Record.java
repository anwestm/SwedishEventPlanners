package model;

import java.util.Date;

public abstract class Record implements Comparable<Record> {

    public String name;
    public Date creationDate;
    public String creator;
    public int id;

    public Record(String name, Date creationDate, int id, String creator) {
        this.name = name;
        this.creationDate = creationDate;
        this.id = id;
        this.creator = creator;
    }

    @Override
    public int compareTo(Record o) {
        return 0;
    }

}
