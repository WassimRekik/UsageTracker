package tn.insat.wassimrekik.usagetracker.Fragment.Statestiques_Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tn.insat.wassimrekik.usagetracker.R;


public class StatAppelFragment extends Fragment {
    public StatAppelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appel_stat, container, false);
    }

}
