package com.misys.teamextrarice.mibudget.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    private void login(){
        userInput = (EditText)findViewById(R.id.user_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
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
            }
            if(userInput.getText().equals(username) && passwordInput.getText().equals(password)){
                Intent mainIntent = new Intent(this,MainScreenActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainIntent);
            }
            else{
                errorText.setText("Invalid Password.");

            }
        }
        else{
            errorText.setText("User does not exist. Please Sign up.");
        }

    }
}
