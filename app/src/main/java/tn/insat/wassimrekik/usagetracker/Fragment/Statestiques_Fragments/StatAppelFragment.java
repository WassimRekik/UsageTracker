package tn.insat.wassimrekik.usagetracker.Fragment.Statestiques_Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import tn.insat.wassimrekik.usagetracker.DataModel.Shared_Key;
import tn.insat.wassimrekik.usagetracker.R;


public class StatAppelFragment extends Fragment {
    SharedPreferences sharedPref;
    RoundCornerProgressBar progress;
    TextView tv_Call_Usage, tv_first, tv_second, tv_third;

    public StatAppelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_appel_stat, container, false);
        tv_Call_Usage = (TextView) rootView.findViewById(R.id.tv_Call_Usage);
        tv_first = (TextView) rootView.findViewById(R.id.textView_first_call);
        tv_second = (TextView) rootView.findViewById(R.id.textView_second_call);
        tv_third = (TextView) rootView.findViewById(R.id.textView_third_call);

        sharedPref = getActivity().getSharedPreferences(Shared_Key.shared_pref_key, Context.MODE_PRIVATE);
        progress = (RoundCornerProgressBar) rootView.findViewById(R.id.Progress_Call);

        if (container == null) {
            return null;

        }
        return rootView;
    }
    @Override
    public void onResume(){
        super.onResume();

        tv_Call_Usage.setText(sharedPref.getInt(Shared_Key.utilisation_duree_call,1) /60  + " Min");
        tv_first.setText(sharedPref.getString(Shared_Key.first_call,"Empty Log"));
        tv_second.setText(sharedPref.getString(Shared_Key.second_call,"Empty Log"));
        tv_third.setText(sharedPref.getString(Shared_Key.third_call,"Empty Log"));
        progress.setMax(sharedPref.getInt(Shared_Key.utilisation_max_call,10) * 60);
        progress.setProgress(sharedPref.getInt(Shared_Key.utilisation_duree_call,1) );
        progress.setSecondaryProgress(sharedPref.getInt(Shared_Key.utilisation_courante_call,0) + 1000);


    }

}
