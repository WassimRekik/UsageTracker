package tn.insat.wassimrekik.usagetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import tn.insat.wassimrekik.usagetracker.DataModel.Shared_Key;
import tn.insat.wassimrekik.usagetracker.Fragment.DebiterFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.HistoryFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.MesAlarmesFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.OffreFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.ParamsFragment;
import tn.insat.wassimrekik.usagetracker.Fragment.StatestiquesFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TextView operateur, numero;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        sharedPref = getSharedPreferences(Shared_Key.shared_pref_key, Context.MODE_PRIVATE);
        operateur = (TextView) headerView.findViewById(R.id.tv_operateur_home);
        numero = (TextView) headerView.findViewById(R.id.tv_num_home);
        TelephonyManager telephonyManager = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        String simOperatorName = "Inconnu...";
        try {
            simOperatorName = telephonyManager.getSimOperatorName();
        }catch (Exception e){
        }
        operateur.setText("Opérateur : "+simOperatorName);
        numero.setText("Numéro : "+sharedPref.getString(Shared_Key.user_phone_number,"00000000"));
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainFragment, new StatestiquesFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        int id = item.getItemId();

        if (id == R.id.nav_stat) {
            setTitle(item.getTitle());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.MainFragment, new StatestiquesFragment());
            fragmentTransaction.commit();
        } else if (id == R.id.nav_debiter) {
            setTitle(item.getTitle());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.MainFragment, new DebiterFragment());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_historique) {
            setTitle(item.getTitle());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.MainFragment, new HistoryFragment());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_alarmes) {
            setTitle(item.getTitle());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.MainFragment, new MesAlarmesFragment());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_offre) {
            setTitle(item.getTitle());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.MainFragment, new OffreFragment());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_param) {
            setTitle(item.getTitle());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.MainFragment, new ParamsFragment());
            fragmentTransaction.commit();


        }else if (id == R.id.nav_propos) {
            setTitle(item.getTitle());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
