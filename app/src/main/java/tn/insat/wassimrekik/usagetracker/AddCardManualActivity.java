package tn.insat.wassimrekik.usagetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import tn.insat.wassimrekik.usagetracker.DataModel.CardModel;

public class AddCardManualActivity extends AppCompatActivity {
    EditText numero_carte;
    NumberPicker npRecharge;
    NumberPicker npOperator;
    ImageButton annuler_action;
    ImageButton valider_action;
    String[] valueSetRecharge;
    String[] valueSetOperator;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_manual);
        setTitle("Sauvegarde des cartes");

        realm = Realm.getDefaultInstance();

        numero_carte = (EditText) findViewById(R.id.editText_num_carte);
        npRecharge = (NumberPicker) findViewById(R.id.numberPickerRecharge);
        npOperator = (NumberPicker) findViewById(R.id.operatorPicker);
        annuler_action = (ImageButton) findViewById(R.id.imageButton_annuler);
        valider_action = (ImageButton) findViewById(R.id.imageButton_valider);

        valueSetRecharge = new String[4];
        valueSetRecharge[0] = "1";
        valueSetRecharge[1] = "5";
        valueSetRecharge[2] = "10";
        valueSetRecharge[3] = "20";
        npRecharge.setMinValue(0);
        npRecharge.setMaxValue(3);
        npRecharge.setDisplayedValues(valueSetRecharge);
        npRecharge.setWrapSelectorWheel(true);

        valueSetOperator = new String [3];
        valueSetOperator[0] = "Orange TN";
        valueSetOperator[1] = "Ooredoo TN";
        valueSetOperator[2] = "Tunisie Telecom";
        npOperator.setMaxValue(2);
        npOperator.setMinValue(0);
        npOperator.setDisplayedValues(valueSetOperator);
        npOperator.setWrapSelectorWheel(true);

        annuler_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        valider_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numero_carte.getText().length() > 10) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
                    final String currentDateandTime = sdf.format(new Date());
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            CardModel card = realm.createObject(CardModel.class);
                            card.setNumber(numero_carte.getText().toString());
                            card.setMontant(valueSetRecharge[npRecharge.getValue()]);
                            card.setOperateur(valueSetOperator[npOperator.getValue()]);
                            card.setDate(currentDateandTime);
                        }
                    });

                }
                finish();
            }
        });

    }
}
