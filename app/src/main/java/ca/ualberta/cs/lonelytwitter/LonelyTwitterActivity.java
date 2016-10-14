/*
Copyright (C) 2016 Team 14, CMPUT301, University of Alberta - All Rights Reserved.
You may use, copy or distribute this code under terms and conditions of University of Alberta
and Code of Student Behavior.
Please contact ting8@ualberta,ca for more details and questions.
 */
package ca.ualberta.cs.lonelytwitter;

		import java.io.BufferedReader;
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
		import android.content.Intent;
		import android.os.Bundle;
		import android.view.View;
		import android.widget.AdapterView;
		import android.widget.ArrayAdapter;
		import android.widget.Button;
		import android.widget.EditText;
		import android.widget.ListView;

		import com.google.gson.Gson;
		import com.google.gson.reflect.TypeToken;

/**
 * This class is the main view class in lonelyTwitter class
 * It deals with user inputs. saves/loads them in/from the file FILE_NAME (file.sav).
 * <p> You can access this file from Android Device Monitor</p>
 * <pre> pre-formatted      text</pre>
 * <code>
 *     pseudo-code that is used in this class as follows: <br>
 *         step 1 <br>
 *         step 2 <br>
 *</code>
 * <pl>
 *     <ting8> first item</ting8>
 *     <ting8> second item</ting8>
 * </pl>
 * @author ting8
 * @since 1.4
 * //@deprecated
 * @see NormalTweet
 * @see java.io.BufferedReader
 * @see TweetList
 */
public class LonelyTwitterActivity extends Activity {

	/**
	 * This is the name of the file that is saved in your virtual device.
	 * You canaccess it through Android Device Monitor by selecting you app,
	 * then data -> data -> file.sav
	 * @see NormalTweet
	 * @author ting8
	 */
	private Activity activity = this;

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	//This is
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;

	/*
	Testing multi-line documentations
	Testing.
	 */
	/** Called when the activity is first created. */

	public ListView getOldTweetsList(){
		return oldTweetsList;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				Tweet newTweet = new NormalTweet(text);
				tweetList.add(newTweet);
				adapter.notifyDataSetChanged();
				saveInFile();
			}
		});

		//bodyText = (EditText) findViewById(R.id.body);
		Button clearButton = (Button) findViewById(R.id.clear);
		//oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		clearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				//setResult(RESULT_OK);
				//String text = bodyText.getText().toString();
				//Tweet newTweet = new NormalTweet(text);
				tweetList.clear();
				deleteFile(FILENAME);
				adapter.notifyDataSetChanged();
				saveInFile();
				bodyText.setText("");
			}
		});

		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				String tweetMessage = tweetList.get(position).getMessage();
				intent.putExtra("TweetMessage",tweetMessage);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}


	/**
	 * This method loads the json file and generates the tweets from its contents.
	 * @throws  RuntimeException
	 * @exception FileNotFoundException
	 * Note:ctrl+/  or   ctrl+shift+/ for comments
	 */
	private void loadFromFile() {
		ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			//Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept.22,2016
			Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();
			tweetList = gson.fromJson(in, listType);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	private void saveInFile() {
		try {

			FileOutputStream fos = openFileOutput(FILENAME,0);
			OutputStreamWriter writer = new OutputStreamWriter(fos);
			Gson gson = new Gson();
			gson.toJson(tweetList, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}