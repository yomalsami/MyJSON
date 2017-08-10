package com.example.android.myjson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView question_view;
    TextView answertf_view;
    Button Btngetdata;


    //URL to get JSON Array
    private static String url = "https://symbolistic-gyros.000webhostapp.com/get_test.php";

    //JSON Node Names
    private static final String QUESTION_SIN = "questionSin";
    private static final String ANSWER = "answer";
    private static final String ARRAY = "result";


    JSONArray user = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        question_view = (TextView)findViewById(R.id.question);
        answertf_view = (TextView)findViewById(R.id.answer);
        new JSONParse().execute();
        Btngetdata = (Button)findViewById(R.id.true_button);
        Btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONParse().execute();
            }
        });







    }



    private class JSONParse extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {

            try {
                // Getting JSON Array
                user = json.getJSONArray(ARRAY);
                JSONObject c = user.getJSONObject(0);

                // Storing  JSON item in a Variable
                String questionSin = c.getString(QUESTION_SIN);



                String answertf1 = c.getString(ANSWER);

                question_view = (TextView)findViewById(R.id.question);
                answertf_view = (TextView)findViewById(R.id.answer);



                //Set JSON Data in TextView
                question_view.setText(questionSin);
                question_view.setText(Html.fromHtml(questionSin));
                answertf_view.setText(answertf1);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }



}
