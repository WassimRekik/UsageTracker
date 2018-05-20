package tn.insat.wassimrekik.usagetracker.Util_Services;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.provider.CallLog;
import android.util.Log;
import android.widget.Toast;

public class NotificationService extends Service {

    private static final String TAG = "NotifService";
    MyCallObserver myObserver;
    public static final int MY_BACKGROUND_JOB = 0;

    private boolean isRunning  = false;

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
        myObserver = new MyCallObserver(new Handler(),getApplicationContext());
        getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI,true, myObserver);
        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");
        scheduleJob(getApplicationContext());
        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid AN
        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {

        isRunning = false;
        getContentResolver().unregisterContentObserver(myObserver);
        Log.i(TAG, "Service onDestroy");
    }
    public static void scheduleJob(Context context) {
        JobScheduler js =
                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo job = new JobInfo.Builder(
                MY_BACKGROUND_JOB,
                new ComponentName(context ,ConnectionService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(true)
                .build();
        js.schedule(job);
    }
}