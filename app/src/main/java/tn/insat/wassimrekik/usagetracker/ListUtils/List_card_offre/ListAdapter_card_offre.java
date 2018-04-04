package tn.insat.wassimrekik.usagetracker.ListUtils.List_card_offre;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import tn.insat.wassimrekik.usagetracker.DataModel.CardModel;
import tn.insat.wassimrekik.usagetracker.R;

/**
 * Created by Sanjeev k Saroj on 28/2/17.
 */

public class ListAdapter_card_offre extends BaseAdapter {

    Activity activity;
    ArrayList<CardModel> customListDataModelArrayList = new ArrayList<CardModel>();
    LayoutInflater layoutInflater = null;

    public ListAdapter_card_offre(Activity activity, ArrayList<CardModel> customListDataModelArray){
        this.activity=activity;
        this.customListDataModelArrayList = customListDataModelArray;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return customListDataModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return customListDataModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder{
        ImageView image_view;
        TextView tv_name,tv_discription;

    }
    ViewHolder viewHolder = null;


    // this method  is called each time for arraylist data size.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View vi=view;
        final int pos = position;
        if(vi == null){

            // create  viewholder object for list_rowcell View.
            viewHolder = new ViewHolder();
            // inflate list_rowcell for each row
            vi = layoutInflater.inflate(R.layout.list_item_card_offre,null);
            viewHolder.image_view = (ImageView) vi.findViewById(R.id.imageView_card_offre_item);
            viewHolder.tv_name = (TextView) vi.findViewById(R.id.textView_title_card_offre);
            viewHolder.tv_discription = (TextView) vi.findViewById(R.id.textView_desc_card_offre);
            /*We can use setTag() and getTag() to set and get custom objects as per our requirement.
            The setTag() method takes an argument of type Object, and getTag() returns an Object.*/
            vi.setTag(viewHolder);
        }else {

             /* We recycle a View that already exists */
            viewHolder= (ViewHolder) vi.getTag();
        }

        //viewHolder.image_view.setImageResource(customListDataModelArrayList.get(pos)());
        viewHolder.tv_name.setText(customListDataModelArrayList.get(pos).getOperateur()+"  "+customListDataModelArrayList.get(pos).getDate());
        viewHolder.tv_discription.setText("Carte de recharge de " + customListDataModelArrayList.get(pos).getMontant() + "DT");


        return vi;
    }
}