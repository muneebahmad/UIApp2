package pk.muneebahmad.ui;

import pk.muneebahmad.util.Log;

/**
 * Created by muneebahmad on 8/25/2015.
 */
public class UIManager {

    public static UIManager sInstance = null;

    public static enum ActivatedView {
        VIEW_SMS,
        VIEW_CALL_LOG,
        VIEW_MAIN,
        VIEW_SPLASH,
        VIEW_DIALER,
        VIEW_CONTACTS,
        VIEW_SETTINGS,
        VIEW_SEARCH,
        VIEW_FAVORITES,
        VIEW_NULL
    }

    private ActivatedView activatedView = ActivatedView.VIEW_NULL;

    public UIManager() {

    }

    /**
     *
     * @return
     */
    public ActivatedView getActivatedView() {
        return activatedView;
    }

    /**
     *
     * @param activatedView
     */
    public void setActivatedView(ActivatedView activatedView) {
        this.activatedView = activatedView;
        Log.log(Log.LOG_ERROR, activatedView + "");
    }

    /**
     *
     * @return Singleton instance.
     */
    public static UIManager getsInstance() {
        synchronized (UIManager.class) {
            if (sInstance == null) {
                sInstance = new UIManager();
            }
            return sInstance;
        }
    }

}/** end class. */
