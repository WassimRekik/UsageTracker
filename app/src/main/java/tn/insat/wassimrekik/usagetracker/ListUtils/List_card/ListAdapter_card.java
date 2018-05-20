package tn.insat.wassimrekik.usagetracker.ListUtils.List_card;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import tn.insat.wassimrekik.usagetracker.DataModel.CardModel;
import tn.insat.wassimrekik.usagetracker.R;

/**
 * Created by Sanjeev k Saroj on 28/2/17.
 */

public class ListAdapter_card extends BaseAdapter {
    final static int MY_PERMISSIONS_REQUEST_PHONE_CALL = 3;
    Activity activity;
    Realm realm;
    ArrayList<CardModel> customListDataModelArrayList = new ArrayList<CardModel>();
    LayoutInflater layoutInflater = null;

    public ListAdapter_card(Activity activity, ArrayList<CardModel> customListDataModelArray) {
        this.activity = activity;
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

    private static class ViewHolder {
        ImageView image_view;
        TextView tv_name, tv_discription;
        ImageButton eliminer_action, utiliser_action;

    }

    ViewHolder viewHolder = null;


    // this method  is called each time for arraylist data size.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View vi = view;
        final int pos = position;
        if (vi == null) {

            // create  viewholder object for list_rowcell View.
            viewHolder = new ViewHolder();
            // inflate list_rowcell for each row
            vi = layoutInflater.inflate(R.layout.list_item_card, null);
            viewHolder.image_view = (ImageView) vi.findViewById(R.id.imageView_card_offre_item);
            viewHolder.tv_name = (TextView) vi.findViewById(R.id.textView_title_card_offre);
            viewHolder.tv_discription = (TextView) vi.findViewById(R.id.textView_desc_card_offre);
            viewHolder.eliminer_action = (ImageButton) vi.findViewById(R.id.Button_eleminer_card_offre);
            viewHolder.utiliser_action = (ImageButton) vi.findViewById(R.id.Button_utiliser_card_offre);

            /*We can use setTag() and getTag() to set and get custom objects as per our requirement.
            The setTag() method takes an argument of type Object, and getTag() returns an Object.*/
            vi.setTag(viewHolder);
        } else {

             /* We recycle a View that already exists */
            viewHolder = (ViewHolder) vi.getTag();
        }
        realm = Realm.getDefaultInstance();
        //viewHolder.image_view.setImageResource(customListDataModelArrayList.get(pos)());
        viewHolder.tv_name.setText(customListDataModelArrayList.get(pos).getOperateur() + "  " + customListDataModelArrayList.get(pos).getDate());
        viewHolder.tv_discription.setText("Carte de recharge de " + customListDataModelArrayList.get(pos).getMontant() + "DT");
        viewHolder.utiliser_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String op = customListDataModelArrayList.get(pos).getOperateur();
                String code;
                if (op.toLowerCase().contains("orange")) {
                    code = "*100*";
                } else if (op.toLowerCase().contains("ooredoo")) {
                    code = "*100*";
                } else {
                    code = "*100*";
                }

                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {
                        Toast.makeText(activity, "Il faut autoriser l'accé au Téléphone (appel) dans Application/Gestionaire d'applications/Usage Tracker/Autorisations", Toast.LENGTH_SHORT).show();

                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_PHONE_CALL);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    String recharge = code + customListDataModelArrayList.get(pos).getNumber() + "#";
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", recharge, null));
                    activity.startActivity(intent);
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            CardModel result = realm.where(CardModel.class).equalTo("number", customListDataModelArrayList.get(pos).getNumber()).findFirst();
                            try{
                                result.deleteFromRealm();
                            }catch (NullPointerException e){}
                        }
                    });
                    customListDataModelArrayList.remove(pos);
                    notifyDataSetChanged();
                }

            }
        });
        viewHolder.eliminer_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        CardModel result = realm.where(CardModel.class).equalTo("number", customListDataModelArrayList.get(pos).getNumber()).findFirst();
                        try{
                            result.deleteFromRealm();
                        }catch (NullPointerException e){}
                    }
                });
                customListDataModelArrayList.remove(pos);
                notifyDataSetChanged();
            }
        });
        return vi;
    }

}