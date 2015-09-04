package pk.muneebahmad.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dringg.com.uiapp.CallDetailsActivity;
import dringg.com.uiapp.Messenger;
import dringg.com.uiapp.R;
import pk.muneebahmad.data.SharedData;
import pk.muneebahmad.util.DP;
import pk.muneebahmad.util.Log;


/**
 * Created by muneebahmad on 8/26/2015.
 */
public class CallHistoryListLayout extends LinearLayout implements View.OnClickListener {

    private LinearLayout icPanel;
    private int i;
    private String name;
    private String phoneNum;

    public static enum AttendTypes {
        CALL_MISSED,
        CALL_INCOMING,
        CALL_OUTGOING
    }

    /**
     *
     * @param context
     * @param name
     * @param phoneNum
     * @param time
     */
    public CallHistoryListLayout(Context context, String name, String phoneNum, String time, AttendTypes attendTypes, int i) {
        super(context);
        this.i = i;
        setOnClickListener(this);
        this.name = name;
        this.phoneNum = phoneNum;
        make(context, name, phoneNum, time, attendTypes);
    }

    /**
     *
     * @param context
     * @param name
     * @param phoneNum
     * @param time
     * @param attendTypes
     */
    private void make(Context context, String name, String phoneNum, String time, AttendTypes attendTypes) {
        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setBackgroundResource(R.drawable.tv_bg);
        this.setWeightSum(4);

        LinearLayout.LayoutParams vertParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vertParams.setMargins(5, 5, 5, 5);
        vertParams.weight = 3;
        this.setLayoutParams(vertParams);
        LinearLayout.LayoutParams icParams = new LinearLayout.LayoutParams(DP.dpToPx(context, 105),
                DP.dpToPx(context, 60));
        icParams.setMargins(1, 1, 1, 1);
        icParams.weight = 1;

        LinearLayout vert = new LinearLayout(context);
        vert.setOrientation(LinearLayout.VERTICAL);
        vert.setGravity(Gravity.LEFT);
        vert.setLayoutParams(vertParams);

        TextView nameView = new TextView(context);
        nameView.setTextColor(Color.parseColor("#45BCD3"));
        nameView.setText(name);
        nameView.setGravity(Gravity.LEFT);
        vert.addView(nameView, vertParams);

        TextView phoneNumView = new TextView(context);
        phoneNumView.setTextColor(Color.BLACK);
        phoneNumView.setText(phoneNum);
        phoneNumView.setGravity(Gravity.LEFT);
        vert.addView(phoneNumView, vertParams);

        icPanel = new LinearLayout(context);
        icPanel.setOrientation(LinearLayout.VERTICAL);
        icPanel.setGravity(Gravity.RIGHT);
        icPanel.setBackgroundResource(R.drawable.tv_bg2);
        icPanel.setOnClickListener(this);

        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView nView = new TextView(context);
        nView.setTextColor(Color.parseColor("#222222"));
        if (time.equalsIgnoreCase("yesterday")) {
            nView.setTextColor(Color.RED);
        } else if (time.equalsIgnoreCase("today")) {
            nView.setTextColor(Color.parseColor("#1e90ff"));
        }
        nView.setText(time);
        nView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        if (UIManager.getInstance().getActivatedView() == UIManager.ActivatedView.VIEW_CALL_LOG ||
                UIManager.getInstance().getActivatedView() == UIManager.ActivatedView.VIEW_SMS) {
            icPanel.addView(getCallTypeImage(context, attendTypes));
        }
        icPanel.addView(nView);

        addView(vert);
        addView(icPanel, icParams);
    }

    /**
     *
     * @param context
     * @param attendTypes
     * @return
     */
    private ImageView getCallTypeImage(Context context, AttendTypes attendTypes) {
        ImageView imageView = new ImageView(context);
        if (attendTypes == AttendTypes.CALL_INCOMING) {
            imageView.setImageResource(R.drawable.incoming);
        } else if (attendTypes == AttendTypes.CALL_MISSED) {
            imageView.setImageResource(R.drawable.missed);
        } else if (attendTypes == AttendTypes.CALL_OUTGOING) {
            imageView.setImageResource(R.drawable.outgoing);
        }
        return imageView;
    }


    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v == this) {
            Log.log(Log.LOG_ERROR, "Hello >><< " + (i + 1));
            if (UIManager.getInstance().getActivatedView() == UIManager.ActivatedView.VIEW_SMS) {
                v.getContext().startActivity(new Intent(v.getContext(), Messenger.class));
                SharedData.getInstance().setMessengerName(name.toUpperCase());
                SharedData.getInstance().setMessengerNo(Integer.parseInt(phoneNum));
            } else if (UIManager.getInstance().getActivatedView() == UIManager.ActivatedView.VIEW_CALL_LOG) {
                SharedData.getInstance().setCallName(name.toUpperCase());
                SharedData.getInstance().setCallNo(phoneNum);
                v.getContext().startActivity(new Intent(v.getContext(), CallDetailsActivity.class));
            }
        } else if (v == this.icPanel) {
            Log.log(Log.LOG_ERROR, "Hello IC PANEL " + (i + 1));
        }
    }
}/** end class. */