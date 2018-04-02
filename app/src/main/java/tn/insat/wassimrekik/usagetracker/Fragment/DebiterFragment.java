package tn.insat.wassimrekik.usagetracker.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import tn.insat.wassimrekik.usagetracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DebiterFragment extends Fragment {

    FrameLayout Frame_No_Card;
    TextView Text_Ajouter;
    ListView List_Card;
    public DebiterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_debiter,container,false);
        Frame_No_Card =(FrameLayout) rootView.findViewById(R.id.Frame_no_card);
        List_Card = (ListView) rootView.findViewById(R.id.List_Cartes);
        Text_Ajouter = (TextView) rootView.findViewById(R.id.textView_Add);


        if (true){
            Frame_No_Card.setVisibility(FrameLayout.VISIBLE);
            Text_Ajouter.setVisibility(ImageView.VISIBLE);
            List_Card.setVisibility(ListView.GONE);
        }else{
            Frame_No_Card.setVisibility(FrameLayout.GONE);
            Text_Ajouter.setVisibility(ImageView.GONE);
            List_Card.setVisibility(ListView.VISIBLE);
        }

        if (container == null) {
            return null;
        }


        return rootView ;    }
}


