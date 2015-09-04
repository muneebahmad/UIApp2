package pk.muneebahmad.util;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by ay on 9/4/2015.
 */
public class MyVibrator {

    public static MyVibrator sInstance = null;
    private Context context;
    public MyVibrator(Context context) {
        this.context = context;
    }

    /**
     *
     * @param millis
     */
    public void myVibrate(long millis) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(millis);
    }

    /**
     *
     * @param context
     * @return Singleton instance
     */
    public static MyVibrator getsInstance(Context context) {
        synchronized (MyVibrator.class) {
            if (sInstance == null) {
                sInstance = new MyVibrator(context);
            }
            return sInstance;
        }
    }

}/** end class. */
