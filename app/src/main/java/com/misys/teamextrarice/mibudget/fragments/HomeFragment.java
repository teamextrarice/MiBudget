package com.misys.teamextrarice.mibudget.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.misys.teamextrarice.mibudget.R;
import com.misys.teamextrarice.mibudget.database.MyDB;
import com.misys.teamextrarice.mibudget.util.BudgetUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String PREFS_NAME = "LoginPrefFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_ACCTID = "acctid";
    private static final String PREF_CUTOFF = "cutoffday";
    private static final String PREF_BDAY = "bday";
    private static final String PREF_JOB = "job";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    } public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<Entry> values1 = new ArrayList<Entry>();


        SharedPreferences pref = this.getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        final String username = pref.getString(PREF_USERNAME, "");
        final String cutoff = pref.getString(PREF_CUTOFF, "");

        MyDB db = MyDB.getInstance();
        db.setdbHelper(this.getActivity().getBaseContext());

        Cursor user = db.selectByName(username);
        BudgetUtil budgetUtil = new BudgetUtil(this.getActivity().getBaseContext());

        //Entry c1e1 = new Entry(40.000f, 0); // 0 == Budget
        budgetUtil.sumExpenses();
        Entry c1e1 = new Entry( Integer.parseInt(budgetUtil.sumMonthIncome(cutoff, user.getString(0)).toString()) -
                Integer.parseInt(budgetUtil.sumMonthExpense(cutoff, user.getString(0)).toString()) -
                Integer.parseInt(budgetUtil.sumMonthDailyExp(cutoff, user.getString(0)).toString())
                , 0); // 0 == Budget
        values1.add(c1e1);
        //Entry c1e2 = new Entry(50.000f, 1); // 1 == Expenses
        Entry c1e2 = new Entry(Integer.parseInt(budgetUtil.sumMonthExpense(cutoff, user.getString(0)).toString()), 1); // 1 == Expenses
        values1.add(c1e2);
        //Entry c1e3 = new Entry(20.000f, 2); // 2 == Other
        Cursor cursor = db.selectByName(username);
        //cursor.moveToFirst();
        Entry c1e3 = new Entry(Integer.parseInt(budgetUtil.sumMonthDailyExp(cutoff, user.getString(0)).toString()), 1); // 2 == Other
        values1.add(c1e3);
        // and so on ...

        PieDataSet setComp1 = new PieDataSet(values1, "Money Floyd");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<PieDataSet> dataSets = new ArrayList<PieDataSet>();
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Budget");
        xVals.add("Expenses");
        xVals.add("Other");


        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        setComp1.setColors(colors);

        PieData data = new PieData(xVals, setComp1);


        PieChart chart = (PieChart) view.findViewById(R.id.chart);
        chart.setDescription("Chart");



        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Date todayWithZeroTime = null;
        Calendar todayCalendar = Calendar.getInstance();
        Calendar initialCutoffCal = Calendar.getInstance();
        Calendar cutoffCalStart = Calendar.getInstance();
        Calendar cutoffCalEnd = Calendar.getInstance();

        try {
            todayWithZeroTime = formatter.parse(formatter.format(today));
        } catch (Exception e) {
            Log.w("", e.toString());
        }

        todayCalendar.setTime(todayWithZeroTime);

        Calendar newDate = Calendar.getInstance();
        newDate.set(todayCalendar.get(Calendar.YEAR), todayCalendar.get(Calendar.MONTH), Integer.parseInt(cutoff));

        initialCutoffCal.setTime(todayWithZeroTime);
        Calendar newDate2 = Calendar.getInstance();
        if (newDate.compareTo(initialCutoffCal) < 0) {
            cutoffCalStart = newDate;
            Log.d("Before 1 ", newDate.getTime().toString());
            newDate2.set(todayCalendar.get(Calendar.YEAR), todayCalendar.get(Calendar.MONTH), Integer.parseInt(cutoff));
            Log.d("Before 2 ", newDate2.getTime().toString());
            newDate2.add(Calendar.MONTH, 1);
            Log.d("After " , newDate2.getTime().toString());
            cutoffCalEnd = newDate2;
        } else if (newDate.compareTo(initialCutoffCal) >= 0) {
            newDate.add(Calendar.DAY_OF_MONTH, -1);
            cutoffCalEnd = newDate;
            newDate2.set(todayCalendar.get(Calendar.YEAR), todayCalendar.get(Calendar.MONTH), Integer.parseInt(cutoff));
            newDate2.add(Calendar.MONTH, -1);
            cutoffCalStart = newDate2;
        }

        long difMillis = cutoffCalEnd.getTimeInMillis() - todayCalendar.getTimeInMillis();
        long diffDays = difMillis / (24 * 60 * 60 * 1000);

        long dayBalance = (Integer.parseInt(budgetUtil.sumMonthIncome(cutoff, user.getString(0)).toString()) -
                Integer.parseInt(budgetUtil.sumMonthExpense(cutoff, user.getString(0)).toString()) -
                Integer.parseInt(budgetUtil.sumMonthDailyExp(cutoff, user.getString(0)).toString())) / diffDays;

        chart.setData(data);
        chart.setCenterText("Today's Budget: \n" + dayBalance + "\nDays Left\n" + diffDays + "\n" + cutoff); //change to budget
        chart.invalidate();

        TextView savings = (TextView) view.findViewById(R.id.card_savings);
        savings.setText(budgetUtil.sumUserSavings(user.getString(0)).toString());

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
