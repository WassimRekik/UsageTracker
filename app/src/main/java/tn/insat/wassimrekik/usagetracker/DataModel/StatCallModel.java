package tn.insat.wassimrekik.usagetracker.DataModel;

import io.realm.RealmObject;

/**
 * Created by wassimrekik on 05/04/2018.
 */

public class StatCallModel extends RealmObject{
    private int utilisation_courante;
    private int utilisation_max;
    private String first_call;
    private String second_call;
    private String third_call;
    private int first_duration;
    private int second_duration;
    private int third_duration;

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

    public String getFirst_call() {
        return first_call;
    }

    public void setFirst_call(String first_call) {
        this.first_call = first_call;
    }

    public String getSecond_call() {
        return second_call;
    }

    public void setSecond_call(String second_call) {
        this.second_call = second_call;
    }

    public String getThird_call() {
        return third_call;
    }

    public void setThird_call(String third_call) {
        this.third_call = third_call;
    }

    public int getFirst_duration() {
        return first_duration;
    }

    public void setFirst_duration(int first_duration) {
        this.first_duration = first_duration;
    }

    public int getSecond_duration() {
        return second_duration;
    }

    public void setSecond_duration(int second_duration) {
        this.second_duration = second_duration;
    }

    public int getThird_duration() {
        return third_duration;
    }

    public void setThird_duration(int third_duration) {
        this.third_duration = third_duration;
    }
}
