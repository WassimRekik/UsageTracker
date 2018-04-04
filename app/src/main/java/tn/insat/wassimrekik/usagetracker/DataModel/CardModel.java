package tn.insat.wassimrekik.usagetracker.DataModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by wassimrekik on 04/04/2018.
 */

public class CardModel extends RealmObject {

    @Required
    String number;
    @Required
    String date;
    @Required
    String operateur;
    String montant;

    public CardModel(){}
    public CardModel(String number, String date, String operateur, String montant) {
        this.number = number;
        this.date = date;
        this.operateur = operateur;
        this.montant = montant;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }



}
