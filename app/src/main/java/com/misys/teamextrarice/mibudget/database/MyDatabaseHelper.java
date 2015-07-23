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
    private static final String DATABASE_CREATE_USERS = "CREATE TABLE Parties ( _id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL UNIQUE" +
            ",password TEXT ,acctNo INTEGER, cutoffDay INTEGER, birthDate DATE, job TEXT);";
    private static final String DATABASE_CREATE_SAVINGS = "CREATE TABLE Savings ( txn_SAVINGS_id INTEGER PRIMARY KEY AUTOINCREMENT" +
            ",partyId INTEGER, txn_date DATE, amount INTEGER, comments TEXT" +
            ",FOREIGN KEY(partyId) REFERENCES Parties(_id));";
    private static final String DATABASE_CREATE_BUDGET = "CREATE TABLE Budget ( txn_BUDGET_id INTEGER PRIMARY KEY AUTOINCREMENT" +
            ",partyId INTEGER, type TEXT NOT NULL, txn_date DATE, amount INTEGER, comments TEXT" +
            ",FOREIGN KEY(partyId) REFERENCES Parties(_id));";
    private static final String DATABASE_CREATE_INCEXP = "CREATE TABLE IncExp ( txn_INCEXP_id INTEGER PRIMARY KEY AUTOINCREMENT" +
            ",partyId INTEGER, type TEXT NOT NULL, effectiveDay DATE, amount INTEGER, comments TEXT" +
            ",FOREIGN KEY(partyId) REFERENCES Parties(_id));";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_USERS);
        database.execSQL(DATABASE_CREATE_SAVINGS);
        database.execSQL(DATABASE_CREATE_BUDGET);
        database.execSQL(DATABASE_CREATE_INCEXP);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(MyDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS Parties");
        database.execSQL("DROP TABLE IF EXISTS Savings");
        database.execSQL("DROP TABLE IF EXISTS Budget");
        database.execSQL("DROP TABLE IF EXISTS IncExp");
        onCreate(database);
    }
}