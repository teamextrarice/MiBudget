package com.misys.teamextrarice.mibudget.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by jozaldua on 7/23/2015.
 */
public class SelectMainActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "LoginPrefFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectMain();
    }

    private void selectMain(){
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);

        if (username == null || password == null) {
            getApplicationContext().startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
        else{
            getApplicationContext().startActivity(new Intent(getApplicationContext(),MainScreenActivity.class));
        }
    }

}
