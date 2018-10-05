package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Tweet is superclass to be subclasses to display on LonelyTwitterActivity
 * */
public abstract class Tweet {

    protected ArrayList<Mood> moods;

    protected String message;
    protected Date date;

    /**
     * setMessage is a function to save an argument message to a Tweet object
     * @param message: target message to save
     * @return void
     * */
    public void setMessage(String message) throws MaxTweetException {
        this.message = message;
    }

    /**
     * setDate is a function to save an argument date to a Tweet object
     * @param date: target date to save
     * @return void
     * */
    public void setDate(Date date) {
        this.date = date;
    }

    // getMessage returns the current message set in a Tweet Object
    public String getMessage() {
        return this.message;
    }
    // getDate returns the current date set in a Tweet Object
    public Date getDate() {
        return this.date;
    }
    // toString returns the message and date to a string string
    public String toString() {
        return this.date.toString() + " | " + this.message;
    }

    // isImportant is a method to be added in subclasses
    public abstract Boolean isImportant();
}
