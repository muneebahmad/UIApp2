package pk.muneebahmad.util;

/**
 * Created by ay on 8/25/2015.
 */
public class Log {
    public static final int LOG_VERBOSE = 0x01;
    public static final int LOG_ERROR = 0x02;
    public static final int LOG_INFO = 0x03;
    public static final int LOG_DEBUG = 0x04;

    public static final String PREFIX = "[UI_APP] >>> ";

    /**
     *
     * @param type Select from above.
     * @param msg java.lang.String
     */
    public static void log(int type, String msg) {
        if (type == LOG_VERBOSE) {
            android.util.Log.v(PREFIX, msg);
        } else if (type == LOG_ERROR) {
            android.util.Log.e(PREFIX, msg);
        } else if (type == LOG_DEBUG) {
            android.util.Log.d(PREFIX, msg);
        } else if (type == LOG_INFO) {
            android.util.Log.i(PREFIX, msg);
        }
    }
}/** end class. */
