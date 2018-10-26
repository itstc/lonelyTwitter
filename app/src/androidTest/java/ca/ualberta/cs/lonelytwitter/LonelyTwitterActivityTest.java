package ca.ualberta.cs.lonelytwitter;

import com.robotium.solo.Solo;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {
    Solo solo;
    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testEditTweet() {
        solo.enterText((EditText)solo.getView(R.id.body), "EditTweet");
        solo.clickOnText("Save");
        solo.clearEditText((EditText)solo.getView(R.id.body));
        solo.waitForText("EditTweet");

        solo.clickOnText("EditTweet");
        solo.waitForActivity(EditTweetActivity.class);
        solo.waitForText("EditTweet");
    }
}