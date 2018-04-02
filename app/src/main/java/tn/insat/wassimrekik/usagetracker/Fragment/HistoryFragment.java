package tn.insat.wassimrekik.usagetracker.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.rishabhkhanna.customtogglebutton.CustomToggleButton;
import tn.insat.wassimrekik.usagetracker.Fragment.History_Fragments.AppelHistFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.History_Fragments.MessageHistFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.Statestiques_Fragments.StatAppelFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.Statestiques_Fragments.StatMessageFragment;
import tn.insat.wassimrekik.usagetracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    CustomToggleButton toggle_Appel;
    CustomToggleButton toggle_Message;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String Appel_Frag_Tag = "Appel_Frag_Tag";
    String Message_Frag_Tag = "Message_Frag_Tag";
    Fragment AppelFragment;
    Fragment MessageFragment;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        toggle_Appel = (CustomToggleButton) rootView.findViewById(R.id.toggle_appel_hist);
        toggle_Message = (CustomToggleButton) rootView.findViewById(R.id.toggle_message_hist);
        toggle_Appel.setChecked(false);
        toggle_Message.setChecked(true);

        AppelFragment = new AppelHistFragment();
        MessageFragment = new MessageHistFragment();

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame_Hist_Content, AppelFragment ,Appel_Frag_Tag);
        fragmentTransaction.commit();

        toggle_Appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle_Appel.isChecked()){
                    toggle_Appel.setChecked(false);
                    toggle_Message.setChecked(true);
                }else{
                    toggle_Message.setChecked(true);
                }
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame_Hist_Content, AppelFragment ,Appel_Frag_Tag);
                fragmentTransaction.commit();

            }
        });
        toggle_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle_Message.isChecked()){
                    toggle_Message.setChecked(false);
                    toggle_Appel.setChecked(true);
                }else{
                    toggle_Appel.setChecked(true);
                }
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame_Hist_Content, MessageFragment ,Message_Frag_Tag);
                fragmentTransaction.commit();

            }
        });
        if (container == null) {
            return null;

        }
        return rootView;
    }

}
