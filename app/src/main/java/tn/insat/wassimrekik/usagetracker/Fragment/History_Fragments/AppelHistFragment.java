package tn.insat.wassimrekik.usagetracker.Fragment.History_Fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tn.insat.wassimrekik.usagetracker.ListUtils.List_log.ListAdapter_Log;
import tn.insat.wassimrekik.usagetracker.Model.CallMessageLog;
import tn.insat.wassimrekik.usagetracker.R;
import tn.insat.wassimrekik.usagetracker.Utilities.Generic_Action;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppelHistFragment extends Fragment {

    ListView List_Appel;
    ListAdapter_Log customListAdapter;
    ArrayList<CallMessageLog> customListDataModelArrayList;
    public AppelHistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_appel_hist,container,false);
        List_Appel = (ListView) rootView.findViewById(R.id.List_Appel_Hist);


        if (container == null) {
            return null;
        }
        return rootView ;
    }

    @Override
    public void onResume() {
        super.onResume();
        customListDataModelArrayList = new ArrayList<CallMessageLog>();
        getCallDetails(getActivity());

    }

    private  void getCallDetails(Context context) {
        int accumulatedDuration = 0;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        while (cursor.moveToNext()) {
            CallMessageLog customListDataModel = new CallMessageLog();
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
            String callDateFormat = formatterDate.format(callDayTime);
            String callTimeFormat = formatterTime.format(callDayTime);
            String callDuration = cursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
                default:
                    dir = "Call";
                    break;
            }
            if (dir == "OUTGOING" && Generic_Action.check_current_month(callDayTime)) {
                accumulatedDuration = accumulatedDuration + Integer.parseInt(callDuration);
                customListDataModel.setNumber(phNumber);
                customListDataModel.setType("Appel");
                customListDataModel.setDuration(Generic_Action.convertToMinSecDuration(callDuration));
                customListDataModel.setDate(callDateFormat);
                customListDataModel.setTime(callTimeFormat);
                customListDataModel.setExtra(Generic_Action.convertToMinSecDuration(accumulatedDuration+""));
                customListDataModelArrayList.add(customListDataModel);
            }
        }
        cursor.close();
        int accuAll = Generic_Action.convertToIntDuration(customListDataModelArrayList.get(customListDataModelArrayList.size() - 1 ).getExtra());
        customListDataModelArrayList.get(0).setExtra(Generic_Action.convertToMinSecDuration(accuAll+""));
        for (int i = 1 ; i < customListDataModelArrayList.size() ; i++ ){
            int currentAcc = accuAll - Generic_Action.convertToIntDuration(customListDataModelArrayList.get(i-1).getDuration());
            accuAll = currentAcc;
            customListDataModelArrayList.get(i).setExtra(Generic_Action.convertToMinSecDuration(currentAcc+""));
        }
        customListAdapter = new ListAdapter_Log(getActivity(), customListDataModelArrayList);
        List_Appel.setAdapter(customListAdapter);
    }
}
