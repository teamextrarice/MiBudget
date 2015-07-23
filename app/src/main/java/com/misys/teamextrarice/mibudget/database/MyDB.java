package com.misys.teamextrarice.mibudget.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by edgresma on 7/21/2015.
 */
public class MyDB{

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;

    /**
     *
     * @param context
     */
    public MyDB(Context context){
        dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    public final static String PTY_TABLE="Parties"; // name of table

    public final static String PTY_ID="_id"; // id value for user
    public final static String PTY_NAME="name";  // name of user
    public final static String PTY_PWD="password";  // pass of user
    public final static String PTY_ACCTID="acctNo"; // account number of user
    public final static String PTY_CutoffDate="cutoffDay";  // cutoff date of user
    public final static String PTY_BirthDate="birthDate";  // birthdate of user
    public final static String PTY_Occupation="job";  // birthdate of user



    public long createRecord(String id, String name,String password, String acctid, String cutoffDay, String birthDate, String job){
        ContentValues values = new ContentValues();
        values.put(PTY_ID, id);
        values.put(PTY_NAME, name);
        values.put(PTY_PWD, password);
        values.put(PTY_ACCTID, acctid);
        values.put(PTY_CutoffDate, cutoffDay);
        values.put(PTY_BirthDate, birthDate);
        values.put(PTY_Occupation, job);
        return database.insert(PTY_TABLE, null, values);
    }

    public long updateRecord(String id, String name,String password, String acctid, String cutoffDay, String birthDate, String job){
        ContentValues values = new ContentValues();
        values.put(PTY_ID, id);
        values.put(PTY_NAME, name);
        values.put(PTY_PWD, password);
        values.put(PTY_ACCTID, acctid);
        values.put(PTY_CutoffDate, cutoffDay);
        values.put(PTY_BirthDate, birthDate);
        values.put(PTY_Occupation, job);
        return database.update(PTY_TABLE, values, PTY_ID + " = " + id, null);
    }

    public long deleteRecord(String id){
        return database.delete(PTY_TABLE, PTY_ID + " = " + id, null);
    }
    public Cursor selectRecords() {
        String[] cols = new String[] {PTY_ID, PTY_NAME, PTY_PWD, PTY_ACCTID, PTY_CutoffDate,
                PTY_BirthDate, PTY_Occupation};
        Cursor mCursor = database.query(true, PTY_TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public void delteDB(Context context){
        context.deleteDatabase("ExtraRice");
    }

    public final static String SVG_TABLE="Savings"; // name of table

    public final static String SVG_ID="txn_SAVINGS_id"; // id value for user
    public final static String SVG_PTY_ID="partyId";  // name of user
    public final static String SVG_DATE="txn_date";  // pass of user
    public final static String SVG_AMOUNT="amount"; // account number of user
    public final static String SVG_COMMENTS="comments";  // cutoff date of user

    public long addSavings(String id, String partyId,String date, String amount, String comments){
        ContentValues values = new ContentValues();
        values.put(SVG_ID, id);
        values.put(SVG_PTY_ID, partyId);
        values.put(SVG_DATE, date);
        values.put(SVG_AMOUNT, amount);
        values.put(SVG_COMMENTS, comments);
        return database.insert(SVG_TABLE, null, values);
    }

    public long updateSavings(String id, String partyId,String date, String amount, String comments){
        ContentValues values = new ContentValues();
        values.put(SVG_ID, id);
        values.put(SVG_PTY_ID, partyId);
        values.put(SVG_DATE, date);
        values.put(SVG_AMOUNT, amount);
        values.put(SVG_COMMENTS, comments);
        return database.update(SVG_TABLE, values, SVG_ID + " = " + id, null);
    }

    public long deleteSavings(String id){
        return database.delete(SVG_TABLE, SVG_ID + " = " + id, null);
    }
    public Cursor selectAllSavings() {
        String[] cols = new String[] {SVG_ID, SVG_PTY_ID, SVG_DATE, SVG_AMOUNT, SVG_COMMENTS};
        Cursor mCursor = database.query(true, SVG_TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public final static String BGT_TABLE="Budget"; // name of table

    public final static String BGT_ID="txn_BALANCES_id"; // id value for user
    public final static String BGT_PTY_ID="partyId";  // name of user
    public final static String BGT_TYPE="type";  // pass of user
    public final static String BGT_DATE="txn_date";  // pass of user
    public final static String BGT_AMOUNT="amount"; // account number of user
    public final static String BGT_COMMENTS="comments";  // cutoff date of user

    public long addBudget(String id, String partyId,String type,String date, String amount, String comments){
        ContentValues values = new ContentValues();
        values.put(BGT_ID, id);
        values.put(BGT_PTY_ID, partyId);
        values.put(BGT_TYPE, type);
        values.put(BGT_DATE, date);
        values.put(BGT_AMOUNT, amount);
        values.put(BGT_COMMENTS, comments);
        return database.insert(BGT_TABLE, null, values);
    }

    public long updateSavings(String id, String partyId,String type,String date, String amount, String comments){
        ContentValues values = new ContentValues();
        values.put(BGT_ID, id);
        values.put(BGT_PTY_ID, partyId);
        values.put(BGT_TYPE, type);
        values.put(BGT_DATE, date);
        values.put(BGT_AMOUNT, amount);
        values.put(BGT_COMMENTS, comments);
        return database.update(BGT_TABLE, values, BGT_ID + " = " + id, null);
    }

    public long deleteBudgetEntry(String id){
        return database.delete(BGT_TABLE, SVG_ID + " = " + id, null);
    }
    public Cursor selectAllBudgetEntries() {
        String[] cols = new String[] {BGT_ID, BGT_PTY_ID, BGT_TYPE, BGT_DATE, BGT_AMOUNT, BGT_COMMENTS};
        Cursor mCursor = database.query(true, BGT_TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }
}
