package tn.insat.wassimrekik.usagetracker.CameraReader;

/**
 * Created by wassimrekik on 08/04/2018.
 */

public class Wait {
    public static void oneSec() {
        try {
            Thread.currentThread().sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void manySec(long s) {
        try {
            Thread.currentThread().sleep(s * 1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
