package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;

/**
 * Created by romansky on 10/20/16.
 */
public class ElasticsearchTweetController {
    static JestDroidClient client = null;

    public static void setClient() {
        if(client == null) {
            // declare configs
            DroidClientConfig config = new DroidClientConfig
                    .Builder("http://cmput301.softwareprocess.es:8080/")
                    .build();

            // create our factory
            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);

            // set our client variable to factory object
            client = (JestDroidClient) factory.getObject();
        }
    }

    public static class AddTweetTask extends AsyncTask<Tweet, Void, Void>{
        @Override
        protected Void doInBackground(Tweet... params) {
            setClient();
            Tweet tweet = params[0];
            Index index = new Index.Builder(tweet)
                    .index("shaiful-thursday")
                    .type("tweet")
                    .build();
            try {


                DocumentResult result = client.execute(index);
                if(result.isSucceeded()) {
                    tweet.setTweetID(result.getId());
                }
            }catch(IOException e) {}
            return null;
        }
    }

    public static class GetTweetsTask extends AsyncTask<String, Void, ArrayList<Tweet>> {

        protected ArrayList<Tweet> doInBackground(String... params) {
            setClient();
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            Search search = new Search.Builder(params[0])
                    .addIndex("shaiful-new-test")
                    .addType("tweet")
                    .build();
            try {
                JestResult result = client.execute(search);
                if(result.isSucceeded()) {
                    List<NormalTweet> tweetList;
                    tweetList = result.getSourceAsObjectList(NormalTweet.class);
                    tweetList = tweetList.subList(0, 10);
                    tweets.addAll(tweetList);
                }
            } catch(IOException e) {}
            return tweets;
        }
    }
}