package tn.insat.wassimrekik.usagetracker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;
import tn.insat.wassimrekik.usagetracker.DataModel.Shared_Key;
import tn.insat.wassimrekik.usagetracker.Util_Services.MyCallObserver;
import tn.insat.wassimrekik.usagetracker.Util_Services.NotificationService;
import tn.insat.wassimrekik.usagetracker.Util_Services.StatService;

public class LanchActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    private MyReceiver receiver;
    AVLoadingIndicatorView Loading;
    EditText phone_number;
    LinearLayout ll_phone;
    FancyButton start;
    ImageView image_lanch;
    TextView desc_lanch;
    String phNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanch);
        Loading = (AVLoadingIndicatorView) findViewById(R.id.avi);
        start = (FancyButton) findViewById(R.id.btn_start_lanch);
        ll_phone = (LinearLayout) findViewById(R.id.LL_lanch);
        phone_number = (EditText) findViewById(R.id.editText_phone_number);
        desc_lanch = (TextView) findViewById(R.id.textView_lanch_desc);
        image_lanch = (ImageView) findViewById(R.id.imageView_lanch);
        ll_phone.setVisibility(LinearLayout.GONE);
        Loading.setVisibility(LinearLayout.GONE);
        start.setVisibility(LinearLayout.GONE);
        desc_lanch.setVisibility(LinearLayout.GONE);
        image_lanch.setVisibility(LinearLayout.GONE);
        receiver = new MyReceiver();
        startService();
    }
    @Override
    protected void onResume() {
        super.onResume();

        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(MyReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, filter);

        sharedPref = getApplicationContext().getSharedPreferences(Shared_Key.shared_pref_key,MODE_PRIVATE);
        if (sharedPref.contains(Shared_Key.user_phone_number)) {
            Loading.setVisibility(LinearLayout.VISIBLE);
            Loading.smoothToShow();
            Intent msgIntent = new Intent(this, StatService.class);
            startService(msgIntent);
            // on déclare notre Broadcast Receiver
        }else {
            ll_phone.setVisibility(View.VISIBLE);
            start.setVisibility(LinearLayout.VISIBLE);
            desc_lanch.setVisibility(LinearLayout.VISIBLE);
            image_lanch.setVisibility(LinearLayout.VISIBLE);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phNumber = phone_number.getText().toString();
                    if (phNumber.length() == 8) {
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(Shared_Key.user_phone_number, phNumber);
                        editor.apply();
                        continuer();
                    }else{
                        SuperActivityToast.create(LanchActivity.this, new Style(), Style.TYPE_BUTTON)
                                .setProgressBarColor(Color.WHITE)
                                .setText("Le numèro est incorrect !")
                                .setDuration(Style.DURATION_LONG)
                                .setFrame(Style.FRAME_LOLLIPOP)
                                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_BLUE))
                                .setAnimations(Style.ANIMATIONS_POP).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause ();
        // on désenregistre notre broadcast
        unregisterReceiver(receiver);
    }
    public class MyReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP ="tn.insat.wassimrekik.usagetracker.FINISH_ACTION";

        @Override
        public void onReceive(Context context, Intent intent) {
            //String text = intent.getStringExtra(DownloadSourceService.SOURCE_URL);
            // send text to display
            Loading.smoothToHide();
            startActivity(new Intent(LanchActivity.this,HomeActivity.class));
            finish();
        }
    }

    public void startService() {
        startService(new Intent(LanchActivity.this,NotificationService.class));
    }

    // Method to stop the service
    public void stopService() {
        stopService(new Intent(LanchActivity.this
                , NotificationService.class));
    }
    public void continuer(){
        ll_phone.setVisibility(View.GONE);
        Loading.setVisibility(View.VISIBLE);
        start.setVisibility(LinearLayout.GONE);
        desc_lanch.setVisibility(LinearLayout.GONE);
        image_lanch.setVisibility(LinearLayout.GONE);
        Loading.smoothToShow();
        receiver = new MyReceiver();
        Intent msgIntent = new Intent(this, StatService.class);
        startService(msgIntent);
        // on déclare notre Broadcast Receiver
        IntentFilter filter = new IntentFilter(MyReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, filter);

    }
}
