package tn.insat.wassimrekik.usagetracker.DataModel;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by wassimrekik on 10/04/2018.
 */

public class OfferModel extends RealmObject {
    String title;
    String description;
    String type;
    String quantity;
    String unit;
    String period_type;
    String period_lenght_type;
    String period_lenght;
    String offer_code;
    String offer_operator;
    public OfferModel(){}
    public OfferModel(String title, String description, String type, String quantity, String unit, String period_type, String period_lenght_type, String period_lenght, String offer_code, String offer_operator) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
        this.unit = unit;
        this.period_type = period_type;
        this.period_lenght_type = period_lenght_type;
        this.period_lenght = period_lenght;
        this.offer_code = offer_code;
        this.offer_operator = offer_operator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPeriod_type() {
        return period_type;
    }

    public void setPeriod_type(String period_type) {
        this.period_type = period_type;
    }

    public String getPeriod_lenght_type() {
        return period_lenght_type;
    }

    public void setPeriod_lenght_type(String period_lenght_type) {
        this.period_lenght_type = period_lenght_type;
    }

    public String getPeriod_lenght() {
        return period_lenght;
    }

    public void setPeriod_lenght(String period_lenght) {
        this.period_lenght = period_lenght;
    }

    public String getOffer_code() {
        return offer_code;
    }

    public void setOffer_code(String offer_code) {
        this.offer_code = offer_code;
    }

    public String getOffer_operator() {
        return offer_operator;
    }

    public void setOffer_operator(String offer_operator) {
        this.offer_operator = offer_operator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
