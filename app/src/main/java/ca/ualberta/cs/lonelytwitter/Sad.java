package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Sad is a Mood subclass describing sadness
 * */
public class Sad extends Mood {
    public Sad() {
        super("Sad", new Date());
    }

    public Sad(Date date) {
        super("Sad", date);
    }

    public String toString() {
        return "wahhh wahhh im sad n crying";
    }
}
