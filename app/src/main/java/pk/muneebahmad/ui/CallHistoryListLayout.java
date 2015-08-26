package pk.muneebahmad.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import dringg.com.uiapp.R;
import pk.muneebahmad.util.DP;
import pk.muneebahmad.util.Log;


/**
 * Created by ay on 8/26/2015.
 */
public class CallHistoryListLayout extends LinearLayout implements View.OnClickListener {

    private LinearLayout icPanel;

    /**
     *
     * @param context
     * @param name
     * @param phoneNum
     * @param time
     */
    public CallHistoryListLayout(Context context, String name, String phoneNum, String time) {
        super(context);
        setOnClickListener(this);
        make(context, name, phoneNum, time);
    }

    /**
     *
     * @param context
     * @param name
     * @param phoneNum
     * @param time
     */
    public void make(Context context, String name, String phoneNum, String time) {
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

        TextView nView = new TextView(context);
        nView.setTextColor(Color.parseColor("#222222"));
        nView.setText(time);
        nView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        icPanel.addView(nView);

        addView(vert);
        addView(icPanel, icParams);
    }

    @Override
    public void onClick(View v) {
        if (v == this) {
            Log.log(Log.LOG_ERROR, "Hello >><<");
        } else if (v == this.icPanel) {
            Log.log(Log.LOG_ERROR, "Hello IC PANEL");
        }
    }
}/** end class. */