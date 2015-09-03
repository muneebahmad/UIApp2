package pk.muneebahmad.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import dringg.com.uiapp.R;
import pk.muneebahmad.util.DP;

/**
 * Created by ay on 9/3/2015.
 */
public class MessengerLayout extends LinearLayout implements View.OnLongClickListener {

    private LinearLayout horLayout;
    private TextView msgView;

    public static enum MsgType {
        MSG_TYPE_INCOMING,
        MSG_TYPE_OUTGOING
    };

    public MessengerLayout(Context context, String msg, MsgType msgType) {
        super(context);
        make(context, msg, msgType);
    }

    /**
     *
     * @param context
     * @param msg
     * @param msgType
     */
    private void make(Context context, String msg, MsgType msgType) {
        setOnLongClickListener(this);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mainParams.setMargins(5, 10, 5, 10);

        this.setLayoutParams(mainParams);
        this.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams mkParams = new LinearLayout.LayoutParams(DP.dpToPx(context, 220),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mkParams.setMargins(5, 10, 5, 10);

        this.horLayout = new LinearLayout(context);
        horLayout.setOrientation(LinearLayout.HORIZONTAL);
        this.msgView = new TextView(context);
        this.msgView.setTextSize(19.0f);
        this.msgView.setText(msg);
        this.msgView.setTextColor(Color.WHITE);

        horLayout.setPadding(10, 10, 10, 10);

        if (msgType == MsgType.MSG_TYPE_OUTGOING) {
            horLayout.setBackgroundResource(R.drawable.msg_bg_out);
            setGravity(Gravity.LEFT);
        } else if (msgType == MsgType.MSG_TYPE_INCOMING) {
            horLayout.setBackgroundResource(R.drawable.msg_bg_in);
            setGravity(Gravity.RIGHT);
        }

        this.horLayout.setLayoutParams(mkParams);
        horLayout.addView(this.msgView);

        this.addView(horLayout);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

}/** end class. */
