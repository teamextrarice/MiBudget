package com.misys.teamextrarice.mibudget.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.misys.teamextrarice.mibudget.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String PREFS_NAME = "LoginPrefFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_ACCTID = "acctid";
    private static final String PREF_CUTOFF = "cutoffday";
    private static final String PREF_BDAY = "bday";
    private static final String PREF_JOB = "job";

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
     * @return A new instance of fragment UserDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserDetailsFragment newInstance(String param1, String param2) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static UserDetailsFragment newInstance() {
        UserDetailsFragment fragment = new UserDetailsFragment();
        return fragment;
    }

    public UserDetailsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        SharedPreferences pref = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        TextView userdet = (TextView)view.findViewById(R.id.userdet);
        String username = pref.getString(PREF_USERNAME,"");
        String acctid = pref.getString(PREF_ACCTID,"");
        acctid = pad(acctid);
        String cutoffday = pref.getString(PREF_CUTOFF,"");
        String birthdate = pref.getString(PREF_BDAY,"");
        String job = pref.getString(PREF_JOB,"");

        userdet.setText("username: " + username + "\n" + "acct id: " + acctid + "\n" + "cutoff day: " + cutoffday
                + "\n" + "birthdate: " + birthdate + "\n" + "job: " + job);
        return view;
    }

    private String pad(String acctref){
        int len = acctref.length();
        String zeroes = "0000000000000";

        acctref = zeroes.substring(0,zeroes.length()-len) + acctref;
        return acctref;

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
