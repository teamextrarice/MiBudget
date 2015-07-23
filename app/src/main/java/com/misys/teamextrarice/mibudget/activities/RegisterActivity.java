package com.misys.teamextrarice.mibudget.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.misys.teamextrarice.mibudget.R;
import com.misys.teamextrarice.mibudget.database.MyDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jozaldua on 7/23/2015.
 */
public class RegisterActivity extends ActionBarActivity  {
    private EditText name;
    private EditText password;
    private EditText acctnum;
    private EditText cutOffDate;
    private EditText birthDate;
    private EditText job;

    private DatePickerDialog cutoffDatePickerDialog;
    private DatePickerDialog birthDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setupGUI();
        setDateTimeField();
    }

    private void setupGUI(){
        name = (EditText)findViewById(R.id.name_input);
        password = (EditText)findViewById(R.id.pw_input);
        acctnum = (EditText)findViewById(R.id.acctnum_input);
        cutOffDate = (EditText)findViewById(R.id.cutoffdate_input);
        cutOffDate.setInputType(InputType.TYPE_NULL);
        cutOffDate.requestFocus();
        birthDate = (EditText)findViewById(R.id.birthdate_input);
        birthDate.setInputType(InputType.TYPE_NULL);
        job = (EditText)findViewById(R.id.job_input);
    }

    private void setDateTimeField() {
        cutOffDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cutoffDatePickerDialog.show();
            }
        });
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthDatePickerDialog.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        cutoffDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                cutOffDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        birthDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                birthDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public void submitReg(View view){
        createRecord();
    }
    public void createRecord(){
        MyDB db = MyDB.getInstance();
        db.setdbHelper(this);
        long success = db.createRecord(name.getText().toString(),password.getText().toString(),acctnum.getText().toString(),
                cutOffDate.getText().toString(),birthDate.getText().toString(),job.getText().toString());
        if(success < 0){
            Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(loginIntent);
        }
    }
}
