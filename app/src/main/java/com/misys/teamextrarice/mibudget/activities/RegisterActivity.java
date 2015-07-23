package com.misys.teamextrarice.mibudget.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

import com.misys.teamextrarice.mibudget.R;
import com.misys.teamextrarice.mibudget.database.MyDB;

import java.util.Date;

/**
 * Created by jozaldua on 7/23/2015.
 */
public class RegisterActivity extends ActionBarActivity {
    private EditText name;
    private EditText password;
    private EditText acctnum;
    private EditText cutOffDate;
    private EditText birthDate;
    private EditText job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        setupGUI();
    }

    private void setupGUI(){
        name = (EditText)findViewById(R.id.name_input);
        password = (EditText)findViewById(R.id.pw_input);
        acctnum = (EditText)findViewById(R.id.acctnum_input);
        cutOffDate = (EditText)findViewById(R.id.cutoffdate_input);
        birthDate = (EditText)findViewById(R.id.birthdate_input);
        job = (EditText)findViewById(R.id.job_input);
    }

    public void createRecord(){
        MyDB db = MyDB.getInstance();
        db.setdbHelper(this);
        //db.createRecord(name.getText().toString(),password.getText().toString(),acctnum.getText(),cutOffDate.get);
    }
}
