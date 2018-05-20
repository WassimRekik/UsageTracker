package tn.insat.wassimrekik.usagetracker.Fragment.Statestiques_Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import io.realm.Realm;
import tn.insat.wassimrekik.usagetracker.DataModel.Shared_Key;
import tn.insat.wassimrekik.usagetracker.DataModel.StatMsgModel;
import tn.insat.wassimrekik.usagetracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatMessageFragment extends Fragment {

    SharedPreferences sharedPref;
    RoundCornerProgressBar progress;
    TextView tv_Msg_Usage, tv_first, tv_second, tv_third;
    public StatMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_message_stat, container, false);
        tv_Msg_Usage = (TextView) rootView.findViewById(R.id.tv_Msg_Usage);
        tv_first = (TextView) rootView.findViewById(R.id.textView_first_msg);
        tv_second = (TextView) rootView.findViewById(R.id.textView_second_msg);
        tv_third = (TextView) rootView.findViewById(R.id.textView_third_msg);

        sharedPref = getActivity().getSharedPreferences(Shared_Key.shared_pref_key, Context.MODE_PRIVATE);
        progress = (RoundCornerProgressBar) rootView.findViewById(R.id.Progress_Msg);
        if (container == null) {
            return null;

        }
        return rootView;
    }
    @Override
    public void onResume(){
        super.onResume();

        tv_Msg_Usage.setText(sharedPref.getInt(Shared_Key.utilisation_courante_msg,0) + " Msg");
        tv_first.setText(sharedPref.getString(Shared_Key.first_msg,"Empty Log"));
        tv_second.setText(sharedPref.getString(Shared_Key.second_msg,"Empty Log"));
        tv_third.setText(sharedPref.getString(Shared_Key.third_msg,"Empty Log"));
        progress.setMax(sharedPref.getInt(Shared_Key.utilisation_max_msg,40));
        progress.setProgress(sharedPref.getInt(Shared_Key.utilisation_courante_msg,0) );
        progress.setSecondaryProgress(sharedPref.getInt(Shared_Key.utilisation_courante_msg,0));


    }

}
