package tn.insat.wassimrekik.usagetracker.Util_Services;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;

import android.os.Handler;
import android.util.Log;

/**
 * Created by wassimrekik on 05/04/2018.
 */

public class MyCallObserver extends ContentObserver {
    Context context;
    public MyCallObserver(Handler handler,Context context) {
        super(handler);
        this.context = context;
    }

    @Override
    public void onChange(boolean selfChange) {
        this.onChange(selfChange,null);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        //Write your code here
        //Whatever is written here will be
        //executed whenever a change is made
        Log.e("content","changed");
        Intent msgIntent = new Intent(context, StatService.class);
        context.startService(msgIntent);


    }

}
