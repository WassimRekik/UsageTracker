package tn.insat.wassimrekik.usagetracker.Fragment;


import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import tn.insat.wassimrekik.usagetracker.AddCardManualActivity;
import tn.insat.wassimrekik.usagetracker.DataModel.CardModel;
import tn.insat.wassimrekik.usagetracker.ListUtils.List_card.ListAdapter_card;
import tn.insat.wassimrekik.usagetracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DebiterFragment extends Fragment {

    Realm realm;
    FrameLayout Frame_No_Card;
    TextView Text_Ajouter;
    ListView List_Card;
    FloatingActionButton Button_Add_Card;
    ListAdapter_card customListAdapter;
    ArrayList<CardModel> customListDataModelArrayList;
    public DebiterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_debiter,container,false);

        realm = Realm.getDefaultInstance();

        Frame_No_Card =(FrameLayout) rootView.findViewById(R.id.Frame_no_card);
        List_Card = (ListView) rootView.findViewById(R.id.List_Cartes);
        Text_Ajouter = (TextView) rootView.findViewById(R.id.textView_Add);
        Button_Add_Card = (FloatingActionButton) rootView.findViewById(R.id.floating_Add_Card);
        Button_Add_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddCardManualActivity.class));
//                new FancyAlertDialog.Builder(getActivity())
//                        .setTitle("Choisir une méthode d'ajout de carte")
//                        .setBackgroundColor(Color.parseColor("#4CAF50"))  //Don't pass R.color.colorvalue
//                        .setMessage("Voulez Vous ajouter les carte manuellement ou par scan des données de la carte automatiquement ?")
//                        .setNegativeBtnText("Scan")
//                        .setPositiveBtnBackground(Color.parseColor("#0288d1"))  //Don't pass R.color.colorvalue
//                        .setPositiveBtnText("Manuel")
//                        .setNegativeBtnBackground(Color.parseColor("#0288d1"))  //Don't pass R.color.colorvalue
//                        .setAnimation(Animation.POP)
//                        .isCancellable(true)
//                        .setIcon(R.drawable.ic_add_card_dialog, Icon.Visible)
//                        .OnPositiveClicked(new FancyAlertDialogListener() {
//                            @Override
//                            public void OnClick() {
//                                startActivity(new Intent(getActivity(), AddCardManualActivity.class));
//                            }
//                        })
//                        .OnNegativeClicked(new FancyAlertDialogListener() {
//                            @Override
//                            public void OnClick() {
//                                startActivity(new Intent(getActivity(), CameraPreview.class));
//
//                            }
//                        })
//                        .build();
            }
        });

        if (false){
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


        return rootView ;
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.e("onResume", "Entred");
        customListDataModelArrayList=new ArrayList<CardModel>();
        RealmResults<CardModel> result = realm.where(CardModel.class).findAll();
        for (int i = 0 ; i < result.size() ; i++){
            CardModel customListDataModel = new CardModel();
            customListDataModel.setDate(result.get(i).getDate());
            customListDataModel.setMontant(result.get(i).getMontant());
            customListDataModel.setNumber(result.get(i).getNumber());
            customListDataModel.setOperateur(result.get(i).getOperateur());
            customListDataModelArrayList.add(customListDataModel);
        }
        if (customListDataModelArrayList.size() == 0){
            Frame_No_Card.setVisibility(FrameLayout.VISIBLE);
            Text_Ajouter.setVisibility(ImageView.VISIBLE);
            List_Card.setVisibility(ListView.GONE);
        }else{
            Frame_No_Card.setVisibility(FrameLayout.GONE);
            Text_Ajouter.setVisibility(ImageView.GONE);
            List_Card.setVisibility(ListView.VISIBLE);
        }
        customListAdapter = new ListAdapter_card(getActivity(), customListDataModelArrayList);
        customListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (customListAdapter.getCount() == 0){
                    Frame_No_Card.setVisibility(FrameLayout.VISIBLE);
                    Text_Ajouter.setVisibility(ImageView.VISIBLE);
                    List_Card.setVisibility(ListView.GONE);
                }else{
                    Frame_No_Card.setVisibility(FrameLayout.GONE);
                    Text_Ajouter.setVisibility(ImageView.GONE);
                    List_Card.setVisibility(ListView.VISIBLE);
                }
            }
        });
        List_Card.setAdapter(customListAdapter);

    }
}


