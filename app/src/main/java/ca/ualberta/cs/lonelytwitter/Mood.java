package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public abstract class Mood {
    protected String emotion;
    protected Date date;

    public Mood(String emotion, Date date) {
        this.emotion = emotion;
        this.date = date;
    }

    public void setDate(Date newDate) {
        this.date = newDate;
    }

    public Date getDate() {
        return date;
    }

    public abstract String toString();

}
