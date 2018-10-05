package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * LonelyTwitterActivity is the main page of the app where we send tweets and view them
 * */
public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);
 		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

 		// give functionality to the Save Button
		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String text = bodyText.getText().toString();
				ImportantTweet newTweet = new ImportantTweet();
				try {
					newTweet.setMessage(text);
					newTweet.setDate(new Date());
					tweets.add(newTweet);
					adapter.notifyDataSetChanged();
					saveInFile();

				} catch (MaxTweetException e) {
					e.printStackTrace();
				}
			}
		});

		// give functionality to the Clear Button
		clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				tweets.clear();
				saveInFile();
				adapter.notifyDataSetChanged();

			}
		});
	}

	/**
	 * when onStart is called the activity will load the tweets from internal storage
	 * that was saved in previous sessions
	 * */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("APP_START", "STARTING");
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 * loadFromFile deserializes a GSON object file to Tweets and stores it to the tweets member
	 * attribute
	 * @return void
	 * */
	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader bf = new BufferedReader(isr);

			Gson gson = new Gson();
			Type listTweetType = new TypeToken<ArrayList<ImportantTweet>>(){}.getType();
			tweets = gson.fromJson(bf, listTweetType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>();
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * saveInFile serializes current list of tweets to a GSON object which can later be deserialized
	 * and displayed
	 * */
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter writer = new BufferedWriter(osw);

			Gson gson = new Gson();
			gson.toJson(tweets, writer);
			writer.flush();
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}