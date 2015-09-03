package pk.muneebahmad.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by muneebahmad on 9/3/2015.
 */
public class NetStats {
    public NetStats() {}

    /**
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo kWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (kWifi.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}/** end class. */
