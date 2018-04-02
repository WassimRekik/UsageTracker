package tn.insat.wassimrekik.usagetracker.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import tn.insat.wassimrekik.usagetracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffreFragment extends Fragment {

    ImageView Image_No_Offer;
    ListView List_Offres;
    TextView Text_NoOffer;
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


        if (true){
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
