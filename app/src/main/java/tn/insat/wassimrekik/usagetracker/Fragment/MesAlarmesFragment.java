package tn.insat.wassimrekik.usagetracker.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tn.insat.wassimrekik.usagetracker.R;

public class MesAlarmesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mes_alarmes,container,false);

        if (container == null) {
            return null;
        }


        return rootView ;
    }
}
