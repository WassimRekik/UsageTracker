package tn.insat.wassimrekik.usagetracker.Util_Services;

import android.Manifest;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.SystemClock;
import android.provider.CallLog;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import tn.insat.wassimrekik.usagetracker.DataModel.CardModel;
import tn.insat.wassimrekik.usagetracker.DataModel.Shared_Key;
import tn.insat.wassimrekik.usagetracker.DataModel.StatMsgModel;
import tn.insat.wassimrekik.usagetracker.LanchActivity;
import tn.insat.wassimrekik.usagetracker.ListUtils.List_log.ListAdapter_Log;
import tn.insat.wassimrekik.usagetracker.Model.CallMessageLog;
import tn.insat.wassimrekik.usagetracker.R;
import tn.insat.wassimrekik.usagetracker.Utilities.Generic_Action;


public class StatService extends IntentService {

    boolean result = false;
    public StatService() {
        super("StatService");
    }
    int counterMsg = 0;
    int counterCallDuration = 0;
    int counterCall = 0;
    String namesCall[] = new String[3];
    String names[] = new String[3];

    SharedPreferences sharedPref;
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            sharedPref = getApplicationContext().getSharedPreferences(Shared_Key.shared_pref_key,MODE_PRIVATE);
            getSMSDetails(getApplicationContext());
            getCallDetails(getApplicationContext());
            Log.e("Service test", counterCall+"");
            Log.e("Service test", counterCallDuration+"");
            Log.e("Service test", namesCall[0] );
            Log.e("Service test", namesCall[1] );
            Log.e("Service test", namesCall[2] );

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(Shared_Key.utilisation_courante_msg,counterMsg);
            editor.putString(Shared_Key.first_msg,names[0]);
            editor.putString(Shared_Key.second_msg,names[1]);
            editor.putString(Shared_Key.third_msg,names[2]);
            editor.putInt(Shared_Key.utilisation_courante_call,counterCall);
            editor.putInt(Shared_Key.utilisation_duree_call,counterCallDuration);
            editor.putString(Shared_Key.first_call,namesCall[0]);
            editor.putString(Shared_Key.second_call,namesCall[1]);
            editor.putString(Shared_Key.third_call,namesCall[2]);
            editor.apply();

            showNotif();
        } catch (Exception e) {
            // message d'erreur

        }

        // maintenant on transmet le résultat
        // on pourrait avoir un Handler, BroadCast, Notification, etc.
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(LanchActivity.MyReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra("result", result);
        sendBroadcast(broadcastIntent);

    }
    private  void getSMSDetails(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        Cursor cursor = context.getContentResolver().query(Telephony.Sms.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(Telephony.Sms.ADDRESS);
        int type = cursor.getColumnIndex(Telephony.Sms.TYPE);
        int date = cursor.getColumnIndex(Telephony.Sms.DATE);
        //int duration = cursor.getColumnIndex(Telephony.Sms.);
        while (cursor.moveToNext()) {
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
            String callDateFormat = formatterDate.format(callDayTime);
            String callTimeFormat = formatterTime.format(callDayTime);
            //String callDuration = cursor.getString(duration);
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
            if (dir == "OUTGOING" && Generic_Action.check_current_month(callDayTime) ) {
                switch (counterMsg){
                    case 0:
                        names[0] = phNumber;
                        break;
                    case 1:
                        names[1] = phNumber;
                        break;
                    case 2:
                        names[2] = phNumber;
                        break;
                }
                counterMsg++;

            }
        }


        cursor.close();
    }
    private  void getCallDetails(Context context) {
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
            if (dir == "OUTGOING" && Generic_Action.check_current_month(callDayTime) ) {
                switch (counterCall){
                    case 0:
                        namesCall[0] = phNumber;
                        break;
                    case 1:
                        namesCall[1] = phNumber;
                        break;
                    case 2:
                        namesCall[2] = phNumber;
                        break;
                }
                counterCall++;
                counterCallDuration = counterCallDuration + Integer.parseInt(callDuration);
            }
        }


        cursor.close();
    }
    public void showNotif(){
        int progressCallMax = sharedPref.getInt(Shared_Key.utilisation_max_call,10) * 60;
        int progressCallMax_80 = (progressCallMax / 100) * 80;
        int progressCallDuree = sharedPref.getInt(Shared_Key.utilisation_duree_call,0);
        int progressMsgMax = sharedPref.getInt(Shared_Key.utilisation_max_msg,5);
        int progressMsgMax_80 = (progressMsgMax / 100) * 80;
        int progressMsgCurr = sharedPref.getInt(Shared_Key.utilisation_courante_msg,0);
        String alert_text = "";
        int icon = R.mipmap.ic_notif_color;
        long when = System.currentTimeMillis();
        Notification.Builder notification= new Notification.Builder(getApplicationContext());
        notification.setSmallIcon(R.mipmap.ic_notif_color);
        NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.costum_notification_layout);

        if (progressMsgCurr < progressMsgMax && progressCallDuree < progressCallMax){
            alert_text = "Votre utilisation est encore dans les limites \n";
            contentView.setTextViewText(R.id.textView_Notif_alert, alert_text);
        }
        if (progressMsgCurr > progressMsgMax_80){
            alert_text =  "80% de votre limite des msg est dépassée \n";
            contentView.setTextViewText(R.id.textView_Notif_alert, alert_text);
        }
        if (progressCallDuree > progressCallMax_80){
            alert_text = alert_text + "80% de votre limite des appels est dépassée \n";
            contentView.setTextViewText(R.id.textView_Notif_alert, alert_text);
        }
        if (progressMsgCurr > progressMsgMax){
            progressMsgCurr = progressMsgMax;
            alert_text =  "Vous avez depasser votre limite des msg \n";
            contentView.setTextViewText(R.id.textView_Notif_alert, alert_text);
        }
        if (progressCallDuree > progressCallMax){
            progressCallDuree = progressCallMax;
            alert_text = alert_text + "Vous avez depasser votre limite des appels \n";
            contentView.setTextViewText(R.id.textView_Notif_alert, alert_text);
        }
        contentView.setImageViewResource(R.id.imageNotif, R.mipmap.ic_notif_color);
        contentView.setProgressBar(R.id.progressBar_appel, progressCallMax ,progressCallDuree,false);
        contentView.setProgressBar(R.id.progressBar_msg, progressMsgMax ,progressMsgCurr ,false);

        notification.setContent(contentView) ;
        Notification notification_instance = notification.build();
        Intent notificationIntent = new Intent(this, LanchActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification_instance.contentIntent = contentIntent;

        notification_instance.flags |= Notification.FLAG_NO_CLEAR; //Do not clear the notification
        notification_instance.defaults |= Notification.DEFAULT_LIGHTS; // LED
        //notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
        //notification.defaults |= Notification.DEFAULT_SOUND; // Sound

        mNotificationManager.notify(1, notification_instance);
    }
}
