package pk.muneebahmad.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import dringg.com.uiapp.R;
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
    private MyTwoButtDialogListener twoButtDialogListener;
    private MyOneButtonDialogListener oneButtonDialogListener;

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
     * @param title
     * @param msg
     * @param butt1Label
     * @param butt2Label
     */
    public void makeTwoButtonDialog(Activity activity, final String title, final String msg,
                                           final String butt1Label, final String butt2Label) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
            builder.setTitle(title).setMessage(msg).
                    setPositiveButton(butt1Label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            twoButtDialogListener.onFirstButtClicked(dialog, which);
                        }
                        }).setNegativeButton(butt2Label, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            twoButtDialogListener.onSecondButtClicked(dialog, which);
                        }
            });
            AlertDialog dialog = builder.create();
        dialog.show();

    }

    /**
     *
     * @param activity
     * @param title
     * @param msg
     * @param butt1Label
     */
    public void makeOneButtonDialog(Activity activity, final String title, final String msg,
                                    final String butt1Label) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
        builder.setMessage(msg).setTitle(title).setPositiveButton(butt1Label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     *
     * @param twoButtDialogListener
     */
    public void addTwoButtonDialogClickListener(MyTwoButtDialogListener twoButtDialogListener) {
        this.twoButtDialogListener = twoButtDialogListener;
    }

    /**
     *
     * @param oneButtonDialogListener
     */
    public void addOneButtonDialogClickListner(MyOneButtonDialogListener oneButtonDialogListener) {
        this.oneButtonDialogListener = oneButtonDialogListener;
    }

    /**
     *
     * @return
     *      Singleton instance.
     */
    public static UIManager getInstance() {
        synchronized (UIManager.class) {
            if (sInstance == null) {
                sInstance = new UIManager();
            }
            return sInstance;
        }
    }

}/** end class. */
