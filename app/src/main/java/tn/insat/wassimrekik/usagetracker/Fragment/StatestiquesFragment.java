package tn.insat.wassimrekik.usagetracker.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import me.rishabhkhanna.customtogglebutton.CustomToggleButton;
import tn.insat.wassimrekik.usagetracker.Fragment.Statestiques_Fragments.StatAppelFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.Statestiques_Fragments.StatDataFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.Statestiques_Fragments.StatMessageFragment;
import tn.insat.wassimrekik.usagetracker.R;


public class StatestiquesFragment extends Fragment {
    CustomToggleButton toggle_Data;
    CustomToggleButton toggle_Appel;
    CustomToggleButton toggle_Message;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String Data_Frag_Tag = "Data_Frag_Tag";
    String Appel_Frag_Tag = "Appel_Frag_Tag";
    String Message_Frag_Tag = "Message_Frag_Tag";
    Fragment DataFragment;
    Fragment AppelFragment;
    Fragment MessageFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statestiques, container, false);
        toggle_Data = (CustomToggleButton) rootView.findViewById(R.id.toggle_data);
        toggle_Appel = (CustomToggleButton) rootView.findViewById(R.id.toggle_appel);
        toggle_Message = (CustomToggleButton) rootView.findViewById(R.id.toggle_message);
        toggle_Data.setChecked(false);
        toggle_Appel.setChecked(true);
        toggle_Message.setChecked(true);

        DataFragment = new StatDataFragment();
        AppelFragment = new StatAppelFragment();
        MessageFragment = new StatMessageFragment();

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame_Stat_Content, DataFragment ,Data_Frag_Tag);
        fragmentTransaction.commit();

        toggle_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle_Data.isChecked()){
                    toggle_Data.setChecked(false);
                    toggle_Appel.setChecked(true);
                    toggle_Message.setChecked(true);
                }else{
                    toggle_Appel.setChecked(true);
                    toggle_Message.setChecked(true);
                }
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame_Stat_Content, DataFragment ,Data_Frag_Tag);
                fragmentTransaction.commit();


            }
        });
        toggle_Appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle_Appel.isChecked()){
                    toggle_Appel.setChecked(false);
                    toggle_Data.setChecked(true);
                    toggle_Message.setChecked(true);
                }else{
                    toggle_Data.setChecked(true);
                    toggle_Message.setChecked(true);
                }
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame_Stat_Content, AppelFragment ,Appel_Frag_Tag);
                fragmentTransaction.commit();

            }
        });
        toggle_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle_Message.isChecked()){
                    toggle_Message.setChecked(false);
                    toggle_Data.setChecked(true);
                    toggle_Appel.setChecked(true);
                }else{
                    toggle_Data.setChecked(true);
                    toggle_Appel.setChecked(true);
                }
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Frame_Stat_Content, MessageFragment ,Message_Frag_Tag);
                fragmentTransaction.commit();

            }
        });

        if (container == null) {
            return null;

        }
        return rootView;

    }
    @Override
    public  void onResume(){
        super.onResume();

    }

}
