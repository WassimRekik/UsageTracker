package tn.insat.wassimrekik.usagetracker.Model;

/**
 * Created by wassimrekik on 04/04/2018.
 */

public class CallMessageLog {
    private String Number;
    private String Date;
    private String Time;
    private String Type;
    private String Duration;
    private String Extra;
    public CallMessageLog(){}
    public CallMessageLog(String number, String date, String time, String type, String duration, String extra) {
        Number = number;
        Date = date;
        Time = time;
        Type = type;
        Duration = duration;
        Extra = extra;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }
}
