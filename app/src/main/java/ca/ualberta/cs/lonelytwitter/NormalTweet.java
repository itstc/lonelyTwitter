package ca.ualberta.cs.lonelytwitter;

/**
 * NormalTweet is a Tweet subclass where isImportant() method returns false
 * */
public class NormalTweet extends Tweet {
    @Override
    public Boolean isImportant() {
        return false;
    }
}
