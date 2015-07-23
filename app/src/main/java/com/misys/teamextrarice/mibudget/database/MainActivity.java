package com.misys.teamextrarice.mibudget.database;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "check1", Toast.LENGTH_LONG).show();

        MyDB mydb = new MyDB(this);
        /*
        Toast.makeText(getApplicationContext(),
                Long.toString(mydb.createRecords("001", "Edgar Resma", "", "", "14","" , "Magician")),
                 Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),
                Long.toString(mydb.createRecords("002", "Bong Zaldua", "", "", "14", "", "Crusader")),
                Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),
                Long.toString(mydb.createRecords("003", "Amiel Zaldua", "", "", "30", "", "Druid")),
                Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),
                Long.toString( mydb.createRecords("004", "Dan Rasing","" , "", "30", "", "Bard")),
                Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),
                Long.toString( mydb.updateRecord("004", "Dan Rasing","" , "", "30", getDateTime(), "Knight")),
                Toast.LENGTH_LONG).show();
        */
        Toast.makeText(getApplicationContext(),
                Long.toString( mydb.deleteRecord("004")),
                Toast.LENGTH_LONG).show();

        Cursor values = mydb.selectRecords();
        values.moveToFirst();
        while (!values.isAfterLast()) {
            Toast.makeText(getApplicationContext(), values.getString(0) + values.getString(1)
                    + values.getString(3)+ values.getString(4)+ values.getString(5) + values.getString(6), Toast.LENGTH_LONG).show();
            values.moveToNext();
        }
        values.close();
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
    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
