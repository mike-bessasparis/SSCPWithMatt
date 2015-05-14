package com.bessasparis.michael.sscpwithmatt;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {
    JSONObject mJObj;
    QuestionFragment mFrag = new QuestionFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mJObj = loadJSONFromAsset();
            updateUI(getQuestionObjToDisplay(mJObj));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateUI(JSONObject mQObj) throws JSONException {
        Log.i("mjb", "in updateUI");

            getFragmentManager().beginTransaction().
                    add(R.id.question_fragment_container, mFrag).commit();

        mFrag.displayQuestion(mQObj);



    }


    // takes the JSON object read from file
    // returns the next question object to display
    private JSONObject getQuestionObjToDisplay(JSONObject mObj) throws JSONException {
        int i = 0; //hardcoded for testing

        Log.i("mjb", "in getQuestionToDIsplay");

        JSONArray questionsArray = mObj.getJSONArray("questions");
        JSONObject questionObj = questionsArray.getJSONObject(i);

        return questionObj;
    }

    //loads the json object from file
    public JSONObject loadJSONFromAsset() {
        String json;
        JSONObject jsonObj = null;

        try {
            InputStream is = getAssets().open("ports.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            jsonObj = new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObj;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
