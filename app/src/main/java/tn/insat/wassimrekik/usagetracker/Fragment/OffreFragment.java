package tn.insat.wassimrekik.usagetracker.Fragment;


import android.database.DataSetObserver;
import android.media.Image;
import android.os.Bundle;
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
import tn.insat.wassimrekik.usagetracker.DataModel.CardModel;
import tn.insat.wassimrekik.usagetracker.DataModel.OfferModel;
import tn.insat.wassimrekik.usagetracker.ListUtils.List_card.ListAdapter_card;
import tn.insat.wassimrekik.usagetracker.ListUtils.List_offre.List_Adapter_Offre;
import tn.insat.wassimrekik.usagetracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffreFragment extends Fragment {

    ImageView Image_No_Offer;
    ListView List_Offres;
    TextView Text_NoOffer;
    Realm realm;
    int offre_size;
    List_Adapter_Offre customListAdapter;
    ArrayList<OfferModel> customListDataModelArrayList;
    public OffreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_offre,container,false);
        Image_No_Offer =(ImageView) rootView.findViewById(R.id.imageView_noOffre);
        List_Offres = (ListView) rootView.findViewById(R.id.List_Offre);
        Text_NoOffer = (TextView) rootView.findViewById(R.id.textView_noOffre);
        realm = Realm.getDefaultInstance();
        RealmResults<OfferModel> res;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try{
                    RealmResults<OfferModel> res = realm.where(OfferModel.class).findAll();
                    offre_size = res.size();
                    Log.e("Offre", offre_size+"");
                }catch (NullPointerException e){
                    Log.e("Offre", "Clearing error");
                }
            }
        });
        customListDataModelArrayList=new ArrayList<OfferModel>();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RealmResults<OfferModel> result = realm.where(OfferModel.class).findAll();
                    for (int i = 0; i < result.size(); i++) {
                        OfferModel customListDataModel = new OfferModel();
                        customListDataModel.setTitle(result.get(i).getTitle());
                        customListDataModel.setDescription(result.get(i).getDescription());
                        customListDataModel.setOffer_code(result.get(i).getOffer_code());
                        customListDataModel.setOffer_operator(result.get(i).getOffer_operator());
                        customListDataModel.setType(result.get(i).getType());
                        customListDataModelArrayList.add(customListDataModel);
                    }
                } catch (NullPointerException e) {
                    Log.e("Offre", "Clearing error");
                }
            }
        });
        customListAdapter = new List_Adapter_Offre(getActivity(), customListDataModelArrayList);
        customListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (customListAdapter.getCount() == 0){
                    Image_No_Offer.setVisibility(View.VISIBLE);
                    Text_NoOffer.setVisibility(ImageView.VISIBLE);
                    List_Offres.setVisibility(ListView.GONE);
                }else{
                    Image_No_Offer.setVisibility(View.GONE);
                    Text_NoOffer.setVisibility(ImageView.GONE);
                    List_Offres.setVisibility(ListView.VISIBLE);
                }
            }
        });
        List_Offres.setAdapter(customListAdapter);
        if (customListDataModelArrayList.size() == 0){
            Image_No_Offer.setVisibility(ImageView.VISIBLE);
            Text_NoOffer.setVisibility(ImageView.VISIBLE);
            List_Offres.setVisibility(ListView.GONE);
        }else{
            Image_No_Offer.setVisibility(ImageView.GONE);
            Text_NoOffer.setVisibility(ImageView.GONE);
            List_Offres.setVisibility(ListView.VISIBLE);
        }

        if (container == null) {
            return null;
        }


        return rootView ;    }

}
