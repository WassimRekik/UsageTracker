package tn.insat.wassimrekik.usagetracker.ListUtils.List_log;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import tn.insat.wassimrekik.usagetracker.Model.CallMessageLog;
import tn.insat.wassimrekik.usagetracker.R;

/**
 * Created by Sanjeev k Saroj on 28/2/17.
 */

public class ListAdapter_Log extends BaseAdapter {

    Activity activity;
    ArrayList<CallMessageLog> customListDataModelArrayList = new ArrayList<CallMessageLog>();
    LayoutInflater layoutInflater = null;

    public ListAdapter_Log(Activity activity, ArrayList<CallMessageLog> customListDataModelArray){
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
        TextView tv_title,tv_discription,tv_duree,tv_date,tv_time,tv_accuml;

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
            vi = layoutInflater.inflate(R.layout.list_item_log,null);
            viewHolder.image_view = (ImageView) vi.findViewById(R.id.imageView_Log_Item);
            viewHolder.tv_title = (TextView) vi.findViewById(R.id.textView_title_log);
            viewHolder.tv_discription = (TextView) vi.findViewById(R.id.textView_Description_log);
            viewHolder.tv_duree = (TextView) vi.findViewById(R.id.textView_Duration_log);
            viewHolder.tv_date = (TextView) vi.findViewById(R.id.textView_Date_log);
            viewHolder.tv_time = (TextView) vi.findViewById(R.id.textView_Time_log);
            viewHolder.tv_accuml = (TextView) vi.findViewById(R.id.textView_Accuml_log);
            /*We can use setTag() and getTag() to set and get custom objects as per our requirement.
            The setTag() method takes an argument of type Object, and getTag() returns an Object.*/
            vi.setTag(viewHolder);
        }else {

             /* We recycle a View that already exists */
            viewHolder= (ViewHolder) vi.getTag();
        }
        String type = customListDataModelArrayList.get(pos).getType();
        if (type == "Appel"){
            viewHolder.image_view.setImageResource(R.mipmap.ic_call);
            viewHolder.tv_discription.setText("Appel Sortant");
            viewHolder.tv_duree.setText("Durée : " + customListDataModelArrayList.get(pos).getDuration());
            viewHolder.tv_accuml.setText("#Accumul : " + customListDataModelArrayList.get(pos).getExtra() );

        }else{
            viewHolder.image_view.setImageResource(R.mipmap.ic_message);
            viewHolder.tv_discription.setText("Message Sortant");
            int accuml = customListDataModelArrayList.size() - (pos);
            int estim  = accuml * 40;
            viewHolder.tv_duree.setText("#Estim : " + estim +"Mil");
            viewHolder.tv_accuml.setText("#Nbr : " + accuml);
        }
        viewHolder.tv_title.setText(customListDataModelArrayList.get(pos).getNumber());
        viewHolder.tv_date.setText(customListDataModelArrayList.get(pos).getDate());
        viewHolder.tv_time.setText(customListDataModelArrayList.get(pos).getTime());

        return vi;
    }

}
