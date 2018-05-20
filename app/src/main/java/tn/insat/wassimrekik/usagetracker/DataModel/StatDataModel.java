package tn.insat.wassimrekik.usagetracker.DataModel;

import io.realm.RealmObject;

/**
 * Created by wassimrekik on 01/01/2017.
 */

public class StatDataModel extends RealmObject {

    private int utilisation_courante;
    private int utilisation_max;
    private int utilisation_wifi;
    private String first_app;
    private String second_app;
    private String third_app;
    private int first_usage;
    private int second_usage;
    private int third_usage;

    public int getUtilisation_courante() {
        return utilisation_courante;
    }

    public void setUtilisation_courante(int utilisation_courante) {
        this.utilisation_courante = utilisation_courante;
    }

    public int getUtilisation_max() {
        return utilisation_max;
    }

    public void setUtilisation_max(int utilisation_max) {
        this.utilisation_max = utilisation_max;
    }

    public int getUtilisation_wifi() {
        return utilisation_wifi;
    }

    public void setUtilisation_wifi(int utilisation_wifi) {
        this.utilisation_wifi = utilisation_wifi;
    }

    public String getFirst_app() {
        return first_app;
    }

    public void setFirst_app(String first_app) {
        this.first_app = first_app;
    }

    public String getSecond_app() {
        return second_app;
    }

    public void setSecond_app(String second_app) {
        this.second_app = second_app;
    }

    public String getThird_app() {
        return third_app;
    }

    public void setThird_app(String third_app) {
        this.third_app = third_app;
    }

    public int getFirst_usage() {
        return first_usage;
    }

    public void setFirst_usage(int first_usage) {
        this.first_usage = first_usage;
    }

    public int getSecond_usage() {
        return second_usage;
    }

    public void setSecond_usage(int second_usage) {
        this.second_usage = second_usage;
    }

    public int getThird_usage() {
        return third_usage;
    }

    public void setThird_usage(int third_usage) {
        this.third_usage = third_usage;
    }
}
