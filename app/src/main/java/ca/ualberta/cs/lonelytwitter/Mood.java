package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Mood is a component used in tweets to describe it
 * */
public abstract class Mood {
    protected String emotion;
    protected Date date;

    /**
     * Mood is a superclass to be subclassed
     * @param emotion: target type of Mood, date: a date of when this Mood occurred
     * */
    public Mood(String emotion, Date date) {
        this.emotion = emotion;
        this.date = date;
    }

    /**
     * setDate is a method to set the date member attribute to the argument given
     * @param newDate: the date to set
     * @return void
     * */
    public void setDate(Date newDate) {
        this.date = newDate;
    }

    // getDate returns the stored date member attribute
    public Date getDate() {
        return date;
    }

    // toString is a string to return from the subclass
    public abstract String toString();

}
