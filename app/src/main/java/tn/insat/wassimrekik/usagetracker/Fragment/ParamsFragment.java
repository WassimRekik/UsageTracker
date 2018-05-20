package tn.insat.wassimrekik.usagetracker.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import mehdi.sakout.fancybuttons.FancyButton;
import tn.insat.wassimrekik.usagetracker.DataModel.Shared_Key;
import tn.insat.wassimrekik.usagetracker.R;
import tn.insat.wassimrekik.usagetracker.Util_Services.StatService;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParamsFragment extends Fragment {

    SharedPreferences sharedPref;
    EditText appel_max, msg_max, data_max, prix_app, prix_msg;
    FancyButton Save;

    public ParamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_params,container,false);
        sharedPref = getActivity().getSharedPreferences(Shared_Key.shared_pref_key, Context.MODE_PRIVATE);
        appel_max = (EditText) rootView.findViewById(R.id.editText_param_max_appel);
        msg_max = (EditText) rootView.findViewById(R.id.editText_param_max_msg);
        data_max = (EditText) rootView.findViewById(R.id.editText_param_max_data);
        prix_app = (EditText) rootView.findViewById(R.id.editText_param_prix_min);
        prix_msg = (EditText) rootView.findViewById(R.id.editText_prix_msg);
        Save = (FancyButton) rootView.findViewById(R.id.btn_save_param);
        appel_max.setText(sharedPref.getInt(Shared_Key.utilisation_max_call,600)+"");
        msg_max.setText(sharedPref.getInt(Shared_Key.utilisation_max_msg,10)+"");
        data_max.setText(sharedPref.getInt(Shared_Key.utilisation_max_data,100)+"");
        prix_app.setText(sharedPref.getInt(Shared_Key.Prix_minute,100)+"");
        prix_msg.setText(sharedPref.getInt(Shared_Key.Prix_message,40)+"");

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int max_app = 100;
                int max_msg = 10;
                int max_data= 100;
                int prix_min = 100;
                int prix_mesg = 40;
                if (appel_max.getText().toString().length()>0){
                    max_app = Integer.parseInt(appel_max.getText().toString());
                }
                if (msg_max.getText().toString().length()>0){
                    max_msg = Integer.parseInt(msg_max.getText().toString());
                }
                if (data_max.getText().toString().length()>0){
                    max_data = Integer.parseInt(data_max.getText().toString());
                }
                if (prix_app.getText().toString().length()>0){
                    prix_min = Integer.parseInt(prix_app.getText().toString());
                }
                if (prix_msg.getText().toString().length()>0){
                    prix_mesg = Integer.parseInt(prix_msg.getText().toString());
                }
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(Shared_Key.utilisation_max_msg,max_msg);
                editor.putInt(Shared_Key.utilisation_max_call,max_app);
                editor.putInt(Shared_Key.utilisation_max_data,max_data);
                editor.putInt(Shared_Key.Prix_minute,prix_min);
                editor.putInt(Shared_Key.Prix_message,prix_mesg);
                editor.apply();

                SuperActivityToast.create(getActivity(), new Style(), Style.TYPE_BUTTON)
                        .setProgressBarColor(Color.WHITE)
                        .setText("Vos paramètres sont mis a jours")
                        .setDuration(Style.DURATION_LONG)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_BLUE))
                        .setAnimations(Style.ANIMATIONS_POP).show();
                Intent msgIntent = new Intent(getActivity(), StatService.class);
                getActivity().startService(msgIntent);

            }
        });

        if (container == null) {
            return null;
        }


        return rootView ;
    }

}
