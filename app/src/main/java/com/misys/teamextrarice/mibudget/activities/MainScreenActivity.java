package com.misys.teamextrarice.mibudget.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieData;
import com.misys.teamextrarice.mibudget.fragments.BalanceDetailsFragment;
import com.misys.teamextrarice.mibudget.fragments.HomeFragment;
import com.misys.teamextrarice.mibudget.fragments.NavigationDrawerFragment;
import com.misys.teamextrarice.mibudget.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.misys.teamextrarice.mibudget.fragments.TransactionHistoryFragment;
import com.misys.teamextrarice.mibudget.fragments.UserDetailsFragment;
import com.misys.teamextrarice.mibudget.soaphandler.AccountBalanceEqSoapHandler;

import org.w3c.dom.Text;


public class MainScreenActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
                    UserDetailsFragment.OnFragmentInteractionListener,
                    HomeFragment.OnFragmentInteractionListener,
                    BalanceDetailsFragment.OnFragmentInteractionListener,
                   TransactionHistoryFragment.OnFragmentInteractionListener{
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    public static final String PREFS_NAME = "LoginPrefFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";

    private static AccountBalanceEqSoapHandler handler = new AccountBalanceEqSoapHandler("0000450044003");




    /*
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_placeholder, HomeFragment.newInstance())
                .commit();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {
            case 0: fragmentManager.beginTransaction()
                    .replace(R.id.fragment_placeholder, HomeFragment.newInstance())
                    .commit();
                break;

            case 1:

                    fragmentManager.beginTransaction()
                    .replace(R.id.fragment_placeholder, UserDetailsFragment.newInstance())
                    .commit();
                     break;
            case 2:  fragmentManager.beginTransaction()
                    .replace(R.id.fragment_placeholder, BalanceDetailsFragment.newInstance())
                    .commit();
                break;
            case 3:  fragmentManager.beginTransaction()
                    .replace(R.id.fragment_placeholder, TransactionHistoryFragment.newInstance())
                    .commit();
                break;
            case 4:
                SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(PREF_USERNAME,null);
                editor.putString(PREF_PASSWORD,null);
                editor.commit();

                Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(loginIntent);
                break;
            default:
                break;

        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu_add_daily:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Toast.makeText(MainScreenActivity.this, "Add Daily Transactions is Selected", Toast.LENGTH_SHORT).show();
                Intent dailyInt = new Intent(getApplicationContext(),AddDailyTransactionActivity.class);
                dailyInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(dailyInt);
                return true;

            case R.id.menu_add_income_expense:
                Toast.makeText(MainScreenActivity.this, "Add Income/Expense is Selected", Toast.LENGTH_SHORT).show();
                Intent incexpInt = new Intent(getApplicationContext(),AddIncomeExpenseActivity.class);
                incexpInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(incexpInt);
                return true;

            case R.id.menu_savings:
                Toast.makeText(MainScreenActivity.this, "Add Savings is Selected", Toast.LENGTH_SHORT).show();
                Intent savInt = new Intent(getApplicationContext(),AddSavingsActivity.class);
                savInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(savInt);
                return true;

            case R.id.menu_upd_savings:
                Toast.makeText(MainScreenActivity.this, "Update Savings is Selected", Toast.LENGTH_SHORT).show();
                Intent upsavInt = new Intent(getApplicationContext(),UpdateSavingsActivity.class);
                upsavInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(upsavInt);
                return true;
            case R.id.sync_balance:
                Toast.makeText(MainScreenActivity.this, "Synchronizing Balance", Toast.LENGTH_SHORT).show();
                AsyncCallWS task = new AsyncCallWS();
                //Call execute
                task.execute();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static AccountBalanceEqSoapHandler getHandler() {
        return handler;
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Log.i(TAG, "doInBackground");
            //getFahrenheit(celcius);
            //getAcctDetails(celcius);
            handler = new AccountBalanceEqSoapHandler("0000450044003");
            handler.getAcctDetails();
//            accBasicDetMap = handler.getAcctBasicDetailMap();
//            accBalanceDetMap = handler.getAcctBalanceDetailMap();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            Log.i(TAG, "onPostExecute");
//            tv.setText(fahren + "� F");
            Toast.makeText(MainScreenActivity.this, "Synchronize Balance Successful", Toast.LENGTH_SHORT).show();

//            String acctref = accBasicDetMap.get("acctref");
//            String custName = accBasicDetMap.get("custname");
//            String ledgerbalance = accBalanceDetMap.get("ledgerbalance");
//            String availbalance = accBalanceDetMap.get("availbalance");
//            TextView sample = (TextView) findViewById(R.id.sample_text);
//            sample.setText("acctref: " + acctref + "\n" + "custname: " + custName + "\n" + "ledgerbalance: " + ledgerbalance
//            + "\n" + "avail balance: " + availbalance);

        }

        @Override
        protected void onPreExecute() {
//            Log.i(TAG, "onPreExecute");
//            tv.setText("Calculating...");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            //Log.i(TAG, "onProgressUpdate");
        }

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_screen, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainScreenActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }


    }

}
