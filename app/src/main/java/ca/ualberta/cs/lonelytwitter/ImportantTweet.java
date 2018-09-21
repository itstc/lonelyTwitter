package ca.ualberta.cs.lonelytwitter;

public class ImportantTweet extends Tweet {
    @Override
    public Boolean isImportant() {
        return true;
    }

}
