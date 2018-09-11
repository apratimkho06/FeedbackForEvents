package com.example.feedback.database;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "event")
public class Event {

    @DatabaseField(id = true)
    long event_id;

    @DatabaseField
    String event_name;

    @ForeignCollectionField(eager = true)
    ForeignCollection<Responses> responses;

    public ForeignCollection<Responses> getResponses() {
        return responses;
    }

    public long getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setResponses(ForeignCollection<Responses> responses) {
        this.responses = responses;
    }
}
