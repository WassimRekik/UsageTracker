package tn.insat.wassimrekik.usagetracker.Util_Services;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import tn.insat.wassimrekik.usagetracker.DataModel.CardModel;
import tn.insat.wassimrekik.usagetracker.DataModel.OfferModel;
import tn.insat.wassimrekik.usagetracker.DataModel.Shared_Key;

public class ConnectionService extends JobService {
    Realm realm;
    public ConnectionService() {
    }

    @Override
    public boolean onStartJob (JobParameters params) {
        Log.e("job ","connection");
        realm = Realm.getDefaultInstance();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                Shared_Key.url_all_offers, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Server Response", response);
                try {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            try{
                                RealmResults<OfferModel> res = realm.where(OfferModel.class).findAll();
                                res.deleteAllFromRealm();
                                Log.e("Offre", "Clearing");
                            }catch (NullPointerException e){
                                Log.e("Offre", "Clearing error");
                            }
                        }
                    });
                    final JSONArray array = new JSONArray(response);
                    for (int i = 0;i< array.length();i++) {
                        final JSONObject obj = array.getJSONObject(i);
                        Log.e("Offre", obj.toString());
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                try {
                                    OfferModel customListDataModel = realm.createObject(OfferModel.class);
                                    customListDataModel.setTitle(obj.getString("offer_title"));
                                    customListDataModel.setDescription(obj.getString("offer_description"));
                                    customListDataModel.setType(obj.getString("offer_type"));
                                    customListDataModel.setQuantity(obj.getString("offer_quantity"));
                                    customListDataModel.setOffer_operator(obj.getString("offer_operator"));
                                    customListDataModel.setUnit(obj.getString("offer_unit"));
                                    customListDataModel.setOffer_code(obj.getString("offer_code"));
                                    customListDataModel.setPeriod_type(obj.getString("offer_period_type"));
                                    customListDataModel.setPeriod_lenght_type(obj.getString("offer_period_lenght_type"));
                                    customListDataModel.setPeriod_lenght(obj.getString("offer_period_lenght"));
                                    Log.e("Offre", "Added");

                                }catch(Exception e){
                                    Log.e("Offre", "Not Added");
                                }
                            }
                        });
                    }
                } catch (Throwable t) {
                    Log.e("Error", "Could not parse malformed JSON: \"" + response + "\"");

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Server Error", error.getMessage());
                Log.d("Url",Shared_Key.url_all_offers);


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(strReq);

        return true;
    }

    @Override
    public boolean onStopJob (JobParameters params) {
        Log.e("job stop ","connection");
        return true;
    }
}
