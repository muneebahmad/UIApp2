package pk.muneebahmad.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import dringg.com.uiapp.Messenger;
import dringg.com.uiapp.R;
import pk.muneebahmad.util.Log;

/**
 * Created by muneebahmad on 8/25/2015.
 */
public class UIManager {

    public static UIManager sInstance = null;

    private int color = Color.parseColor("#558b2f");

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

    public static enum SelectedTheme {
        THEME_DARK_GRAY,
        THEME_ORANGE,
        THEME_RED,
        THEME_LT_GRAY,
        THEME_GREEN,
        THEME_PL_GREEN,
        THEME_BLUE,
        THEME_WA
    }

    private ActivatedView activatedView = ActivatedView.VIEW_NULL;
    private MyTwoButtDialogListener twoButtDialogListener;
    private MyOneButtonDialogListener oneButtonDialogListener;
    private SelectedTheme selectedTheme;

    public UIManager() {
        this.selectedTheme = SelectedTheme.THEME_DARK_GRAY;
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
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, getStyleID());
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
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, getStyleID());
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
     * @param title
     * @param array
     */
    public void makeCpDialog(final Activity activity, final String title, String[] array) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, getStyleID());
        LayoutInflater inflater = activity.getLayoutInflater();
        View cView = (View) inflater.inflate(R.layout.clip_dialog_layout, null);
        builder.setTitle(title);
        builder.setView(cView);
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        ListView lv = (ListView) cView.findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, array);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int getStyleID() {
        if (getSelectedTheme() == SelectedTheme.THEME_DARK_GRAY) {
            return R.style.AppCompatAlertDialogStyle;
        } else if (getSelectedTheme() == SelectedTheme.THEME_BLUE) {
            return R.style.AppCompatAlertDialogStyleBlue;
        } else if (getSelectedTheme() == SelectedTheme.THEME_GREEN) {
            return R.style.AppCompatAlertDialogStyleGreen;
        } else if (getSelectedTheme() == SelectedTheme.THEME_LT_GRAY) {
            return R.style.AppCompatAlertDialogStyleLtGray;
        } else if (getSelectedTheme() == SelectedTheme.THEME_PL_GREEN) {
            return R.style.AppCompatAlertDialogStyleGreen;
        } else if (getSelectedTheme() == SelectedTheme.THEME_RED) {
            return R.style.AppCompatAlertDialogStyleRed;
        } else if (getSelectedTheme() == SelectedTheme.THEME_ORANGE) {
            return R.style.AppCompatAlertDialogStyleOrange;
        } else if (getSelectedTheme() == SelectedTheme.THEME_WA) {
            return R.style.AppCompatAlertDialogStyleWA;
        } else {
            return 0;
        }
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
     */
    public SelectedTheme getSelectedTheme() {
        return selectedTheme;
    }

    /**
     *
     * @param selectedTheme
     */
    public void setSelectedTheme(SelectedTheme selectedTheme) {
        this.selectedTheme = selectedTheme;
    }

    public String getThemeColor() {
        if (selectedTheme == SelectedTheme.THEME_DARK_GRAY) {
            return "#555555";
        } else if (selectedTheme == SelectedTheme.THEME_GREEN) {
            return "#558500";
        } else if (selectedTheme == SelectedTheme.THEME_LT_GRAY) {
            return "#999999";
        } else if (selectedTheme == SelectedTheme.THEME_ORANGE) {
            return "#ffd700";
        } else if (selectedTheme == SelectedTheme.THEME_RED) {
            return "#ff0000";
        } else if (selectedTheme == SelectedTheme.THEME_BLUE) {
            return "#1e90ff";
        } else if (selectedTheme == SelectedTheme.THEME_PL_GREEN) {
            return "#00e600";
        } else if (selectedTheme == SelectedTheme.THEME_WA) {
            return "#ff2e8a06";
        } else {
            return "#555555";
        }
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
