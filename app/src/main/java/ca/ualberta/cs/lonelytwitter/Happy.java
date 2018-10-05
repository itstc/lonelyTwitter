package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Happy is a mood subclass describing a happy mood
 * */
public class Happy extends Mood {

    public Happy(Date date) {
        super("Happy", date);
    }

    public Happy() {
        super("Happy", new Date());
    }

    public String toString() {
        return "I'm a happy boy";
    }
}
