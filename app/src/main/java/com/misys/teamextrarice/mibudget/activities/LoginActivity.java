package com.misys.teamextrarice.mibudget.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.misys.teamextrarice.mibudget.R;
import com.misys.teamextrarice.mibudget.database.MyDB;

import org.apache.http.cookie.CookieIdentityComparator;

/**
 * Created by jozaldua on 7/23/2015.
 */
public class LoginActivity extends ActionBarActivity {
    private EditText userInput;
    private EditText passwordInput;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        setupGUI();
//        Button b = (Button) findViewById(R.id.login_button);
//        //Button Click Listener
//        b.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//               login();
//            }
//        }
//        );
        }
    private void setupGUI(){
        userInput = (EditText)findViewById(R.id.user_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
        errorText = (TextView) findViewById(R.id.error_text);
    }

    //public void

    public void login(View view){

        String username ="";
        String password ="";

        MyDB db = MyDB.getInstance();
        db.setdbHelper(this);
        //validate user
        Cursor cur = db.selectByName(userInput.getText().toString());

        if(cur != null) {
            while (!cur.isAfterLast()) {
                username = cur.getString(cur.getColumnIndex(MyDB.PTY_NAME));
                password = cur.getString(cur.getColumnIndex(MyDB.PTY_PWD));
                cur.moveToNext();
            }
            if(userInput.getText().toString().equals(username) && passwordInput.getText().toString().equals(password)){
                Intent mainIntent = new Intent(this,MainScreenActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainIntent);
                Toast.makeText(getApplicationContext(), "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();
            }
            else{
                errorText.setText("Invalid Password.");

            }
        }
        else{
            errorText.setText("User does not exist. Please Sign up.");
        }

    }



    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            login(findViewById(R.id.login_button));
            //Log.i(TAG, "doInBackground");

            //getFahrenheit(celcius);
            //getAcctDetails(celcius);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Log.i(TAG, "onPostExecute");
            //tv.setText(fahren + "° F");

        }

        @Override
        protected void onPreExecute() {
            //Log.i(TAG, "onPreExecute");
            //tv.setText("Calculating...");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            //Log.i(TAG, "onProgressUpdate");
        }

    }
}
