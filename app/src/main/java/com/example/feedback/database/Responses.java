package com.example.feedback.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "quesele")
public class Responses {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    public String ques;

    @DatabaseField
    public String catg;

    @DatabaseField
    public String ans;

    @DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true)
    public Event event;

    public Responses(){}

    public Responses(String ques, String catg){
        this.ques = ques;
        this.catg = catg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getAns() {
        return ans;
    }

    public String getCatg() {
        return catg;
    }

    public String getQues() {
        return ques;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }
}
