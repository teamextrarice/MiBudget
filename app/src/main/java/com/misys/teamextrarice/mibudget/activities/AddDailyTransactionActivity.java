package com.misys.teamextrarice.mibudget.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.misys.teamextrarice.mibudget.R;
import com.misys.teamextrarice.mibudget.database.MyDB;

/**
 * Created by jozaldua on 7/24/2015.
 */
public class AddDailyTransactionActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "LoginPrefFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_daily_transaction);

        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        final String username = pref.getString(PREF_USERNAME, "");

        final MyDB db = MyDB.getInstance();
        db.setdbHelper(this);

        final EditText amountInput = (EditText) findViewById(R.id.amount_input);
        final EditText commentInput = (EditText) findViewById(R.id.comment_input);
        final EditText dateInput = (EditText) findViewById(R.id.date_input);
        final Spinner type = (Spinner) findViewById(R.id.spinner1);
        Button addButton = (Button) findViewById(R.id.add_button);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);


        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(getApplicationContext(),"Record " + Long.toString(db.addBudget(db.selectByName(username).getString(db.selectByName(username).getColumnIndex("_id")),
                        type.getSelectedItem().toString(), dateInput.getText().toString(),
                        amountInput.getText().toString(), commentInput.getText().toString())) + "Add Successful"
                        ,Toast.LENGTH_SHORT).show();
                Intent mainIntent = new Intent(getApplicationContext(),MainScreenActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mainIntent);
            }
        });

    }
}
