package com.misys.teamextrarice.mibudget.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by edgresma on 7/21/2015.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ExtraRice2";


    private static final int DATABASE_VERSION = 2;
    // Database creation sql statement
    private static final String DATABASE_CREATE_USERS = "CREATE TABLE Parties ( _id INTEGER PRIMARY KEY AUTO_INCREMENT,name TEXT NOT NULL" +
            ",password TEXT ,acctNo INTEGER, cutoffDay INTEGER, birthDate DATE, job TEXT);";
    private static final String DATABASE_CREATE_SAVINGS = "CREATE TABLE Savings ( txn_SAVINGS_id INTEGER PRIMARY KEY AUTO_INCREMENT" +
            ",partyId FOREIGN KEY REFERENCES Parties(_id), txn_date DATE, amount INTEGER, comments TEXT);";
    private static final String DATABASE_CREATE_BUDGET = "CREATE TABLE Budget ( txn_BUDGET_id INTEGER PRIMARY KEY AUTO_INCREMENT" +
            ",partyId FOREIGN KEY REFERENCES Parties(_id), type TEXT NOT NULL, txn_date DATE, amount INTEGER, comments TEXT);";
    private static final String DATABASE_CREATE_INCEXP = "CREATE TABLE IncExp ( txn_INCEXP_id INTEGER PRIMARY KEY AUTO_INCREMENT" +
            ",partyId FOREIGN KEY REFERENCES Parties(_id), type TEXT NOT NULL, effectiveDay DATE, amount INTEGER, comments TEXT);";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_USERS);
        //database.execSQL(DATABASE_CREATE_SAVINGS);
        //database.execSQL(DATABASE_CREATE_BUDGET);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(MyDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS Parties");
        //database.execSQL("DROP TABLE IF EXISTS Savings");
        //database.execSQL("DROP TABLE IF EXISTS Budget");
        onCreate(database);
    }
}