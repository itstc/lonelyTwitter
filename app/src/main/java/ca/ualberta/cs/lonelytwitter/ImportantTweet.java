package ca.ualberta.cs.lonelytwitter;

/**
 * ImportantTwet is a tweet with isImportant() method returning true
 * */
public class ImportantTweet extends Tweet {
    @Override
    public Boolean isImportant() {
        return true;
    }

}
