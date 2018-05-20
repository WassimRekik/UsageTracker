package tn.insat.wassimrekik.usagetracker.DataModel;

import io.realm.RealmObject;

/**
 * Created by wassimrekik on 05/04/2018.
 */

public class StatMsgModel extends RealmObject {
    private int utilisation_courante;
    private int utilisation_max;
    private String first_msg;
    private String second_msg;
    private String third_msg;

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

    public String getFirst_msg() {
        return first_msg;
    }

    public void setFirst_msg(String first_msg) {
        this.first_msg = first_msg;
    }

    public String getSecond_msg() {
        return second_msg;
    }

    public void setSecond_msg(String second_msg) {
        this.second_msg = second_msg;
    }

    public String getThird_msg() {
        return third_msg;
    }

    public void setThird_msg(String third_msg) {
        this.third_msg = third_msg;
    }
}
