package pk.muneebahmad.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dringg.com.uiapp.R;
import pk.muneebahmad.util.DP;
import pk.muneebahmad.util.Log;

/**
 * Created by ay on 8/28/2015.
 */
public class ContactsLayout extends LinearLayout implements View.OnClickListener {

    private LinearLayout icPanel;
    private int i;

    /**
     *
     * @param context
     * @param name
     * @param phoneNum
     * @param img
     * @param i
     */
    public ContactsLayout(Context context, final String name, final String phoneNum, final ImageView img, int i) {
        super(context);
        this.i = i;
        setOnClickListener(this);
        make(context, name, phoneNum, img);
    }

    /**
     *
     * @param context
     * @param name
     * @param phoneNum
     * @param img
     */
    private void make(Context context, final String name, final String phoneNum, final ImageView img) {
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
        icPanel.setGravity(Gravity.CENTER);
        icPanel.setOnClickListener(this);

        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        if (img != null) {
            icPanel.addView(img, imgParams);
        } else {
            icPanel.addView(getContactsImgs(context));
        }

        addView(icPanel, icParams);
        addView(vert, vertParams);

    }

    /**
     *
     * @param context
     * @return
     */
    private ImageView getContactsImgs(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.ic_contacts2);
        imageView.setScaleX(1.5f);
        imageView.setScaleY(1.5f);
        return imageView;
    }

    @Override
    public void onClick(View v) {
        if (v == this) {
            Log.log(Log.LOG_ERROR, "Contacts Clicked..." + (i + 1));
        } else if (v == icPanel) {
            Log.log(Log.LOG_ERROR, "Image clicked...." + (i + 1));
        }
    }

}/** end class. */
