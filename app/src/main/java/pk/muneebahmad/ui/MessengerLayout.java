package pk.muneebahmad.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import dringg.com.uiapp.Messenger;
import dringg.com.uiapp.R;
import pk.muneebahmad.data.SharedData;
import pk.muneebahmad.util.DP;
import pk.muneebahmad.util.Dater;
import pk.muneebahmad.util.Log;
import pk.muneebahmad.util.MyVibrator;

/**
 * Created by ay on 9/3/2015.
 */
public class MessengerLayout extends LinearLayout implements View.OnLongClickListener {

    private LinearLayout vertLayout;
    private LinearLayout hLayout;
    private TextView msgView;
    private Context context;
    private Activity activity;
    private String msg;

    private String listItems[] = {"Copy", "Forward", "Delete", "Add to Favorites"};
    private String listItems2[] = {"Copy"};
    private MsgType msgType;

    public static enum MsgType {
        MSG_TYPE_INCOMING,
        MSG_TYPE_OUTGOING
    };

    public MessengerLayout(Context context, Activity activity, String msg, MsgType msgType) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.msg = msg;
        this.msgType = msgType;
        make(context, activity, msg, msgType);
    }

    /**
     *
     * @param context
     * @param activity
     * @param msg
     * @param msgType
     */
    private void make(Context context, Activity activity, String msg, MsgType msgType) {
        setOnLongClickListener(this);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mainParams.setMargins(5, 10, 5, 10);

        this.setLayoutParams(mainParams);
        this.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams mkParams = new LinearLayout.LayoutParams(DP.dpToPx(context, 220),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mkParams.setMargins(5, 10, 5, 10);

        this.hLayout = new LinearLayout(context);
        this.hLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView timeView = new TextView(context);
        timeView.setTextColor(Color.LTGRAY);
        timeView.setTextSize(13.0f);
        timeView.setText(Dater.getInstance().getNamedDate());

        this.vertLayout = new LinearLayout(context);
        vertLayout.setOrientation(LinearLayout.VERTICAL);
        this.msgView = new TextView(context);
        this.msgView.setTextSize(19.0f);
        this.msgView.setText(msg);
        this.msgView.setTextColor(Color.WHITE);

        ImageView iView = new ImageView(context);
        iView.setImageResource(R.drawable.ic_contacts);

        vertLayout.setPadding(10, 10, 10, 10);

        if (msgType == MsgType.MSG_TYPE_OUTGOING) {
            vertLayout.setBackgroundResource(R.drawable.msg_bg_out);
            setGravity(Gravity.LEFT);
            timeView.setGravity(Gravity.LEFT);
        } else if (msgType == MsgType.MSG_TYPE_INCOMING) {
            vertLayout.setBackgroundResource(R.drawable.msg_bg_in);
            setGravity(Gravity.RIGHT);
            timeView.setGravity(Gravity.RIGHT);
        }

        this.vertLayout.setLayoutParams(mkParams);
        hLayout.addView(iView);
        hLayout.addView(timeView, tvParams);
        vertLayout.addView(hLayout);
        vertLayout.addView(this.msgView);

        this.addView(vertLayout);
    }

    @Override
    public boolean onLongClick(View v) {
        MyVibrator.getsInstance(context).myVibrate(100);
        if (activity.getLocalClassName().equalsIgnoreCase("Messenger")) {
            makeDialog(activity, "Clipboard action", listItems);
        } else if (activity.getLocalClassName().equalsIgnoreCase("MainActivity")) {
            makeDialog(activity, "Clipboard action", listItems2);
        }
        return true;
    }

    // copy text to clipboard
    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
            Log.log(Log.LOG_ERROR, "Text Copied...." + " >> " + text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
            Log.log(Log.LOG_ERROR, "Text Copied...." + " >> " + text);
        }
    }

    /**
     *
     * @param activity
     * @param title
     * @param array
     */
    private void makeDialog(final Activity activity, final String title, String[] array) {
        int style = 0;
        if (UIManager.getInstance().getSelectedTheme() == UIManager.SelectedTheme.THEME_DARK_GRAY) {
            style = R.style.AppCompatAlertDialogStyle;
        } else if (UIManager.getInstance().getSelectedTheme() == UIManager.SelectedTheme.THEME_BLUE) {
            style = R.style.AppCompatAlertDialogStyleBlue;
        } else if (UIManager.getInstance().getSelectedTheme() == UIManager.SelectedTheme.THEME_GREEN) {
            style = R.style.AppCompatAlertDialogStyleGreen;
        } else if (UIManager.getInstance().getSelectedTheme() == UIManager.SelectedTheme.THEME_LT_GRAY) {
            style = R.style.AppCompatAlertDialogStyleLtGray;
        } else if (UIManager.getInstance().getSelectedTheme() == UIManager.SelectedTheme.THEME_PL_GREEN) {
            style = R.style.AppCompatAlertDialogStyleGreen;
        } else if (UIManager.getInstance().getSelectedTheme() == UIManager.SelectedTheme.THEME_RED) {
            style = R.style.AppCompatAlertDialogStyleRed;
        } else if (UIManager.getInstance().getSelectedTheme() == UIManager.SelectedTheme.THEME_ORANGE) {
            style = R.style.AppCompatAlertDialogStyleOrange;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, style);
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
        final AlertDialog dialog = builder.create();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setClipboard(context, msgView.getText().toString());
                        dialog.cancel();
                        break;
                    case 1:
                        dialog.cancel();
                        break;
                    case 2:
                        Messenger.messenger.mainLayout.removeView(MessengerLayout.this);
                        dialog.cancel();
                        break;
                    case 3:
                        SharedData.getInstance().addToFav(msg);
                        SharedData.favNameList.add(SharedData.getInstance().getMessengerName());
                        dialog.cancel();
                        break;
                }
            }
        });
        dialog.show();
    }

}/** end class. */
